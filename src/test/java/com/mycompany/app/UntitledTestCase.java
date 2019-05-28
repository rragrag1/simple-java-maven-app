package com.mycompany.app;

import java.util.regex.Pattern;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UntitledTestCase {
  public WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  
	  //if you're going to use more than one OS, you should make this switchable based on OS.
     // Path path = FileSystems.getDefault().getPath("/Users/admin/Downloads/geckodriver");
      String exePath = "/Users/admin/Documents/Devops/pipeline/Selenium/WebDrivers/geckodriver";

      
      System.setProperty("webdriver.gecko.driver",exePath);
    
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  

  }

  @Test
  public void testUntitledTestCase() throws Exception {
   
    driver.get("https://www.google.com/");
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("demo");
    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Résultats Web'])[1]/following::h3[1]")).click();
    Thread.sleep(5000);

    System.out.println("page Title: "+ driver.getTitle());
    Thread.sleep(5000);

       }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
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
