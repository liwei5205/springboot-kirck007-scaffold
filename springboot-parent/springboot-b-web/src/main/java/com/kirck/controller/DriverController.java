package com.kirck.controller;

import javax.annotation.Resource;


import com.alibaba.fastjson.JSONObject;
import com.kirck.commen.constants.RedisConstants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.exec.util.MapUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Api(value = "浏览器相关controller", tags = { "登录操作接口" })
@RestController
@RequestMapping("driver")
public class DriverController extends BaseController{

    @Resource
    RedisTemplate<String, List<Map<String,Object>>> redisTemplate;

    private static ChromeDriver browser;
    private final  static  String HOMEURL = "http://www.dianping.com";
    private final  static  String LOGINURL = "https://account.dianping.com/login";
    private final static  String NEWDEALURL = "http://t.dianping.com/list/shanghai-category_1?desc=1&sort=new&pageIndex=";
    private final  static String COOKIEPATH = RedisConstants.KEYPRE.DIANPING+RedisConstants.OBJTYPE.COOKIES;
    private final static String  USERNAME = "18571844624";
    private final static String  PASSWORD = "Qq276532727";

    @GetMapping(value = "/hello")
    @ResponseBody
    @ApiOperation(value = "欢迎", httpMethod = "GET")
    public String login(Integer index){

        browser = (ChromeDriver) openBrowser("webdriver.chrome.driver", "D:/project/chromedriver.exe");

        boolean f = true;
        while (f) {
            List<Map<String,Object>> cookies =  redisTemplate.opsForValue().get(COOKIEPATH + USERNAME);
            if (cookies==null) {
                loginDianPing(browser, USERNAME, PASSWORD);
            }else{
                browser.manage().deleteAllCookies();
                browser.get(HOMEURL);
                for (Map<String,Object> temp : cookies) {
                    Cookie parse = JSONObject.parseObject(JSONObject.toJSONString(temp), Cookie.class);
                    System.out.println(parse.toString());
                    browser.manage().addCookie(parse);
                }
                f = false;
            }
        }
        browser.get(NEWDEALURL+index);

        WebDriverWait webDriverWait=new WebDriverWait(browser,5);
        String text = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("body"))).getText();
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
/*        WebDriverWait webDriverWait=new WebDriverWait(webDriver,10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("账号登录"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("tab-account"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("account-textbox"))).sendKeys(userName);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("password-textbox"))).sendKeys(password);
        webDriver.findElement(By.id("login-button-account")).click();*/
        //等待2秒用于页面加载，保证Cookie响应全部获取。
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Set<Cookie> cookies=webDriver.manage().getCookies();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Cookie cookie : cookies) {
            list.add(cookie.toJson());
        }
        if(cookies!=null){
            redisTemplate.opsForValue().set(COOKIEPATH+userName,list);
        }
    }
}
