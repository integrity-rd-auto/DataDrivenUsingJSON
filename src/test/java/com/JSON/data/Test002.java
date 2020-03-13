package com.JSON.data;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test002 {

	public static WebDriver driver;

	@BeforeMethod

	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/mercuryregister.php");

	}

	@DataProvider

	public static String[] readJson() throws IOException, ParseException {

		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\JSON\\data\\JsonFile.json");

		JSONParser jsonparser = new JSONParser();
		Object obj = jsonparser.parse(reader);
		JSONObject jsonobj = (JSONObject) obj;
		JSONArray array = (JSONArray) jsonobj.get("Registration Data");

		String[] arr = new String[array.size()];

		for (int i = 0; i < array.size(); i++) {

			JSONObject jsonarryobj = (JSONObject) array.get(i);

			String FirstName = (String) jsonarryobj.get("FirstName");

			String LastName = (String) jsonarryobj.get("LastName");

			arr[i]=FirstName+","+LastName;

		}

		return arr;

	}

	@Test(dataProvider = "readJson")
	public void registration(String data) {
		
		String users[]=data.split(",");

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(users[0]);

		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(users[1]);

	}

	

}
