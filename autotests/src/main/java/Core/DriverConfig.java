package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public  class DriverConfig {
    public static WebDriver driver = null;
    public static String browserName = "chrome";

    public static WebDriver getDriver(){
        if (driver == null) {
            if(browserName.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
        }
        return driver;
    }

    public static void initialize() {
        getDriver();
        driver.manage().window().maximize();
    }

    public static void quit(){
        System.out.println("quitting the browser");
        driver.quit();
        driver = null;
    }
}
