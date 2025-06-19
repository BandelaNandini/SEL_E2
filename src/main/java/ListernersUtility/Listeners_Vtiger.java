package ListernersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.Baseclass;

public class Listeners_Vtiger implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentTest test;
	public static ExtentReports report;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("On Suite Execution started", true);
		Reporter.log("Configure the Report", true);
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");

		// Configure the Report
		spark = new ExtentSparkReporter("./AdvancedReport/Report" + timestamp + ".html");
		spark.config().setDocumentTitle("VTiger CRM appln");
		spark.config().setReportName("VTiger");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Browser", "Chrome-137");

	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("On Suite Execution finished", true);
		Reporter.log("Report Backup", true);
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("On Test Execution started", true);
		String testname = result.getMethod().getMethodName();
		test = report.createTest(testname);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, "Test method execution started <==" + testname + "==>");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("Test Execution Success", true);
		String testname = result.getMethod().getMethodName();
		test.log(Status.INFO, "Test method execution successful <==" + testname + "==>");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("Test Execution Failed", true);
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");

		// Take screenshot
		TakesScreenshot ts = (TakesScreenshot) Baseclass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src + "" + testname + "_" + timestamp + "");
		test.log(Status.INFO, "Test method execution Failed <==" + testname + "==>");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test Execution skipped", true);
		String testname = result.getMethod().getMethodName();
		test.log(Status.INFO, "Test method execution skipped <==" + testname + "==>");

	}

}
