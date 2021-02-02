/* Ersteller: Alain Samen Wnagueu
 * 
 * 
 * Beschreibung: Handling der Maske Login
 * 
 * Diese Klasse ermueglicht das Geschueftsprozesses der Login-Maske abzuwickeln.
 * 
 * Folgende Aktionen werden durchgefuehrt: 
 * 
 * - ueberpruefung der Existenz des Login-Buttons
 * - Login-Buttons druecken
 * - Logout-Buttons druecken
 * - ueberpruefung der Existenz des Email-Eingabefelders
 * - ueberpruefung der Existenz des Passwort-Eingabefelders
 * - ueberpruefung der Existenz des Checkbox eingeloggen Bleiben?
 * - ueberpruefung der Existenz des Anmelde-Buttons
 * - Email eingeben
 * - Passwort eingeben
 * - Passwort eingeben
 * - Checkbox eingeloggen Bleiben? abwuehlen
 *  
 *  
 * 
 * 
 */

package de.alain.FreelancerDe.htmlControlProMaske;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import de.alain.FreelancerDe.baseAktionen.BaseHtmlControl;
import de.alain.FreelancerDe.utils.ExtentListeners;

public class Login extends BaseHtmlControl {

	@FindBy(xpath = "//input[@id='username']")
	private WebElement email;
	@FindBy(xpath = "//body/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/label[1]")
	private WebElement emailEingabeFelderExist;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwort;
	@FindBy(xpath = "//div[@class= 'form-group  required']//label[@for='password'][contains(text(), 'Passwort')]")
	private WebElement passwortEingabeFelderExist;

	@FindBy(xpath = "//input[@id='remember']")
	private WebElement eingeloggenBleiben;
	@FindBy(xpath = "//body/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[5]/div[1]/div[1]/span[1]")
	private WebElement eingeloggenBleibenCheckboxExist;

	@FindBy(xpath = "//input[@id='login']")
	private WebElement anmelden;
	@FindBy(xpath = "//input[@id='login']")
	private WebElement anmeldeButtonExist;

	@FindBy(xpath = "//ul[@class='nav nav-pills hidden-xs']//a[@class='nav-top-link'][contains(text(),'Login')]")
	private WebElement loginButton;

	@FindBy(xpath = "//ul[@class='nav nav-pills hidden-xs']//a[@class='visible-lg-block'][contains(text(),'Willkommen alains..')]")
	private WebElement einloggenMessageVisible;

	@FindBy(xpath = "//ul[@class='nav nav-pills hidden-xs']//li[@class='dropdown hidewhen768 active']//ul[@class='dropdown-menu']//li//a[@class='nav-top-link'][contains(text(),'Logout')]")
	private WebElement logoutButton;

	@FindBy(xpath = "//div[@class= 'form-box-submit-area']//button[@class='btn btn-default pull-right'][contains(text(),'Fortfahren »')]")
	private WebElement fortfahrenButton;

	// Webdriver und Seite initialisieren
	public Login(WebDriver driver) {
		super(driver);
		// visit("https://www.freelance.de");
	}

	// Email-Adresse eingeben
	public void typeMail(String inputText) {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Anmeldename eingeben " + "</b>");

		if (!getLoginText().equals("Login")) {

			// ExtentTestManager
			// .logFatal("Der Login-Maske wird nicht angezeigt, deshalb kann es kein Email
			// angegeben werden");
			ExtentListeners.test.log(Status.FATAL, "<b>"
					+ "Der Login-Maske wird nicht angezeigt, deshalb kann es kein Email angegeben werden" + "</b>");

			return;
		} else {
			ExtentListeners.test.log(Status.INFO, "<b>" + "Es wird die Email-Adresse XXXXX@" + inputText.split("@")[1]
					+ " im Eingabefeld Nutzername/E-Mail eingeben");
			/*
			 * ExtentTestManager .logInfo("Es wird die Email-Adresse " + inputText +
			 * " im Eingabefeld <Nutzername/E-Mail> eingeben");
			 */
			type(email, 10, inputText);
			ExtentListeners.test.log(Status.PASS, "<b>" + "Anmeldename ist erfolgreich eingegeben." + "</b>");
		}

	}

	// EmailEingabeFelder Existenz pruefen
	public String EmailEingabeFelderExistenzPruefen() {

		return selectgetText(emailEingabeFelderExist, 10);
	}

	// Passwort eingeben
	public void typePasswort(String inputText) {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Passwort eingeben " + "</b>");
		if (!getLoginText().equals("Login")) {
			System.out.println("typePasswort Not Exist");
			ExtentListeners.test.log(Status.FATAL, "<b>"
					+ "Der Login-Maske wird nicht angezeigt, deshalb kann es kein Passwort angegeben werden" + "</b>");
			return;
		} else {
			ExtentListeners.test.log(Status.INFO,
					"<b>" + "Es wird das Passwort XXXX im Eingabefeld Passwort> eingeben" + "</b>");
			type(passwort, 10, inputText);
			ExtentListeners.test.log(Status.PASS, "<b>" + "Passwort ist erfolgreich eingegeben worden." + "</b>");
		}

	}

	// PasswortEingabeFelder Existenz pruefen
	public String PasswortEingabeFelderExistenzPruefen() {

		return selectgetText(passwortEingabeFelderExist, 10);
	}

	// Checkbox: nicht eingeloggt bleiben auswuehlen
	public void eingeloggtBleiben() {
		if (!getLoginText().equals("Login")) {
			System.out.println("eingeloggt Bleiben Checkbox Not Exist");
			return;
		} else {

			click(eingeloggenBleiben, 10);
		}

	}

	// eingeloggt bleiben Checkbox Existenz pruefen
	public String EingeloggenBleibenCheckboxExistenzPruefen() {

		return selectgetText(eingeloggenBleibenCheckboxExist, 10);
	}

	// Anmeldebutton druecken
	public void anmelden() {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Anmelde-Button druecken " + "</b>");
		if (!getLoginText().equals("Login")) {
			ExtentListeners.test.log(Status.FAIL, "<b>"
					+ "Der Login-Maske wird nicht angezeigt, deshalb kann der Anmelde-Button nicht gedrueckt werden"
					+ "</b>");
			return;
		} else {
			ExtentListeners.test.log(Status.INFO, "Der Anmeldebutton wird angedrueckt." + "</b>");
			click(anmelden, 10);
			ExtentListeners.test.log(Status.PASS, "<b>" + "Anmelde-Button ist erfolgreich gedrueckt worden." + "</b>");
		}

	}

	// AnmeldeButton Existenz pruefen
	public String anmeldeButtonExistenzPruefen() {

		return selectgetAttributByValue(anmeldeButtonExist, 10);
	}

	// Login-Button druecken
	public void loginButtonDruecken() {

		ExtentListeners.test.log(Status.INFO, "<b>" + "Login-Button: druecken" + "</b>");
		ExtentListeners.test.log(Status.INFO, "<b>" + "Es wird geprueft, ob der Login-Button exitiert" + "</b>");
		if (!getLoginText().equals("Login")) {
			ExtentListeners.test.log(Status.WARNING,
					"<b>" + "Login-Button existiert nicht, deshalb kann es nicht gedrueckt werden" + "</b>");
			return;
		} else {
			ExtentListeners.test.log(Status.INFO, "<b>" + "Login-Button wird gedrueckt" + "</b>");
			click(loginButton, 10);
			ExtentListeners.test.log(Status.PASS, "<b>" + "Login-Button ist erfolgreich gedrueckt worden. " + "</b>");
		}

	}

	// Textname: LoginButton entnehmen
	public String getLoginText() {

		return selectgetText(loginButton, 10);
	}

	// Ist man eingeloggt?
	public Boolean isEmailVisible() {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Pruefe, ob man eingeloggt ist." + "</b>");

		if (elementsIsVisible(einloggenMessageVisible, 10)) {
			ExtentListeners.test.log(Status.PASS, "<b>" + "man ist erfolgreich engeloggt." + "</b>");
			return true;
		}
		return false;
	}

	// Willkommen-Listenfeld druecken
	public void willkommenUser() {
		click(einloggenMessageVisible, 10);
	}

	// LogOut-Button druecken
	public void logoutButton() {
		click(logoutButton, 10);
	}

	// Ist man eingeloggt? Wenn Ja dann wird man ausgeloggt
	public void isLoginButtonExist() {
		new WebDriverWait(driver, 5);
		ExtentListeners.test.log(Status.INFO, "<b>" + "Es wird geprueft, ob der Login-Button exitiert" + "</b>");
		if (getLoginText().equals("Login")) {
			System.out.println("Login Exist!");
			ExtentListeners.test.log(Status.PASS, "<b>" + "Der Login-Button exitiert" + "</b>");
			// logoutButton();
			return;
		} else {
			System.out.println("Kein Login Exist!");
			ExtentListeners.test.log(Status.FAIL, "<b>" + "Der Login-Button exitiert nicht" + "</b>");
			return;
		}
	}

	// Es wird geprueft, ob die Maske Login geladen ist?
	public void isLoginMaskeExist() {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Login-Maske: Existenz pruefen " + "</b>");
		new WebDriverWait(driver, 5);
		ExtentListeners.test.log(Status.INFO,
				"<b>" + "Es wird geprueft, ob das Eingabefeld: Nutzername/E-Mail existiert." + "</b>");
		ExtentListeners.test.log(Status.INFO,
				"<b>" + "Es wird geprueft, ob das Eingabefeld: Passwort> existiert." + "</b>");
		ExtentListeners.test.log(Status.INFO,
				"<b>" + "Es wird geprueft, ob das Checkbox: Eingeloggt bleiben existiert." + "</b>");
		ExtentListeners.test.log(Status.INFO, "<b>" + "Es wird geprueft, ob der Button: Anmelde existiert." + "</b>");
		if (EmailEingabeFelderExistenzPruefen().equals("NUTZERNAME/E-MAIL")
				&& PasswortEingabeFelderExistenzPruefen().equals("PASSWORT")
				&& EingeloggenBleibenCheckboxExistenzPruefen().equals("Eingeloggt bleiben")
				&& anmeldeButtonExistenzPruefen().equals("Anmelden »")) {

			System.out.println("Login Maske existiert!");
			ExtentListeners.test.log(Status.PASS, "<b>" + "Login-Maske existiert!" + "</b>");

		} else {

			System.out.println("Login Maske existiert nicht!");
			ExtentListeners.test.log(Status.FAIL, "<b>" + "Loginmaske nicht existiert!" + "</b>");

		}

	}

	// Fortfahren-Button druecken
	public void fortfahrenButtonDruecken() {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Fortfahren-Button: druecken" + "</b>");
		ExtentListeners.test.log(Status.INFO, "<b>" + "Es wird geprueft, ob der Fortfahren-Button exitiert" + "</b>");
		if (!getFortfahrenText().equals("Fortfahren »")) {
			ExtentListeners.test.log(Status.WARNING,
					"<b>" + "Fortfahren-Button existiert nicht, deshalb kann es nicht gedrueckt werden" + "</b>");
			return;
		} else {
			ExtentListeners.test.log(Status.INFO, "<b>" + "Fortfahren-Button wird gedrueckt" + "</b>");
			click(fortfahrenButton, 3);
			ExtentListeners.test.log(Status.PASS,
					"<b>" + "Fortfahren-Button ist erfolgreich gedrueckt worden. " + "</b>");
		}

	}

	// Textname: LoginButton entnehmen
	public String getFortfahrenText() {
		System.out.println("getFortfahrenText   ------>   " + selectgetText(fortfahrenButton, 10));
		return selectgetText(fortfahrenButton, 3);
	}

}
