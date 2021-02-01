package de.alain.FreelancerDe.setup;

import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import de.alain.FreelancerDe.htmlControlProMaske.CookiesBearbeitung;
import de.alain.FreelancerDe.htmlControlProMaske.Login;
import de.alain.FreelancerDe.htmlControlProMaske.Projektmerkmale;
import de.alain.FreelancerDe.htmlControlProMaske.Projektsuche;
import de.alain.FreelancerDe.sqliteDB.SQLITEJDBC;
import de.alain.FreelancerDe.utilities.ExcelReader;
import de.alain.FreelancerDe.utils.ConfigProperties;
import de.alain.FreelancerDe.utils.ExtentListeners;

/*import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;*/

public class SetUp {

	public static ConfigProperties testEnvironment;

	/*
	 * public static ThreadLocal<ExtentTest> classLevelExtentTest = new
	 * ThreadLocal<ExtentTest>(); public static ThreadLocal<ExtentTest>
	 * testCaseLogger = new ThreadLocal<ExtentTest>();
	 */

	// public static ExtentReports extent=ExtentManager.GetExtent();
	/*
	 * public ExcelReader excel = new ExcelReader( System.getProperty("user.dir") +
	 * "\\src\\test\\resources\\testData\\simple.xlsx");
	 */
	public static String webSeite;
	public static String email;
	public static String passwort;
	public static String scheetName;

	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testDaten.xlsx");

	// Selenium
	public WebDriver driver;

	public Login login;

	public Projektsuche projektsuche;

	public Projektmerkmale projektmerkmale;

	public CookiesBearbeitung cookiesHandling;

	public SQLITEJDBC sqlite;

	public ExtentListeners dd;

	@BeforeTest
	public synchronized static void setUpFramework() {
		System.out.println("Setup Framework");
		ConfigFactory.setProperty("environment", "stage");
		testEnvironment = ConfigFactory.create(ConfigProperties.class);

		webSeite = testEnvironment.getWebSeite();
		email = testEnvironment.getEmail();
		passwort = testEnvironment.getPasswort();
		scheetName = testEnvironment.getSheetName();

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException, IOException {

		if (driver == null) {

			System.out.println("Oeffnen den Firefox");
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Executeable\\geckodriver.exe");

			driver = new FirefoxDriver();
			System.out.println("driver = new FirefoxDriver()");
			/*
			 * System.setProperty("webdriver.chrone.driver", System.getProperty("user.dir")
			 * + "\\src\\test\\resources\\Executeable\\chromedriver.exe"); driver = new
			 * ChromeDriver(); // driver.manage().deleteAllCookies(); ChromeOptions options
			 * = new ChromeOptions(); options.addArguments("--start-maximized"); driver =
			 * new ChromeDriver(options);
			 **/
			Thread.sleep(3000);

			login = new Login(driver);
			projektsuche = new Projektsuche(driver);
			projektmerkmale = new Projektmerkmale(driver);
			cookiesHandling = new CookiesBearbeitung(driver);

			// SQLITE-Datenbank Klasse instanzieren
			sqlite = new SQLITEJDBC();
			// vVerbindung zum Datanbank erstellen
			sqlite.sQLITEJDBCConnectToDatabase();
			// Tabelle erstellen
			sqlite.sQLITEJDBCCreateTable();
			sqlite.sQLITEJDBCDuplicateEntfenen();

			driver.manage().window().maximize();

		}
	}

	@AfterTest
	public void EndTest() throws InterruptedException {

		System.out.println("@AfterTest");
		Thread.sleep(3000);
		// driver.quit();

		// Query Select betätigen und Ergebnisse zeigen
		sqlite.sQLITEJDBCSelect();

	}

	/*
	 * @BeforeClass(alwaysRun = true) public void beforeClass() {
	 * 
	 * ExtentTest parent = extent.createTest(getClass().getSimpleName());
	 * 
	 * classLevelExtentTest.set(parent); }
	 * 
	 * @BeforeMethod(alwaysRun = true) public void beforeMethod(Object[]
	 * testArgs,Method method) {
	 * 
	 * ExtentTest child = classLevelExtentTest.get().createNode(method.getName());
	 * testCaseLogger.set(child);
	 * 
	 * testCaseLogger.get().log(Status.INFO, "Execution of Test case- "
	 * +method.getName()+ " started");
	 * 
	 * System.out.println("Size of Object Array in Before Method:"+testArgs.length);
	 * 
	 * Map<String,String> value=(Map<String,String>)testArgs[0];
	 * if(value.get("isValidKeyReq").equalsIgnoreCase("y")) {
	 * extentLogger().info("Valid Secret key passed");
	 * requestSpec=setRequestSpec(testEnvironment.getValidKey()); } else {
	 * extentLogger().info("InValid Secret key passed");
	 * requestSpec=setRequestSpec(testEnvironment.getInValidKey());
	 * 
	 * } //System.out.println("in before method--> "+testArgs.length);
	 * Map<String,String> paramMap = (Map<String, String>)testArgs[0];
	 * System.out.println("in before method--> "+paramMap);
	 * 
	 * // System.out.println("Execution of Test case:-" + method.getName() + " //
	 * started");
	 * 
	 * }
	 * 
	 * @AfterMethod(alwaysRun = true) public void afterMethod() {
	 * 
	 * extent.flush(); }
	 * 
	 * @AfterClass(alwaysRun = true) public void AfterClass() {
	 * 
	 * }
	 * 
	 * @AfterTest(alwaysRun = true) public void afterTest() {
	 * 
	 * }
	 * 
	 * @BeforeClass(alwaysRun = true) public void afterSuite() {
	 * 
	 * }
	 * 
	 * public static RequestSpecification setRequestSpec(String key) {
	 * 
	 * 
	 * return given().auth().basic(key, "").when();
	 * 
	 * }
	 * 
	 * public static ExtentTest extentLogger() { return testCaseLogger.get(); }
	 */

}
