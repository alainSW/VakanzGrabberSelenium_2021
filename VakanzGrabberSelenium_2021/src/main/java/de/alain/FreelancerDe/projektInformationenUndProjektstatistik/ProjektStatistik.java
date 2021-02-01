package de.alain.FreelancerDe.projektInformationenUndProjektstatistik;

public class ProjektStatistik {

	public static int gesamteProjektAnzahl = 0;
	public static int endProjektMaskeNummer = 0;
	public static int anfangProjektMaskeNummer = 0;
	public static int anzahlProjektProMaske = 0;
	public static double pageTotal = 0;

	public static int gesamteProjekt(String projektErgebnisStatistik) {
		// Diese Methode erm�glicht die Ermittlung der gesamten Projekte
		String[] hilsvariable;
		String input = "";
		String[] wert; // Pojekt Total
		input = projektErgebnisStatistik;
		hilsvariable = input.split("von ");
		gesamteProjektAnzahl = Integer.parseInt(hilsvariable[1]);
		return gesamteProjektAnzahl;

	}

	public static int endProjektMaskeNummer(String projektErgebnisStatistik) {
		// Diese Methode erm�glicht die Ermittlung der Position der letzten Projektmaske
		String[] hilsvariable;
		String input = "";
		String[] wert; // Projekt Total
		input = projektErgebnisStatistik;
		hilsvariable = input.split(" von");
		wert = hilsvariable[0].split("-");
		endProjektMaskeNummer = Integer.parseInt(wert[1]);
		return endProjektMaskeNummer;

	}

	public static int anfangProjektMaskeNummer(String projektErgebnisStatistik) {
		// Diese Methode erm�glicht die Ermittlung der Position der ersten Projektmaske
		String[] hilsvariable;
		String input = "";
		String[] wert; // Pojekt Total
		input = projektErgebnisStatistik;
		hilsvariable = input.split(" von");
		wert = hilsvariable[0].split("-");
		anfangProjektMaskeNummer = Integer.parseInt(wert[0].split(" ")[1]);
		return anfangProjektMaskeNummer;

	}

	public static int anzahlProjektProMaske(String projektErgebnisStatistik) {
		// Diese Methode erm�glicht die Ermmitlung der gesamte Projekte pro Seitee

		anzahlProjektProMaske = endProjektMaskeNummer(projektErgebnisStatistik)
				- anfangProjektMaskeNummer(projektErgebnisStatistik) + 1;
		return anzahlProjektProMaske;

	}

	public static double anzahlDerGeneriertenMaskeJeProjektsuche(String projektErgebnisStatistik) {
		// Diese Methode erm�glicht die Ermittlung der Anzahl der Projektsuche pro Maske
		Double projektPoSeite = (double) endProjektMaskeNummer(projektErgebnisStatistik);
		Double projektTotal = (double) gesamteProjekt(projektErgebnisStatistik);
		Double ergebnis = (double) 0;
		ergebnis = projektTotal / projektPoSeite;
		pageTotal = ergebnis;
		return pageTotal;

	}

}
