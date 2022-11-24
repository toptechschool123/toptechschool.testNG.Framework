package tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.BaseClass;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utils.DriverUtility;

public class Listeners extends BaseClass implements ITestListener{

	
	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";
	
	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_FOLDER);
		
		if(!Files.exists(path)) {
		try {
			Files.createDirectories(path);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter (OUTPUT_FOLDER +FILE_NAME );
		
		sparkReporter.config().setDocumentTitle("Automation Test Result");
		sparkReporter.config().setReportName("Automation Test Result");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setReportUsesManualConfiguration(true);
		
		return extent;
	}
	
	@Override
	public void onTestStart(ITestResult result) {
	logger.info("test execution started");
	String methodName = result.getMethod().getMethodName();
	
	System.out.println(methodName +"started!");
	ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
	result.getMethod().getDescription());
	
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		logger.info("test passed successfully");
		logger.info("method name: " + result.getMethod().getMethodName());
		//test.get().pass("test passed");
		//test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("test failed");
		logger.info("method name : " + result.getMethod().getMethodName());
		test.get().info("test failed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		DriverUtility.screenShot();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped!");
		logger.info("method name" + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	logger.info("method name " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		logger.info("method name " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("test execution started");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info(" end of execution");
		extent.flush();
		test.remove();
	}
	
	private Date getTime(long milli) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milli);
		return calendar.getTime();
	}


	}
	


