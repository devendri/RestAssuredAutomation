package tests;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class TestAssignment {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	public void setup() {
		htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Test

	public void testcase_001() throws IOException {

		Response response=get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false"); 
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getHeader("content-type"));

		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		// creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.createTest("Test case_001", "This test case verify the response status code and content type");

        // log(Status, details)
        test.log(Status.INFO, "This step shows usage of log(status, details)");

        // info(details)
        test.info("This step shows usage of info(details)");
        
        // log with snapshot
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        
        // test with snapshot
        test.addScreenCaptureFromPath("screenshot.png");


	}
	@Test
	public void testcase_002() throws IOException {
		baseURI="https://api.tmsandbox.co.nz/v1";
		given().
		get("/Categories/6327/Details.json?catalogue=false").
		then().
		statusCode(200).
		body("Name", equalTo("Carbon credits"));
		
		 ExtentTest test = extent.createTest("Test case_002", "This test case verify the Name =Carbon credits");

	        // log(Status, details)
	        test.log(Status.INFO, "This step shows usage of log(status, details)");

	        // info(details)
	        test.info("This step shows usage of info(details)");
	        
	        // log with snapshot
	        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
	        
	        // test with snapshot
	        test.addScreenCaptureFromPath("screenshot.png");


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
	
	@AfterSuite
	public void teardown() {
		extent.flush();
	}
	
}

