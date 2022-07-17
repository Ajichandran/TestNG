package com.flipkart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePurchase {
	@DataProvider(name="mobile")
	public Object[][] moblieName() {
		return new Object[][] {{"Redmi Mobile"}};
	}
	
	static  WebDriver driver ;
	static long start;
	@BeforeClass
	public static void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println("Browser launch");
		

	}
	@AfterClass
	public static void browserQuit() {
		System.out.println("browser quit");
	}
	@BeforeMethod
	public void startingTime() {
		System.out.println("before method");
		start = System.currentTimeMillis();
	}
	@AfterMethod
	public void endTime() {
	
		long end = System.currentTimeMillis();
		System.out.println("after method time is taken:"+(end - start));
	}
	@Test(priority=1,groups="smoke")
	public void login() {
		 WebElement closebtn = driver.findElement(By.xpath("//button[text()='✕']"));
		  closebtn.click();
	System.out.println("login");

	}
	
		
	@Test(priority=2,groups="smoke")
	public void search() {
		WebElement search = driver.findElement(By.name("q"));
         search.sendKeys("Redmi Mobile");
 		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("search");

	}
	@Test(priority=3)
	public void mobile() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement redmi = driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
		String s = redmi.getText();
		System.out.println(s);
		redmi.click();
		System.out.println("mobile choose");
		File f = new File("C:\\Users\\srither\\Desktop\\ajii\\FrameWork\\target\\Sample.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet sheet= w.createSheet("Flipkart");
		Row r = sheet.createRow(0);
	    Cell c = r.createCell(0);
	    FileOutputStream f1 = new FileOutputStream(f);
	    w.write(f1);
	}

	
	@Test(priority=4)
	public void window() {
		String pwn = driver.getWindowHandle();
		Set<String> allwn = driver.getWindowHandles();
		for (String x: allwn) {
			if(!x.equals(pwn)) {
				driver.switchTo().window(pwn);
			}
		}
		System.out.println("window");

	}
	@Test(priority=5)
	public void screenshot() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TakesScreenshot tk = (TakesScreenshot)driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\srither\\Desktop\\ajii\\TestNG\\Screenshot\\flipkart.png");
		FileUtils.copyFile(source, target);
		System.out.println("screenshot");

	}
	}
	

	


