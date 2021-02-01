package de.alain.FreelancerDe.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentTestManager {

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentReports extent = ExtentManager.GetExtent();
	public static ExtentTest test;

	public static synchronized ExtentTest getTestLogger() {

		return testReport.get();
	}

	public static void logInfo(String message) {

		test.log(Status.INFO, message);
		// testReport.get().info(message);
	}

	public static void logPass(String message) {
		test.log(Status.PASS, message);
		// testReport.get().pass(message);
	}

	public static void scenarioPass() {

		String passLogg = "SCENARIO PASSED";
		Markup m = MarkupHelper.createLabel(passLogg, ExtentColor.GREEN);
		testReport.get().log(Status.PASS, m);

	}

	public static void logFail(String message) {

		testReport.get().fail(message);
	}

	public static void logFatal(String message) {

		testReport.get().fatal(message);
	}

	/*
	 * public static synchronized boolean addScreenShotsOnFailure() {
	 * 
	 * ExtentManager.captureScreenshot(); try {
	 * 
	 * testReport.get().fail("<b>" + "<font color=" + "red>" +
	 * "Screenshot of failure" + "</font>" + "</b>",
	 * MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).
	 * build()); } catch (IOException e) {
	 * 
	 * }
	 * 
	 * String failureLogg = "SCENARIO FAILED"; Markup m =
	 * MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
	 * testReport.get().log(Status.FAIL, m); return true; }
	 */

	/*
	 * public static synchronized boolean addScreenShots() {
	 * 
	 * ExtentManager.captureScreenshot(); try { testReport.get().info(("<b>" +
	 * "<font color=" + "green>" + "Screenshot" + "</font>" + "</b>"),
	 * MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).
	 * build()); } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return true; }
	 */

	public static synchronized ExtentTest startTest(String testName) {
		System.out.println("Start Test");
		return startTest(testName, "");
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.createTest(testName, desc);
		testReport.set(test);
		return test;
	}

}
