package com.kirck.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kirck.commen.NumberConstants;
import com.kirck.commen.constants.RedisConstants;
import com.kirck.commen.constants.SysConstants;
import com.kirck.commen.utils.UUIDUtils;
import com.kirck.entity.MerchantDeal;
import com.kirck.service.IDianPingService;
import com.kirck.utils.BrowserUtils;

@Component
public class JobTest {

    @Autowired
    private IDianPingService dianPingService;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0/15 * * * * *")
    public void job(){
        System.out.println("Job"+new Date());
    }

	@Scheduled(cron = "0 30 23,8 * * ?")
	public void job2() {
		// 打开浏览器
		ChromeDriver webDriver = (ChromeDriver) BrowserUtils.openBrowser(SysConstants.SysConfig.CHROMEDRIVER,
				SysConstants.SysConfig.CHROMEDRIVERPATH);
		// 设置缓存
		setCookie(webDriver);
		List<String> cityList = new ArrayList<String>();
		cityList.add("wuhan");
		cityList.add("shanghai");
		String url = "";
		for (String city : cityList) {
			List<MerchantDeal> merchantDeals = new ArrayList<MerchantDeal>();
			List<String> dealIds = new ArrayList<String>();
			int index = NumberConstants.ZERO;
			String lastDealPath = RedisConstants.KEYPRE.DIANPING+RedisConstants.KEYPRE.LIST+RedisConstants.KEYPRE.CITY + city + SysConstants.Symbol.COLON
					+ RedisConstants.OBJTYPE.CATEGORY + NumberConstants.ONE;
			// 查找最新的折扣信息记录
			String urlId = (String) redisTemplate.opsForValue().get(lastDealPath);
			while (true) {
				// "http://t.dianping.com/list/shanghai-category_1?desc=1&sort=new&pageIndex=0"
				url = SysConstants.SysConfig.DIANPINGLIST + SysConstants.Symbol.SLASH + city + SysConstants.Symbol.DASH
						+ SysConstants.SysConfig.CATEGORY + SysConstants.Symbol.UNDERLINE + "1"
						+ SysConstants.Symbol.STRING_QUESTION + SysConstants.SysConfig.NEWSORT + index++;

				// 跳转到最新信息链接
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

				// 解析团购信息
				List<MerchantDeal> circeMerchantDeals = parseDeal(webDriver, urlId!=null?urlId:"-1",dealIds);
				merchantDeals.addAll(circeMerchantDeals);
				if (circeMerchantDeals.size() != 40 || index == NumberConstants.TWELVE) {
					break;
				}
			}
			// 将第一条记录塞入缓存
			redisTemplate.opsForValue().set(lastDealPath, merchantDeals.get(0).getDianpingUrlId());
			Collections.reverse(merchantDeals);
			dianPingService.saveOrUpdate(merchantDeals);
		}

		BrowserUtils.closeBrowser(webDriver);

	}

	   /**
  * 解析团购信息
  * @param webDriver
  */
 private List<MerchantDeal> parseDeal(ChromeDriver webDriver,String lastUrlId,List<String> dealIds) {
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
         if(dealIds.contains(urlId)) {
        	 continue;
         }
         merchantDeal.setId(UUIDUtils.getNewId());
         merchantDeal.setPrice(new BigDecimal(webElement.findElement(By.tagName("em")).getText()));
         merchantDeal.setStorePrice(new BigDecimal(webElement.findElement(By.tagName("del")).getText()));
         merchantDeal.setDianpingUrlId(urlId);
         merchantDeals.add(merchantDeal);
         dealIds.add(urlId);
     }
     return merchantDeals;
 }

    @SuppressWarnings("unchecked")
	private void setCookie(ChromeDriver webDriver) {
        String cookiePath = RedisConstants.KEYPRE.DIANPING+RedisConstants.OBJTYPE.COOKIES + SysConstants.SysConfig.USERNAME;
        List<Map<String, Object>> cookies = null;
        while (cookies==null){
            //查找缓存
            cookies =  (List<Map<String, Object>>) redisTemplate.opsForValue().get(cookiePath);
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
