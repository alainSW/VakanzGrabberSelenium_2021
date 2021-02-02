package de.alain.FreelancerDe.testCase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import de.alain.FreelancerDe.navigationUndProjektsinformationenHolen.NavigationUndProjektsinformationenHolen;
import de.alain.FreelancerDe.setup.SetUp;
import de.alain.FreelancerDe.sqliteDB.ExportProjektInformationenToSqlite;
import de.alain.FreelancerDe.utilities.DataUtil;
import de.alain.FreelancerDe.utils.ExtentListeners;

@Listeners({ de.alain.FreelancerDe.utils.ExtentListeners.class })
public class TestCases extends SetUp {
	private boolean isEmailVisible = false;

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void prozessLogikAbwicklung(Hashtable<String, String> data, Method m)
			throws InterruptedException, IOException {
		// login.visit(webSeite);

		ExtentListeners.test.log(Status.INFO, "<b>" + "Suchbegriff: " + data.get("suchebegriff") + "</b>");

		if (!isEmailVisible) {

			// Aktion: Browser-> Web Seite anklicken
			ExtentListeners.test.log(Status.INFO, "<b>" + "Oeffnen der Webseite : " + webSeite + "</b>");
			login.visit(webSeite);
			Thread.sleep(3000);

			// Cookies Handling
			// ExtentListeners.test.log(Status.INFO, "Cookies bestaetigen");
			cookiesHandling.iscookiesButtonExistAcceptButtonDruecken();

			// Aktion: Login
			/*
			 * ExtentListeners.test.log(Status.INFO, "Login-Button: Existenz pruefen ");
			 * login.isLoginButtonExist(); ExtentListeners.test.log(Status.PASS,
			 * "Login-Button existiert");
			 */

			login.loginButtonDruecken();
			login.isLoginMaskeExist(); // Es wird geprueft, ob die Maske-Login existiert
			login.typeMail(email); // User-Name//E-Mail eingeben
			login.typePasswort(passwort);// Passwort eingeben
			login.anmelden(); // Anmeldebutton druecken
			login.fortfahrenButtonDruecken();

		}

		// Es wird geprueft, ob man korrekt eingeloggt ist?
		isEmailVisible = login.isEmailVisible();

		Assert.assertTrue(isEmailVisible);

		if (isEmailVisible) {

			// Freelance.de Maske: Projekt finden
			// projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
			projektsuche.clickFreelanceDe();
			projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
			projektsuche.typeProjekt(data.get("suchebegriff"));// leiter // Testautomatisierer
			projektsuche.clickSuche();
			// Maske navigieren und Projektinformationen aussortieren
			NavigationUndProjektsinformationenHolen.projektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche,
					driver, projektmerkmale, data.get("suchebegriff"));

			ExportProjektInformationenToSqlite.textMethodeName(projektsuche.projektErgebnisse() + "_" + m.getName(),
					data.get("suchebegriff"), driver.getCurrentUrl().split("https://www.")[1].split("/")[0].toString());

			// Projektinformationen in der Datenbank uebertragen
			ExportProjektInformationenToSqlite.insertDataToSqileDB();

		}

	}

	/*
	 * @Test(dataProviderClass = DataUtil.class, dataProvider = "data") public void
	 * prozessLogikAbwicklungTestautomatisierung(Hashtable<String, String> data,
	 * Method m) throws InterruptedException, IOException { //
	 * login.visit(webSeite); ExtentListeners.test.log(Status.INFO, "<b>" +
	 * "Suchbegriff: " + data.get("suchebegriff") + "</b>");
	 * 
	 * if (!isEmailVisible) {
	 * 
	 * // Aktion: Browser-> Web Seite anklicken
	 * ExtentListeners.test.log(Status.INFO, "<b>" + "Oeffnen der Webseite : " +
	 * webSeite + "</b>"); login.visit(webSeite); Thread.sleep(3000);
	 * 
	 * // Cookies Handling // ExtentListeners.test.log(Status.INFO,
	 * "Cookies bestaetigen");
	 * cookiesHandling.iscookiesButtonExistAcceptButtonDruecken();
	 * 
	 * // Aktion: Login
	 * 
	 * ExtentListeners.test.log(Status.INFO, "Login-Button: Existenz pruefen ");
	 * login.isLoginButtonExist(); ExtentListeners.test.log(Status.PASS,
	 * "Login-Button existiert");
	 * 
	 * login.loginButtonDruecken(); login.isLoginMaskeExist(); // Es wird geprueft,
	 * ob die Maske-Login existiert login.typeMail(email); // User-Name//E-Mail
	 * eingeben login.typePasswort(passwort);// Passwort eingeben login.anmelden();
	 * // Anmeldebutton druecken login.fortfahrenButtonDruecken();
	 * 
	 * }
	 * 
	 * // Es wird geprueft, ob man korrekt eingeloggt ist? isEmailVisible =
	 * login.isEmailVisible();
	 * 
	 * Assert.assertTrue(isEmailVisible);
	 * 
	 * if (isEmailVisible) {
	 * 
	 * // Freelance.de Maske: Projekt finden //
	 * projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
	 * projektsuche.clickFreelanceDe();
	 * projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
	 * projektsuche.typeProjekt(data.get("suchebegriff"));// leiter //
	 * Testautomatisierer projektsuche.clickSuche(); // Maske navigieren und
	 * Projektinformationen aussortieren NavigationUndProjektsinformationenHolen.
	 * projektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche, driver,
	 * projektmerkmale, data.get("suchebegriff"));
	 * 
	 * ExportProjektInformationenToSqlite.textMethodeName(projektsuche.
	 * projektErgebnisse() + "_" + m.getName(), data.get("suchebegriff"),
	 * driver.getCurrentUrl().split("https://www.")[1].split("/")[0].toString());
	 * 
	 * // Projektinformationen in der Datenbank uebertragen
	 * ExportProjektInformationenToSqlite.insertDataToSqileDB();
	 * 
	 * }
	 * 
	 * }
	 */

}
