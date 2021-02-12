package de.alain.FreelancerDe.htmlControlProMaske;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import de.alain.FreelancerDe.baseAktionen.BaseHtmlControl;
import de.alain.FreelancerDe.utils.ExtentListeners;

public class CookiesBearbeitung extends BaseHtmlControl {

	public CookiesBearbeitung(WebDriver driver) {

		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath =
	// "//div[@id='CybotCookiebotDialogBody']//div[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelectionWrapper']//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection'][contains(text(),'Auswahl
	// bestätigen')]")
	// private WebElement cookiesButton;
	// @FindBy(xpath =
	// "//div[@id='CybotCookiebotDialogBody']//div[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelectionWrapper']//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection'][contains(text(),'Auswahl
	// bestätigen')]")
	// private WebElement cookiesButtonExist;

	@FindBy(xpath = "//body[1]/div[1]/div[1]/a[1]")
	private WebElement cookiesButton;
	@FindBy(xpath = "//body[1]/div[1]/div[1]/a[1]")
	private WebElement cookiesButtonExist;

	// cookiesButtoneingeben
	public void cookiesButton() {
		ExtentListeners.test.log(Status.INFO, "<b>" + "Cookies-Button wird gedrueckt" + "</b>");
		click(cookiesButton, 10);
	}

	// cookiesButton Existenz prï¿½fen
	public String cookiesButtonExistPruefen() {
		// System.out.println(selectgetText(cookiesButtonExist, 10));

		ExtentListeners.test.log(Status.INFO, "<b>" + "Es wird geprueft, ob der Cookies Button existiert" + "</b>");
		return selectgetText(cookiesButtonExist, 10);
	}

	// Cookies Handling
	public void iscookiesButtonExistAcceptButtonDruecken() {

		ExtentListeners.test.log(Status.INFO, "<b>" + "Cookies bestaetigen" + "</b>");

		new WebDriverWait(driver, 3);

		if (cookiesButtonExistPruefen().equals("Okay, verstanden »")) {
			System.out.println("Cookies-Button existiert");
			ExtentListeners.test.log(Status.PASS, "<b>" + "Cookies-Button existiert" + "</b>");
			cookiesButton();
			System.out.println("Cookies-Button ist erolgreich betaetigt worden");
			ExtentListeners.test.log(Status.PASS, "<b>" + "Cookies ist erfolgreich betaetigt worden." + "</b>");
		} else {
			System.out.println("Cookies existiert nicht");
			ExtentListeners.test.log(Status.INFO, "<b>" + "Cookies-Meldung existiert nicht" + "</b>");
		}
	}
}
