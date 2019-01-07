package com.kirck.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.kirck.commen.NumberConstants;
import com.kirck.commen.constants.RedisConstants;
import com.kirck.commen.constants.SysConstants;
import com.kirck.commen.utils.TitleUtils;
import com.kirck.commen.utils.UUIDUtils;
import com.kirck.entity.MerchantDeal;
import com.kirck.service.IDianPingService;
import com.kirck.utils.BrowserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "浏览器相关controller", tags = { "登录操作接口" })
@RestController
@RequestMapping("driver")
public class DriverController extends BaseController{
	
	@Autowired
	private IDianPingService dianPingService;

    @Resource
    RedisTemplate<String, List<Map<String,Object>>> redisTemplate;


	@GetMapping(value = "/hello")
	@ResponseBody
	@ApiOperation(value = "欢迎", httpMethod = "GET")
	public String setCategoryAll() {
		 //查找最新的折扣信息记录
		MerchantDeal lastDeal = dianPingService.getTheLastDeal();
        //打开浏览器
        ChromeDriver webDriver= (ChromeDriver) BrowserUtils.openBrowser(SysConstants.SysConfig.CHROMEDRIVER, SysConstants.SysConfig.CHROMEDRIVERPATH);
        //设置缓存
        setCookie(webDriver);
        // 跳转到最新信息链接
        //"http://t.dianping.com/deal/35503903"
        String url = SysConstants.SysConfig.DIANPINGDEAl+SysConstants.Symbol.SLASH+lastDeal.getDianpingUrlId();

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
        //获取商户id
        //String merchantId = webDriver
        String title = webDriver.findElement(By.cssSelector("h2[class=sub-title]")).getText();
        try {
        	title = TitleUtils.getTitle(title);
        }catch (Exception e) {
        	logger.error("titleStringErro",e);
		}
        lastDeal.setDealTitle(title);
        
        //获取团购适用分店列表
        WebElement shopUl =  webDriver.findElement(By.cssSelector("ul[class=shoplist]"));
        List<WebElement> shoplist = shopUl.findElements(By.tagName("li"));
        System.out.println("shoplist:"+shoplist.size());
        //获取当前展开分店
        String openShopId = webDriver.findElement(By.cssSelector("li.J_content_list.on")).getAttribute("data-shop-id");
        System.out.println("openShopId:"+openShopId);
        //http://www.dianping.com/shop/549201
        url = SysConstants.SysConfig.DIANPINGHOMEURL+SysConstants.Symbol.SLASH+SysConstants.SysConfig.SHOP+SysConstants.Symbol.SLASH+openShopId;
        webDriver.get(url);
        // 等待是否跳转成功
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // 最多等10秒
        String brandUrl = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("a.more-shop"))).getAttribute("href");
        //获取品牌信息
        
        dianPingService.update(lastDeal);
        BrowserUtils.closeBrowser(webDriver);
        
		return "hello";
	}
	/*
	private void setCategoryAll() {
		List<WebElement> elements = browser.findElements(By.cssSelector("dl.tg-classify-all.tg-classify-flat.Fix"));
		for (WebElement element : elements) {
			// 获取属性值
			// String str = element.getAttribute("data-static-deal-id");
			List<WebElement> elements2 = element.findElements(By.tagName("a"));
			Map<String, String> map = new HashMap<String, String>();
			for (WebElement webElement : elements2) {
				String category = webElement.getAttribute("href");
				map.put(webElement.getText(), category.substring(category.indexOf('_')+1, category.indexOf('?')));
			}
			redisTemplate.opsForHash().putAll(CATEGORYPATH, map);
		}
	}
	*/
	
	   private MerchantDeal parseDealInfo(ChromeDriver webDriver, MerchantDeal lastDeal) {
		   
		   return null;
	}

	/**
     * 解析团购信息
     * @param webDriver
     */
    private List<MerchantDeal> parseDeal(ChromeDriver webDriver,String lastUrlId) {
        // 团购信息存储
        List<MerchantDeal> merchantDeals = new ArrayList<MerchantDeal>();
        WebElement element = webDriver.findElement(By.cssSelector("div.tg-tab-box.tg-floor.on"));
        // 获取属性值
        List<WebElement> elements2 = element.findElements(By.cssSelector("div.tg-floor-item-wrap"));
        for (WebElement webElement : elements2) {
            MerchantDeal merchantDeal = new MerchantDeal();
            String href = webElement.findElement(By.cssSelector("a.tg-floor-img")).getAttribute("href");
            String urlId = href.substring(href.lastIndexOf('/')+NumberConstants.ONE);
            if(lastUrlId.equals(urlId)) {
            	break;
            }
            merchantDeal.setId(UUIDUtils.getNewId());
            merchantDeal.setCreateDate(LocalDateTime.now());
            // merchantDeal.setMerchantId(merchantId);
            merchantDeal.setDealTitle(webElement.findElement(By.tagName("h4")).getText());
            //merchantDeal.setNotes(webElement.findElement(By.tagName("h4")).getText());
            merchantDeal.setPrice(new BigDecimal(webElement.findElement(By.tagName("em")).getText()));
            merchantDeal.setStorePrice(new BigDecimal(webElement.findElement(By.tagName("del")).getText()));
            merchantDeal.setDianpingUrlId(urlId);
            merchantDeals.add(merchantDeal);
        }
        return merchantDeals;
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
    
	@GetMapping(value = "/proxy")
	@ResponseBody
	@ApiOperation(value = "代理测试", httpMethod = "GET")
	public String proxy() {
		//打开浏览器
        ChromeDriver webDriver= (ChromeDriver) BrowserUtils.openBrowserWithProxy(SysConstants.SysConfig.CHROMEDRIVER, SysConstants.SysConfig.CHROMEDRIVERPATH,"");
	     webDriver.get("http://www.dianping.com/");
	     try {
	                Thread.sleep(2000L);   
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	     return "xxx";
		
	}

}
