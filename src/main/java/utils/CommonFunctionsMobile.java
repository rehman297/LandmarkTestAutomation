package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonFunctionsMobile extends CommonFunctions {
    static AppiumDriver driver;
    WebDriverWait wait;


    @Override
    public void assignDriverObject() {
        driver=globalVars.getDriver();
    }

    @Override
    public boolean clickElement(WebElement element, int timeOutInSsec) {
        boolean isElementClicked=false;
        try{
            manageImplicitTimeOut(0);
            wait=new WebDriverWait(driver, timeOutInSsec);
            wait.until(ExpectedConditions.visibilityOf(element)).click();
            isElementClicked=true;
        }
        catch (Exception e) {
            logger.error("Exception occurred in clickElement method: "+e.getMessage());
            manageImplicitTimeOut(globalVars.getImplicitWait());
        }
        manageImplicitTimeOut(globalVars.getImplicitWait());
        Utils.logFunctionLevelLogs(isElementClicked, "clickElement");
        return isElementClicked;
    }

    @Override
    public boolean clickElement(WebElement element) {
        boolean isElementClicked=false;
        try{
            element.click();
            isElementClicked=true;
        }
        catch (Exception e) {
            logger.error("Exception occurred in clickElement method: "+e.getMessage());
        }
        Utils.logFunctionLevelLogs(isElementClicked, "clickElement");
        return isElementClicked;
    }

    @Override
    public void sendKey(WebElement element, String key) {
        try {
            element.clear();element.sendKeys(key);
            Utils.logFunctionLevelLogs(true, "sendKey");
        } catch (Exception e) {
            logger.error("Exception occurred in sendKey method: "+e.getMessage());
        }
    }

    @Override
    public void sendKey(WebElement element, String key, int timeOutInSsec) {
        try {
            manageImplicitTimeOut(0);//Setting the implicit wait as zero as implicit and explicit wait do not work together
            wait=new WebDriverWait(driver, timeOutInSsec);
            wait.until(ExpectedConditions.visibilityOf(element)).clear();element.sendKeys(key);
            Utils.logFunctionLevelLogs(true, "sendKey");
        } catch (Exception e) {
            logger.error("Exception occurred in sendKey method: "+e.getMessage());
            manageImplicitTimeOut(globalVars.getImplicitWait());
        }
        manageImplicitTimeOut(globalVars.getImplicitWait());
    }
    @Override
    public boolean isElementDisplayedByXpath(String xpath) {
        boolean isElementDisplayed=false;
        try {
            isElementDisplayed=driver.findElement(By.xpath(xpath)).isDisplayed();

        } catch (Exception e) {
            isElementDisplayed=false;
            logger.error("Exception occurred in isElementDisplayed method: "+e.getMessage());
        }
        Utils.logFunctionLevelLogs(isElementDisplayed, "isElementDisplayed");
        return isElementDisplayed;
    }

    @Override
    public boolean isElementDisplayed(WebElement element) {
        boolean isElementDisplayed=false;
        try {
            isElementDisplayed=element.isDisplayed();

        } catch (Exception e) {
            isElementDisplayed=false;
            logger.error("Exception occurred in isElementDisplayed method: "+e.getMessage());
        }
        Utils.logFunctionLevelLogs(isElementDisplayed, "isElementDisplayed");
        return isElementDisplayed;
    }

    @Override
    public boolean isElementDisplayed(WebElement element, int timeOutInSsec) {
        boolean isElementDisplayed=false;
        try {
            manageImplicitTimeOut(0);
            wait=new WebDriverWait(driver, timeOutInSsec);
            isElementDisplayed=wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

        } catch (Exception e) {
            isElementDisplayed=false;
            logger.error("Exception occurred in isElementDisplayed method: "+e.getMessage());
        }
        finally {
            manageImplicitTimeOut(globalVars.getImplicitWait());
        }
        Utils.logFunctionLevelLogs(isElementDisplayed, "isElementDisplayed");
        return isElementDisplayed;
    }

    @Override
    public String getElementText(WebElement element, int timeOutInSsec) {
        String text="";
        try {
            manageImplicitTimeOut(0);
            wait=new WebDriverWait(driver, timeOutInSsec);
            text=wait.until(ExpectedConditions.visibilityOf(element)).getText();

        } catch (Exception e) {
            logger.error("Exception occurred in getElementText method: "+e.getMessage());
            manageImplicitTimeOut(globalVars.getImplicitWait());
        }
        manageImplicitTimeOut(globalVars.getImplicitWait());
        logger.info("Element text found :"+ text);
        return text;
    }

    @Override
    public void manageImplicitTimeOut(long timeOutInSsec) {
        try {
            driver.manage().timeouts().implicitlyWait(timeOutInSsec, TimeUnit.SECONDS);//Setting the implicit wait as zero as implicit and explicit wait do not work together
        } catch (Exception e) {
            logger.error("Exception occurred in manageImplicitTimeOut method: "+e.getMessage());
        }
    }

}
