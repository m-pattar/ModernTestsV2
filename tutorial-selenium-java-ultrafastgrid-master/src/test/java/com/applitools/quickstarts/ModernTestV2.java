package com.applitools.quickstarts;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class ModernTestV2 {

	public static void main(String[] args) {
		// Create a new chrome web driver
		WebDriver webDriver = new ChromeDriver();

		// Create a runner with concurrency of 1
		VisualGridRunner runner = new VisualGridRunner(1);

		// Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
		Eyes eyes = new Eyes(runner);

		setUp(eyes);

		try {
			// Note to see visual bugs, run the test using the above URL for the 1st run.
			// but then change the above URL to https://demo.applitools.com/index_v2.html
			// (for the 2nd run)
			task1(webDriver, eyes);
			task2(webDriver, eyes);
			task3(webDriver, eyes);

		} finally {
			tearDown(webDriver, runner);
		}

	}

	public static void setUp(Eyes eyes) {

		// Initialize eyes Configuration
		Configuration config = new Configuration();

		// You can get your api key from the Applitools dashboard
		config.setApiKey("yo59i8y0Xpk3SW0qWCIyax9OSFoVDAiIAE1kbLh1kWQ110");

		// create a new batch info instance and set it to the configuration
		config.setBatch(new BatchInfo("UFG Hackathon"));
		
		config.addBrowser(1200, 700, BrowserType.CHROME);
		config.addBrowser(1200, 700, BrowserType.FIREFOX);
		config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
		config.addBrowser(768, 700, BrowserType.CHROME);
		config.addBrowser(768, 700, BrowserType.FIREFOX);
		config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);
		
		// Add mobile emulation devices in Portrait mode
		config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);

		// Set the configuration object to eyes
		eyes.setConfiguration(config);

	}


	public static void task1(WebDriver webDriver, Eyes eyes) {

		try {

			// Navigate to the url we want to test
			webDriver.get("https://demo.applitools.com/gridHackathonV2.html");

			// Call Open on eyes to initialize a test session
			eyes.open(webDriver, "Task 1", "Cross-Device Elements Test", new RectangleSize(1200, 700));

			// check the login page with fluent api, see more info here
			// https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html
			eyes.check(Target.window().fully().withName("V2 Application"));

//			webDriver.findElement(By.id("log-in")).click();

			// Check the app page
//			eyes.check(Target.window().fully().withName("V1 Application"));

			// Call Close on eyes to let the server know it should display the results
			eyes.closeAsync();

		} finally  {
			eyes.abortAsync();
		}

	}
	
	public static void task2(WebDriver webDriver, Eyes eyes) {

		try {

			// Navigate to the url we want to test
			webDriver.get("https://demo.applitools.com/gridHackathonV2.html");

			// Call Open on eyes to initialize a test session
			eyes.open(webDriver, "Task 2", "Filter Results", new RectangleSize(1200, 700));

			// check the login page with fluent api, see more info here
			// https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html
			

//			webDriver.findElement(By.id("log-in")).click();
			webDriver.findElement(By.cssSelector("input#colors__Black+span")).click();
			webDriver.findElement(By.cssSelector("button#filterBtn")).click();
			eyes.check(Target.window().fully().withName("V2 Application"));
	

			// Check the app page
//			eyes.check(Target.window().fully().withName("V1 Application"));

			// Call Close on eyes to let the server know it should display the results
			eyes.closeAsync();

		} finally  {
			eyes.abortAsync();
		}

	}
	
	public static void task3(WebDriver webDriver, Eyes eyes) {

		try {

			// Navigate to the url we want to test
			webDriver.get("https://demo.applitools.com/gridHackathonV2.html");

			// Call Open on eyes to initialize a test session
			eyes.open(webDriver, "Task 3", "Product Details test", new RectangleSize(1200, 700));

			// check the login page with fluent api, see more info here
			// https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html
			

//			webDriver.findElement(By.id("log-in")).click();
			webDriver.findElement(By.cssSelector("div.grid_item a")).click();
			eyes.check(Target.window().fully().withName("V2 Application"));
	

			// Check the app page
//			eyes.check(Target.window().fully().withName("V1 Application"));

			// Call Close on eyes to let the server know it should display the results
			eyes.closeAsync();

		} finally  {
			eyes.abortAsync();
		}

	}

	private static void tearDown(WebDriver webDriver, VisualGridRunner runner) {
		// Close the browser
		webDriver.quit();

		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = runner.getAllTestResults(false);
		System.out.println(allTestResults);
	}

}