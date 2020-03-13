package com.JSON.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

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
}
