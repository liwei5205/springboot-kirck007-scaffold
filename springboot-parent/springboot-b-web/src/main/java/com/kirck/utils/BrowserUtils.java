package com.kirck.utils;

import com.kirck.commen.constants.SysConstants;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {
    private static ChromeDriver browser;


    public static WebDriver openBrowser(String chromedriver, String chromedriverpath) {
        System.getProperties().setProperty(chromedriver,chromedriverpath);
        browser = new ChromeDriver();
        //等待
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        return browser;
    }

    public static List<Map<String, Object>> loginDianPing(ChromeDriver webDriver, String username, String password) {
        webDriver.get(SysConstants.SysConfig.DIANPINGLOGINURL);
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
        return list;
    }

    public static void closeBrowser(ChromeDriver webDriver) {
        webDriver.close();
    }

	public static ChromeDriver openBrowserWithProxy(String chromedriver, String chromedriverpath,String proxyIpAndPort) {
		System.getProperties().setProperty(chromedriver,chromedriverpath);
        Proxy proxy = new Proxy();
		proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort ).setSslProxy(proxyIpAndPort);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
		cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
		cap.setCapability(CapabilityType.PROXY, proxy);
		ChromeOptions chromeOptions = new ChromeOptions().merge(cap);
        browser = new ChromeDriver(chromeOptions);
        //等待
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        return browser;
	}
}
