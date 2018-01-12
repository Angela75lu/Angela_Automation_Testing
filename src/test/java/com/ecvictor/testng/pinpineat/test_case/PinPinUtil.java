package com.ecvictor.testng.pinpineat.test_case;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.testng.Assert.fail;

public class PinPinUtil {
    public WebDriver driver;
    public String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell=null;
    private static XSSFRow Row=null;
    public final int TIME_OUT = 5000;
    public final int INTERVAL = 500;

    public void setUp() throws Exception {
        // the following is the get the browsername from config.properties file
        Properties prop = getProperties();
        String browserType = prop.getProperty("browserName");

        // the following is get the browser from the pom.xml
        //  String browserType = System.getProperty("browser");
        // System.out.println("Current Browser Type is " + browserType);
        if(browserType.equals("chrome")) {

            String os = (System.getProperty("os.name"));

            if (os.equalsIgnoreCase("Mac OS X"))
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
            else System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--kiosk");
            driver = new ChromeDriver(chromeOptions);

        }
        else if(browserType.equals("Firefox")) {
            // System.setProperty("webdriver.gecko.driver", "E:\\Automation_Testing\\pinpineat_test\\download\\geckodriver-v0.19.1-win64\\geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            driver=new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        baseUrl = "http://www.pinpineat.com";
        driver.get(baseUrl + "/");
    }

    public static Properties getProperties() {
        //load properties from the config file
        String resourceName = "config.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try {
            InputStream resourceStream = loader.getResourceAsStream(resourceName);
            prop.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
   // read the registration informaton from a excel file
    public static String[][] getTableArray(String FilePath, String SheetName) throws Exception{
        String[][] tabArray = null;

        try {

            FileInputStream ExcelFile = new FileInputStream(FilePath);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startRow = 1;
            int startCol = 0;
            int ci,cj;

            int totalRows = ExcelWSheet.getLastRowNum();
            // you can write a function as well to get Column count

            int totalCols = 4;
            tabArray=new String[totalRows][totalCols];
            ci=0;
            for (int i=startRow;i<=totalRows;i++, ci++) {
                cj=0;
                Row = ExcelWSheet.getRow(i);
                for (int j=startCol;j<totalCols;j++, cj++){
                    Cell = Row.getCell(j);
                    if(Cell!=null) {
                        tabArray[ci][cj] = ExcelWSheet.getRow(i).getCell(j).toString();
                        System.out.println(tabArray[ci][cj]);
                    }
                    else
                        tabArray[ci][cj] = null;
                }
            }

        }

        catch (FileNotFoundException e){

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        catch (IOException e){

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        return(tabArray);
    }

    public void tearDown() throws Exception {
            driver.close();
            //Firefox doesn't work for driver.quit();
            //driver.quit()
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
    }

    public boolean validateLink(WebElement webElement, String str)  {
        int timeout = TIME_OUT;
        boolean success = false;
        while (timeout > 0) {
            String name = webElement.getText();
            if (name.equals(str)) {
                System.out.println(str + " link found." + timeout);
                return true;
            } else {
                System.out.println("Not found " + str + " wait 500 ms");
                try {
                    Thread.sleep(INTERVAL);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeout -= INTERVAL;
            }
        }
        if (timeout <= 0)
            Assert.fail("Test failed");
        return false;

    }
    public static boolean isAttributePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {

            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}

        return result;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForPageLoad() {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    public static void clickWhenReady(String link, int timeout,WebDriver driver) {

        WebElement element;

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(link)));

        element.click();

    }

    //populate sign up form

    public static void elmentIsVisible(String link, int timeout, WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(link)));

    }
    public static boolean isClickable(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
