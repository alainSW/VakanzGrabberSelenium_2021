
/* Ersteller: Alain Samen Wnagueu
 * 
 * 
 * Projektmerkmale:
 * 
 * Diese Klasse erm�glicht uns die Projektmerkmale bzw. Projektinformamationen je Projekt zu gewinnen.
 * 
 * Es handelt sich um folgende Informatiomationen:
 * 
 * - Projekttitle
 * - Geplanter Start
 * - Voraussichtliches Ende
 * - Projekt Ort
 * - Stunden Satz
 * - Letzte Update
 * - Remote Arbeit
 * - Refenrenz Nummer
 * - projektbeschreibung. 
 * 
 * 
 * Es werden neben die Projektmerkmale in dieser Klasse auch Originalen Projekt-Title gewonnen.
 * 
 */

package de.alain.FreelancerDe.htmlControlProMaske;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.alain.FreelancerDe.baseAktionen.BaseHtmlControl;

public class Projektmerkmale extends BaseHtmlControl {

	@FindBy(xpath = "//div[@class='panel-body project-header panel-white']//h1")
	private static WebElement ProjektTitle;

	// GeplanterStart
	@FindBy(xpath = "//div[@class='row']//div[1]//p[1]")
	// @FindBy(xpath = "//i[@class='fa fa-sign-in']")
	private static WebElement GeplanterStart;

	// VoraussichtlichesEnde
	@FindBy(xpath = "//div[@class='row']//div[1]//p[2]")
	// @FindBy(xpath = "//i[@class='fa fa-sign-out']")
	private static WebElement VoraussichtlichesEnde;

	// projektOrt
	@FindBy(xpath = "//div[@class='row']//div[1]//p[3]")
	// @FindBy(xpath = "//i[@class='fa fa-globe']")
	private static WebElement ProjektOrt;

	// stundenSatz
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//p[1]")
	// @FindBy(xpath = "//i[@class='fa fa-money']")
	private static WebElement StundenSatz;

	// letzteUpdate
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//p[3]")
	// @FindBy(xpath = "//i[@class='fa fa-refresh']")
	private static WebElement LetzteUpdate;

	// Remote
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//p[2]")
	// @FindBy(xpath = "//i[@class='fa fa-home']")
	private static WebElement RemoteArbeit;

	// RefenrenzNummer
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//p[4]")
	// @FindBy(xpath = "//i[@class='fa fa-tag']")
	private static WebElement RefenrenzNummer;

	// projektbeschreibung
	@FindBy(xpath = "//div[@id='project_container']//div[@class='panel panel-default panel-white']")
	private static WebElement projektbeschreibung;

	// originalTitle_ Geplanter Start
	@FindBy(xpath = "//div[@class='project-right-content']//div[1]//i[@class='far fa-calendar-star']")
	private static WebElement OriginalTitle_GeplanterStart;

	// originalTitle_ VoraussichtlichesEnde
	@FindBy(xpath = "//div[@class='project-right-content']//div[1]//i[@class='far fa-calendar-times']")
	private static WebElement OriginalTitle_VoraussichtlichesEnde;

	// originalTitle_ ProjektOrt
	@FindBy(xpath = "//div[@class='project-right-content']//div[1]//i[@class='far fa-map-marker-alt']")
	private static WebElement OriginalTitle_ProjektOrt;

	// originalTitle_ StundenSatz
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//i[@class='far fa-coins']")
	private static WebElement OriginalTitle_StundenSatz;

	// originalTitle_ LetzteUpdate
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//i[@class='far fa-history']")
	private static WebElement OriginalTitle_LetzteUpdate;

	// originalTitle_ Remote
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//i[@class='far fa-home-alt']")
	private static WebElement OriginalTitle_Remote;

	// originalTitle_ RefenrenzNummer
	@FindBy(xpath = "//div[@class='project-right-content']//div[2]//i[@class='far fa-tag']")
	private static WebElement OriginalTitle_RefenrenzNummer;

	public Projektmerkmale(WebDriver driver) {
		super(driver);
	}

	// *************************************************************************************************************
	// Gruppe 1: Informtionen, die in jedem Projekt behinhaltet sind
	//
	// *************************************************************************************************************

	public static String projektTitle() {
		return selectgetText(ProjektTitle, 0);
	}

	public static String geplanterStart() {
		return selectgetText(GeplanterStart, 0);
	}

	public static String voraussichtlichesEnde() {
		return selectgetText(VoraussichtlichesEnde, 0);
	}

	public static String projektOrt() {
		return selectgetText(ProjektOrt, 0);
	}

	public static String stundenSatz() {
		return selectgetText(StundenSatz, 0);
	}

	public static String letzteUpdate() {
		return selectgetText(LetzteUpdate, 0);
	}

	public static String remote() {
		return selectgetText(RemoteArbeit, 0);
	}

	public static String refenrenzNummer() {
		return selectgetText(RefenrenzNummer, 0);
	}

	public static String projektbeschreibung() {
		return selectgetText(projektbeschreibung, 0);
	}

	// *************************************************************************************************************
	// Gruppe 2: Origniale Projekt-Title
	//
	// *************************************************************************************************************

	public static String originalTitle_GeplanterStart() {

		return selectgetAttribuDataOriginal(OriginalTitle_GeplanterStart, 0);
	}

	public static String originalTitle_VoraussichtlichesEnde() {
		return selectgetAttribuDataOriginal(OriginalTitle_VoraussichtlichesEnde, 0);
	}

	public static String originalTitle_ProjektOrt() {
		return selectgetAttribuDataOriginal(OriginalTitle_ProjektOrt, 0);
	}

	public static String originalTitle_StundenSatz() {

		return selectgetAttribuDataOriginal(OriginalTitle_StundenSatz, 0);
	}

	public static String originalTitle_LetzteUpdate() {
		return selectgetAttribuDataOriginal(OriginalTitle_LetzteUpdate, 0);
	}

	public static String originalTitle_RefenrenzNummer() {
		return selectgetAttribuDataOriginal(OriginalTitle_RefenrenzNummer, 0);
	}

	public static String originalTitle_Remote() {
		return selectgetAttribuDataOriginal(OriginalTitle_Remote, 0);
	}

}
