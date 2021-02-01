package de.alain.FreelancerDe.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import de.alain.FreelancerDe.baseAktionen.BaseHtmlControl;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();

	public static ExtentReports extent = ExtentManager.GetExtent();

	// public static ThreadLocal<ExtentTest> testReport = new
	// ThreadLocal<ExtentTest>();
	public static ExtentTest test;

	static String messageBody;

	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart  " + result.getTestClass().getName());
		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		// testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		// testReport.get().pass(m);

		test.log(Status.PASS, m);

	}

	public void onTestFailure(ITestResult result) {
		System.out.println(result.getThrowable());
		// testReport.get().fail(result.getThrowable().getMessage().toString());
		test.log(Status.FAIL, result.getThrowable().getMessage());
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		/*
		 * testReport.get() .fail("<details>" + "<summary>" + "<b>" + "<font color=" +
		 * "red>" + "Exception Occured:Click to see" + "</font>" + "</b >" +
		 * "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>" +
		 * " \n");
		 */
		test.log(Status.FAIL,
				"<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		String screenShotPath = getScreenshot(BaseHtmlControl.driver, result.getName());
		try {

			test.log(Status.FAIL, "<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (IOException e) {

		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		// testReport.get().log(Status.FAIL, m);
		test.log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		// testReport.get().skip(m);
		test.log(Status.SKIP, m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		System.out.println("TestCase starten");
	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {

		/*
		 * try { messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+
		 * ":8080/job/APITestingFramework/Extent_20Reports/"+fileName; } catch
		 * (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * MonitoringMail mail = new MonitoringMail(); try {
		 * mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
		 * TestConfig.subject, messageBody); } catch (AddressException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (MessagingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

	public static String getScreenshot(WebDriver driver, String screenshotName) {
		// TakesScreenshot: Gibt einen Treiber an, der einen Screenshot erfassen und auf
		// unterschiedliche Weise speichern kann.
		TakesScreenshot ts = (TakesScreenshot) driver;

		// getScreenshotAs: Erfassen Sie den Screenshot, und speichern Sie ihn am
		// angegebenen Speicherort.

		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshot/" + screenshotName + "_"
				+ System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);

		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
