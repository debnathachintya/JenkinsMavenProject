package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	
	@BeforeTest
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test(dataProvider = "getData")
	public void setup(String userName, String Password) throws InterruptedException {
		driver.get("https://admin-demo.nopcommerce.com/login");
		Thread.sleep(2000);
		
		WebElement getEmail = driver.findElement(By.xpath("//input[@name='Email']"));
		WebElement getPassword = driver.findElement(By.xpath("//input[@class='password']"));
		
		getEmail.clear();
		getPassword.clear();
		
		Thread.sleep(2000);
		
		getEmail.sendKeys(userName);
		getPassword.sendKeys(Password);
		
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
	@DataProvider(name="getData")
	public Object[][] getMultipleData() {
		Object[][] obj = new Object[2][2];
		obj[0][0] = "sunnytest@123.com";
		obj[0][1] = "password123";
		obj[1][0] = "achintyatest@123.com";
		obj[1][1] = "password123";
		return obj;
	}
}