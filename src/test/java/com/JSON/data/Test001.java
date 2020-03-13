package com.JSON.data;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test001 {

	public static WebDriver driver;

	@BeforeTest

	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/mercuryregister.php");

	}

	@AfterTest

	public void tearDown() {

		driver.quit();
	}

	@DataProvider

	public static String[] readJson() throws IOException, ParseException {

		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\JSON\\data\\JsonFile.json");

		JSONParser jsonparser = new JSONParser();
		Object obj = jsonparser.parse(reader);
		JSONObject jsonobj = (JSONObject) obj;
		String s = (String) jsonobj.get("firstName");

		JSONArray array = (JSONArray) jsonobj.get("Registration Data");

		String[] arr = new String[array.size()];

		for (int i = 0; i < array.size(); i++) {

			JSONObject jsonarryobj = (JSONObject) array.get(i);

			String FirstName = (String) jsonarryobj.get("FirstName");

			String LastName = (String) jsonarryobj.get("LastName");

			String Phone = (String) jsonarryobj.get("Phone");

			String Email = (String) jsonarryobj.get("Email");

			String Address = (String) jsonarryobj.get("Address");

			String City = (String) jsonarryobj.get("City");

			String Province = (String) jsonarryobj.get("Province");

			String PostalCode = (String) jsonarryobj.get("Postal Code");

			String Country = (String) jsonarryobj.get("Country");

			String UserID = (String) jsonarryobj.get("UserID");

			String Password = (String) jsonarryobj.get("Password");

			String ConPassword = (String) jsonarryobj.get("ConPassword");

			arr[i] = FirstName + "," + LastName + "," + Phone + "," + Email + "," + Address + "," + City + ","
					+ Province + "," + PostalCode + "," + Country + "," + UserID + "," + Password + "," + ConPassword;

			System.out.println(arr[i]);

		}

		return arr;

	}

	@Test(dataProvider = "readJson")
	public void registration(String data) {

		String[] user = data.split(",");

		readData(user[0], user[1], user[2], user[3], user[4], user[5], user[6], user[7], user[8], user[9], user[10],
				user[11]);

		openRegistration();

	}

	public static void readData(String FirstName, String LastName, String Phone, String Email, String Address,
			String City, String Province, String PostalCode, String Country, String UserID, String Password,
			String ConPassword) {

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(FirstName);

		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(LastName);

		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(Phone);

		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(Email);

		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(Address);

		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(City);

		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(Province);

		driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(PostalCode);

		driver.findElement(By.xpath("//select[@name='country']")).sendKeys(Country);

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(UserID);

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);

		driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(ConPassword);

		WebElement ele = driver.findElement(By.xpath("//input[@name='register']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", ele);

	}

	public static void openRegistration() {

		driver.findElement(By.xpath("//a[contains(text(),'REGISTER')]")).click();
	}

}
