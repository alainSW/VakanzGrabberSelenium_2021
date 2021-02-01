package de.alain.FreelancerDe.navigationUndProjektsinformationenHolen;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import de.alain.FreelancerDe.baseAktionen.BaseHtmlControl;
import de.alain.FreelancerDe.htmlControlProMaske.Projektmerkmale;
import de.alain.FreelancerDe.htmlControlProMaske.Projektsuche;
import de.alain.FreelancerDe.projektInformationenUndProjektstatistik.ProjektInformationen;
import de.alain.FreelancerDe.projektInformationenUndProjektstatistik.ProjektStatistik;
import de.alain.FreelancerDe.utils.ExtentListeners;

public class NavigationUndProjektsinformationenHolen extends BaseHtmlControl {

	// Initialisierung Projektsinformationsmenge
	public static ProjektInformationen[] projektInformationen;

	public NavigationUndProjektsinformationenHolen(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void projektmaskeAnklickeUndMaskeInformationenAssortieren(Projektsuche projektsuche, WebDriver driver,
			Projektmerkmale projektmerkmale, String Suchbegriff) throws InterruptedException, IOException {

		/*
		 * Diese Methode ermöglicht von der ersten Projektmaske bis zur letzten
		 * Projektseite zu blättern.
		 * 
		 */
		ExtentListeners.test.log(Status.INFO, "<b>" + "Projektsergebnisse durchklicken" + "</b>");

		int GesamteProjekt = 0;
		GesamteProjekt = ProjektStatistik.gesamteProjekt(Projektsuche.anzahlProjekt());

		// System.out.println("Projektsuche.anzahlProjekt() :" +
		// Projektsuche.anzahlProjekt());
		projektInformationen = new ProjektInformationen[GesamteProjekt];
		int projektMaskeNummer = 1;
		double sss = ProjektStatistik.anzahlDerGeneriertenMaskeJeProjektsuche(Projektsuche.anzahlProjekt());
		// System.out.println(sss);

		System.out.println("projektInformationen Länge: " + projektInformationen.length);

		if (ProjektStatistik
				.anzahlDerGeneriertenMaskeJeProjektsuche(Projektsuche.anzahlProjekt()) == projektMaskeNummer) {
			// System.out.println("nur eine Seite ");
			projektClick(projektsuche, driver, projektmerkmale, projektMaskeNummer, Suchbegriff);

		} else {
			// System.out.println("mehere Seite " + sss);
			// projektClick(projektSuche, driver, projektDescription, i);

			while (projektMaskeNummer < sss + 1) {
				/*
				 * System.out.println(
				 * ProjektStatistik.AnzahlDerGeneriertenMaskeJeProjektsuche(Projektsuche.
				 * anzahlProjekt())); System.out.println(AnzahlProjektJeMaske(projektsuche));
				 */

				if (projektMaskeNummer == 1) {
					projektClick(projektsuche, driver, projektmerkmale, projektMaskeNummer, Suchbegriff);
				} else {
					projektsuche.clickPageProjekt();
					projektClick(projektsuche, driver, projektmerkmale, projektMaskeNummer, Suchbegriff);
				}

				projektMaskeNummer++;
			}

		}
		listProjektInformationen();

		ExtentListeners.test.log(Status.PASS, "<b>" + "Projektsergebnisse sind erfolgreich anklickt worden" + "</b>");

	}

	public static int anzahlProjektJeMaske(Projektsuche projektsuche) {

		/*
		 * Mit dieser Methode wird die Anzahl der Projekte je Projektmaske ermiitteln.
		 */

		int wert1;
		/*
		 * System.out.println("AnzahlProjektJeMaske.projektliste()  :  " +
		 * ProjektStatistik.AnzahlProjektProMaske(Projektsuche.anzahlProjekt()));
		 */
		wert1 = ProjektStatistik.anzahlProjektProMaske(Projektsuche.anzahlProjekt());

		// System.out.println("AnzahlProjektJeMaske(projektsuche): " + wert1);
		return wert1;

	}

	public static void projektClick(Projektsuche projektsuche, WebDriver driver, Projektmerkmale projektmerkmale,
			int projektMaskeNummer, String suchbegriff) throws InterruptedException, IOException {

		/*
		 * Diese Methode ermöglicht die Projekte je Maske anzuklicken
		 * 
		 * 
		 * Variablen Definition:
		 * 
		 * 
		 * k = prokektNummer, i = seitennummer,
		 * 
		 * 
		 */

		// k = prokektNummer
		int prokektNummer = 1;
		// System.out.println("AnzahlProjektJeMaske(projektsuche) +" +
		// AnzahlProjektJeMaske(projektsuche));

		if (projektMaskeNummer == 1) {
			// int k = 1;
			System.out.println(projektMaskeNummer);

			while (prokektNummer < anzahlProjektJeMaske(projektsuche) + 1) {
				// System.out.println(AnzahlProjektJeMaske(projektsuche) + 1);
				// System.out.println(prokektNummer);
				WebElement projektId = driver
						.findElement(By.xpath("(//a[contains(@id,\"project_link\")])[" + prokektNummer + "]"));

				projektId.click();

				System.out.println("Start: " + String.valueOf(prokektNummer) + " "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				// ProjektInformationenSortierenByErsteSeite(prokektNummer);
				projektInformationenSortierenAbDerZweiteSeite(prokektNummer, projektMaskeNummer);

				System.out.println("Ende: " + String.valueOf(prokektNummer) + " "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				TimeUnit.SECONDS.sleep(1);

				// ScreenshotProjekt.ScreenshotExecute(suchbegriff + "_" +
				// Integer.toString(prokektNummer));
				driver.navigate().back();

				prokektNummer++;
			}
		}

		if (projektMaskeNummer > 1) {
			/*
			 * System.out.println( AnzahlProjektJeMaske(projektsuche) +
			 * ProjektStatistik.GesamteProjekt(Projektsuche.anzahlProjekt()));
			 * System.out.println("prokektNummer " + prokektNummer + " i " +
			 * projektMaskeNummer);
			 */

			while (prokektNummer < anzahlProjektJeMaske(projektsuche) + 1) {

				WebElement projektId = driver
						.findElement(By.xpath("(//*[contains(@id,\"project_link\")])[" + prokektNummer + "]"));

				projektId.click();

				System.out.println(Projektmerkmale.originalTitle_GeplanterStart().length() + " "
						+ Projektmerkmale.originalTitle_VoraussichtlichesEnde().length() + " "
						+ Projektmerkmale.originalTitle_ProjektOrt().length() + " "
						+ Projektmerkmale.originalTitle_StundenSatz().length() + " "
						+ Projektmerkmale.originalTitle_Remote().length() + " "
						+ Projektmerkmale.originalTitle_LetzteUpdate().length() + " "
						+ Projektmerkmale.originalTitle_RefenrenzNummer().length());

				System.out.println("Start: " + String.valueOf(prokektNummer) + " "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				projektInformationenSortierenAbDerZweiteSeite(prokektNummer, projektMaskeNummer);

				System.out.println("Ende: " + String.valueOf(prokektNummer) + " "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				TimeUnit.SECONDS.sleep(1);

				driver.navigate().back();

				prokektNummer++;
			}
		}

	}

	public static List<ProjektInformationen> listProjektInformationen() {
		/*
		 * Dieser Methode wandelt die Array-Projektinformationen in einer Liste um.
		 * 
		 * 
		 */

		List<ProjektInformationen> list = Arrays.asList(projektInformationen);

		// System.out.println(list.get(2).getRefenrenzNummer());

		return list;
	}

	// 13.07.2020
	public static void projektInformationenSortierenAbDerZweiteSeite(int prokektNummer, int projektMaskeNummer) {

		/*
		 * ab der 1. Projektmaske
		 * 
		 * Diese Methode ermöglicht die Projektinformationen ab der ersten Projektmaske
		 * zu sortieren.
		 * 
		 * 
		 */

		String a1 = Projektmerkmale.originalTitle_GeplanterStart();
		String a2 = Projektmerkmale.originalTitle_VoraussichtlichesEnde();
		String a3 = Projektmerkmale.originalTitle_ProjektOrt();
		String a4 = Projektmerkmale.originalTitle_StundenSatz();
		String a5 = Projektmerkmale.originalTitle_Remote();
		String a6 = Projektmerkmale.originalTitle_LetzteUpdate();
		String a7 = Projektmerkmale.originalTitle_RefenrenzNummer();

		String projektTitle = Projektmerkmale.projektTitle();
		String geplanterStart = Projektmerkmale.geplanterStart();
		String voraussichtlichesEnde = Projektmerkmale.voraussichtlichesEnde();
		String projektOrt = Projektmerkmale.projektOrt();
		String stundenSatz = Projektmerkmale.stundenSatz();
		String remote = Projektmerkmale.remote();
		String letzteUpdat = Projektmerkmale.letzteUpdate();
		String refenrenzNummer = Projektmerkmale.refenrenzNummer();
		String projektbeschreibung = Projektmerkmale.projektbeschreibung();

		// Fall 1: Stundensatz, TRemote, letztes Update, Referenznummer

		if (a1.equals("Geplanter Start") && a2.equals("Voraussichtliches Ende") && a3.equals("Projektort")
				&& a4.equals("Stundensatz") && a5.equals("Remote-Einsatz möglich") && a6.equals("Letztes Update")
				&& a7.equals("Referenz-Nummer")) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, remote, letzteUpdat,
					refenrenzNummer, projektbeschreibung);
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);

			// System.out.println("aaa1");

		}

		// Fall 4: Stundensatz, letztes Update, Referenznummer

		/*
		 * System.out.println("Kein Remote 1: " + String.valueOf(prokektNummer) + " " +
		 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		 */

		// 9 ist die länge der "kein Text"

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() != 9) {

			refenrenzNummer = "kein Text";

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, refenrenzNummer,
					remote, letzteUpdat, projektbeschreibung);
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			/*
			 * System.out.println("Kein Remote 2: " + String.valueOf(prokektNummer) + " " +
			 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			 */
			return;
		}

		/*
		 * System.out.println("Kein Remote 3: " + String.valueOf(prokektNummer) + " " +
		 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		 */
		// Fall 2: Stundensatz, TRemote, letztes Update,

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
				&& a6.length() != 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.stundenSatz(), Projektmerkmale.remote(), Projektmerkmale.letzteUpdate(),
					refenrenzNummer, Projektmerkmale.projektbeschreibung());

			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 3: Stundensatz, TRemote, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() != 9) {
			refenrenzNummer = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.stundenSatz(), Projektmerkmale.remote(), refenrenzNummer,
					Projektmerkmale.letzteUpdate(), Projektmerkmale.projektbeschreibung());

			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 5: TRemote, letztes Update, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() != 9 && a7.length() != 9) {
			refenrenzNummer = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(), refenrenzNummer,
					Projektmerkmale.stundenSatz(), Projektmerkmale.remote(), Projektmerkmale.letzteUpdate(),
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 6: Stundensatz, TRemote

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.stundenSatz(), Projektmerkmale.remote(), letzteUpdat, refenrenzNummer,
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 7: Stundensatz, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() != 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.stundenSatz(), letzteUpdat, refenrenzNummer, Projektmerkmale.remote(),
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 8: Stundensatz, letztes Update
		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			System.out.println(Projektmerkmale.projektbeschreibung().replace("ü", "ue"));
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.stundenSatz(), letzteUpdat, Projektmerkmale.remote(), refenrenzNummer,
					Projektmerkmale.projektbeschreibung().replace("ü", "ue"));
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +ü
			// prokektNummer - 1]);
			return;
		}

		// Fall 9: TRemote, letztes Update

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() != 9 && a7.length() == 9) {

			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(), letzteUpdat,
					Projektmerkmale.stundenSatz(), Projektmerkmale.remote(), refenrenzNummer,
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;

		}

		// Fall 10: TRemote, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() != 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(), letzteUpdat,
					Projektmerkmale.stundenSatz(), refenrenzNummer, Projektmerkmale.remote(),
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 11: letztes Update, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() != 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(), letzteUpdat, refenrenzNummer,
					Projektmerkmale.stundenSatz(), Projektmerkmale.remote(), Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 12: Stundensatz,

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			remote = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.stundenSatz(), remote, letzteUpdat, refenrenzNummer,
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 13: TRemote,

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			stundenSatz = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(), Projektmerkmale.remote(),
					stundenSatz, letzteUpdat, refenrenzNummer, Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 14: letztes Update

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			remote = "kein Text";
			stundenSatz = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.letzteUpdate(), remote, stundenSatz, refenrenzNummer,
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall 15: Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() != 9) {
			letzteUpdat = "kein Text";
			remote = "kein Text";
			stundenSatz = "kein Text";
			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.projektTitle(), Projektmerkmale.geplanterStart(),
					Projektmerkmale.voraussichtlichesEnde(), Projektmerkmale.projektOrt(),
					Projektmerkmale.refenrenzNummer(), remote, letzteUpdat, stundenSatz,
					Projektmerkmale.projektbeschreibung());
			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;
		}

		// Fall: keine Attributen

		if (a1.length() == 9 && a2.length() == 9 && a3.length() == 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() == 9) {
			refenrenzNummer = "kein Text";
			letzteUpdat = "kein Text";
			remote = "kein Text";
			stundenSatz = "kein Text";
			projektTitle = "kein Text";
			geplanterStart = "kein Text";
			voraussichtlichesEnde = "kein Text";
			projektOrt = "kein Text";

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, remote, letzteUpdat,
					refenrenzNummer, projektbeschreibung);

			// System.out.println(projektInformationen[(projektMaskeNummer - 1) * 20 +
			// prokektNummer - 1]);
			return;

		}

	}

}
