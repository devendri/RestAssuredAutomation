package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestAssignment {
	static Properties prop= new Properties();

	public static String base;

	@BeforeTest
	public  void getproperties() throws IOException {
		FileInputStream input= new FileInputStream("C:/Users/dvk/eclipse-workspace/RestAssuredAutomation/src/test/java/config/config.properties");
		
		prop.load(input);
		base=prop.getProperty("BaseURI");


	}


	@Test
	public void testcase_001() throws IOException {

		Response response=get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false"); 


		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String contenttype= response.getHeader("content-type");

		Assert.assertEquals(contenttype, "application/json");

	}

	@Test
	public void testcase_002() {

		baseURI=base;
		given().
		get("/Categories/6327/Details.json?catalogue=false").
		then().
		statusCode(200).
		body("Name", equalTo("Carbon credits"));

	}

	@Test
	public void testcase_003() {

		baseURI=base;
		given().
		get("/Categories/6327/Details.json?catalogue=false").
		then().
		statusCode(200).
		body("CanRelist", equalTo(true));

	}
	@Test
	public void testcase_004() {

		baseURI=base;
		given().
		get("/Categories/6327/Details.json?catalogue=false").
		then().
		statusCode(200).and().body("Promotions[1].Description", equalTo("Good position in category"));

	}



}

