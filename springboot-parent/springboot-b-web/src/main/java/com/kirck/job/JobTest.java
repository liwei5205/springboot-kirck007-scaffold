package com.kirck.job;

import com.alibaba.fastjson.JSONObject;
import com.kirck.commen.constants.RedisConstants;
import com.kirck.commen.constants.SysConstants;
import com.kirck.commen.utils.UUIDUtils;
import com.kirck.entity.MerchantDeal;
import com.kirck.service.IDianPingService;
import com.kirck.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JobTest {

    @Autowired
    private IDianPingService dianPingService;

    @Resource
    RedisTemplate<String, List<Map<String,Object>>> redisTemplate;

    @Scheduled(cron = "0/2 * * * * *")
    public void job(){
        System.out.println("Job"+new Date());
    }

    public void job2(){
        //查找最新的折扣信息记录
        String urlId = dianPingService.getTheLastDeal();
        //打开浏览器
        ChromeDriver webDriver= (ChromeDriver) BrowserUtils.openBrowser(SysConstants.SysConfig.CHROMEDRIVER, SysConstants.SysConfig.CHROMEDRIVERPATH);
        //设置缓存
        setCookie(webDriver);
        // 跳转到最新信息链接
        //"http://t.dianping.com/list/shanghai-category_1?desc=1&sort=new&pageIndex=0"
        String url = SysConstants.SysConfig.DIANPINGLIST
                +SysConstants.Symbol.SLASH
                +"shanghai"
                +SysConstants.Symbol.DASH
                +SysConstants.SysConfig.CATEGORY
                +SysConstants.Symbol.UNDERLINE
                +"1"
                +SysConstants.Symbol.STRING_QUESTION
                +SysConstants.SysConfig.NEWSORT
                +"0";

        webDriver.get(url);
        // 等待是否跳转成功
        try {
            while (true) {
                Thread.sleep(2000L);
                if (!webDriver.getCurrentUrl().startsWith(SysConstants.SysConfig.DIANPINGLOGINURL)) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //解析团购信息
        parseDeal(webDriver);

        BrowserUtils.closeBrowser(webDriver);

    }

    /**
     * 解析团购信息
     * @param webDriver
     */
    private void parseDeal(ChromeDriver webDriver) {
        // 团购信息存储
        List<MerchantDeal> merchantDeals = new ArrayList<MerchantDeal>();
        WebElement element = webDriver.findElement(By.cssSelector("div.tg-tab-box.tg-floor.on"));
        // 获取属性值
        List<WebElement> elements2 = element.findElements(By.cssSelector("div.tg-floor-item-wrap"));
        for (WebElement webElement : elements2) {
            MerchantDeal merchantDeal = new MerchantDeal();
            String href = webElement.findElement(By.cssSelector("a.tg-floor-img")).getAttribute("href");
            merchantDeal.setId(UUIDUtils.getNewId());
            // merchantDeal.setMerchantId(merchantId);
            merchantDeal.setDealTitle(webElement.findElement(By.tagName("h4")).getText());
            //merchantDeal.setNotes(webElement.findElement(By.tagName("h4")).getText());
            merchantDeal.setPrice(new BigDecimal(webElement.findElement(By.tagName("em")).getText()));
            merchantDeal.setStorePrice(new BigDecimal(webElement.findElement(By.tagName("del")).getText()));
            //merchantDeal.setUrl(href);
            merchantDeals.add(merchantDeal);
        }
        dianPingService.saveOrUpdate(merchantDeals);
    }

    private void setCookie(ChromeDriver webDriver) {
        String cookiePath = RedisConstants.KEYPRE.DIANPING+RedisConstants.OBJTYPE.COOKIES + SysConstants.SysConfig.USERNAME;
        List<Map<String, Object>> cookies = null;
        while (cookies==null){
            //查找缓存
            cookies =  redisTemplate.opsForValue().get(cookiePath);
            if(cookies==null) {
                //没有缓存需要登陆
                cookies = BrowserUtils.loginDianPing(webDriver, SysConstants.SysConfig.USERNAME, SysConstants.SysConfig.PASSWORD);
                redisTemplate.opsForValue().set(cookiePath,cookies);
            }
        }
        // 清除原有cookie
        webDriver.manage().deleteAllCookies();
        // 添加cookie需要访问一个同域名页面
        webDriver.get(SysConstants.SysConfig.DIANPINGHOMEURL);
        for (Map<String, Object> map : cookies) {
            Cookie cookie = JSONObject.parseObject(JSONObject.toJSONString(map), Cookie.class);
            webDriver.manage().addCookie(cookie);
        }
    }
}
