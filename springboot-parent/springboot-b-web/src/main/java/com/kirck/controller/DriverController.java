package com.kirck.controller;

import javax.annotation.Resource;


import com.alibaba.fastjson.JSONObject;
import com.kirck.commen.constants.RedisConstants;
import io.swagger.annotations.ApiOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;


import java.util.Set;
import java.util.concurrent.TimeUnit;

@Api(value = "浏览器相关controller", tags = { "登录操作接口" })
@RestController
@RequestMapping("driver")
public class DriverController extends BaseController{

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    private static ChromeDriver browser;
    private final  static  String LOGINURL = "https://account.dianping.com/login";
    private final static  String NEWDEALURL = "http://t.dianping.com/list/shanghai-category_1";
    private final  static String COOKIEPATH = RedisConstants.KEYPRE.DIANPING+RedisConstants.OBJTYPE.COOKIES;

    @GetMapping(value = "/hello")
    @ResponseBody
    @ApiOperation(value = "欢迎", httpMethod = "GET")
    public String login(String userName,String password){

        browser = (ChromeDriver) openBrowser("webdriver.chrome.driver", "D:/project/chromedriver.exe");

        boolean f = true;
        while (f) {
            Set<Cookie> cookies = (Set<Cookie>) redisTemplate.opsForValue().get(COOKIEPATH + userName);
            if (CollectionUtils.isEmpty(cookies)) {
                loginDianPing(browser, userName, password);
            }else{
                for (Object temp : cookies) {
                    Cookie cookie = JSONObject.parseObject(JSONObject.toJSONString(temp), Cookie.class);
                    browser.manage().addCookie(cookie);
                }
                f = false;
            }
        }
        browser.get(NEWDEALURL);
        WebDriverWait webDriverWait=new WebDriverWait(browser,5);
        String text = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("tg-floor-list Fix tg-floor-list-freak"))).getText();
        System.out.println(text);
        closeBrowser(browser);
        return "hello";
    }

    private WebDriver openBrowser(String driverType, String driverPath){
        System.getProperties().setProperty(driverType,driverPath);
        browser = new ChromeDriver();
        //等待
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        return browser;
    }

    private void closeBrowser(WebDriver webDriver){
        webDriver.close();
    }

    private void loginDianPing(WebDriver webDriver,String userName,String password){
        //设定网址
        webDriver.get(LOGINURL);
        //显示等待控制对象
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("账号登录"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("tab-account"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("account-textbox"))).sendKeys(userName);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("password-textbox"))).sendKeys(password);
        webDriver.findElement(By.id("login-button-account")).click();
        //等待2秒用于页面加载，保证Cookie响应全部获取。
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Set<Cookie> cookies=webDriver.manage().getCookies();
        if(cookies!=null){
            redisTemplate.opsForValue().set(COOKIEPATH+userName,cookies);
        }
    }
}
