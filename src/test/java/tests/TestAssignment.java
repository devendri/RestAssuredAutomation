package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestAssignment {
	@Test

	public void testcase_001() {
		
		Response response=get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false"); 
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		
	}
	@Test
public void testcase_002() {
	baseURI="https://api.tmsandbox.co.nz/v1";
	given().
		get("/Categories/6327/Details.json?catalogue=false").
	then().
		statusCode(200).
		body("Name", equalTo("Carbon credits"));
		
		
	}
	
	@Test
public void testcase_003() {
	baseURI="https://api.tmsandbox.co.nz/v1";
	given().
		get("/Categories/6327/Details.json?catalogue=false").
	then().
		statusCode(200).
		body("CanRelist", equalTo(true));
	
}
	@Test
public void testcase_004() {
	
	baseURI="https://api.tmsandbox.co.nz/v1";
	given().
		get("/Categories/6327/Details.json?catalogue=false").
	then().
		statusCode(200).
		body("Promotions[1].Name", equalTo("Gallery")).and().body("Promotions[1].Description", equalTo("Good position in category"));
	
}
}

