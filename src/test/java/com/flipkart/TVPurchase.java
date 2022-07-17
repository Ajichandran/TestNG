package com.flipkart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TVPurchase {
	@DataProvider(name="Tv")
	public Object[][] moblieName() {
		return new Object[][] {{"Sony Tv"}};
	}
	static WebDriver driver;
	static long start;
	@Parameters("URL")
	@BeforeClass(groups="default")
	public static void browserLunch() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
			driver.get("https://www.flipkart.com");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("browser Launch");
	}
	@AfterClass
	public static void browserQuit() {
		
		System.out.println("browser Quit");
	}
	@BeforeMethod
	public void startingTime() {
		 start = System.currentTimeMillis();
		System.out.println("Before");
		

	}
	@AfterMethod
	public void endTiming() {
		long end = System.currentTimeMillis();
		System.out.println("After Time is taken :"+(end-start)); 
	}
	@Test(priority=1,groups="smoke")
	public void method1() {
		 WebElement closebtn = driver.findElement(By.xpath("//button[text()='✕']"));
		  closebtn.click();
		System.out.println("method1 is closed");

	}
	@Test(priority=2,groups="smoke")
	public void method2() {
		driver.findElement(By.name("q")).sendKeys(" Sony Tv");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("method2 is done");
	}
	@Test(priority=3,groups="smoke")
	public void method3() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement tv = driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
		String s = tv.getText();
		System.out.println(s);
		File f = new File("C:\\Users\\srither\\Desktop\\ajii\\FrameWork\\target\\Sample.xlsx");
		Workbook w = new HSSFWorkbook();
		Sheet sh = w.createSheet("Flipakart");
		Row r = sh.createRow(0);
		Cell c = r.createCell(1);
		FileOutputStream f1 = new FileOutputStream(f);
		w.write(f1);
		System.out.println("method3 is done");


	}
	@Test(priority=4,groups="default")
		public void method4() throws Exception {
		String pwn = driver.getWindowHandle();
		Set<String> allwn = driver.getWindowHandles();
		for (String x: allwn) {
			if(!x.equals(pwn)) {
				driver.switchTo().window(pwn);
			}
		}
				 System.out.println("method 4 is done");
			}


	
	@Test(priority=5,groups="default")
	public void method5() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TakesScreenshot tk = (TakesScreenshot)driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\srither\\Desktop\\ajii\\TestNG\\Screenshot//flipkart.png");
		FileUtils.copyFile(source, target);
		System.out.println(" method 5 screenshot");
		
		
	

		}


	}

