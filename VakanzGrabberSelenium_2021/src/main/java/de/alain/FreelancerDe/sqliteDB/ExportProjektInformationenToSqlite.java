package de.alain.FreelancerDe.sqliteDB;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.Status;

import de.alain.FreelancerDe.navigationUndProjektsinformationenHolen.NavigationUndProjektsinformationenHolen;
import de.alain.FreelancerDe.projektInformationenUndProjektstatistik.ProjektInformationen;
import de.alain.FreelancerDe.utils.ExtentListeners;

public class ExportProjektInformationenToSqlite {

	private static String variable;

	private static SQLITEJDBC dd = new SQLITEJDBC();

	public static String textMethodeName(String MethodeName, String FileName, String getTitleURL) {

		return variable = MethodeName + " " + FileName + " " + getTitleURL;

	}

	public static void insertDataToSqileDB() throws IOException {
		ExtentListeners.test.log(Status.INFO, "Projektsergebnisse in Excel exportiert");

		List<ProjektInformationen> list = NavigationUndProjektsinformationenHolen.listProjektInformationen();

		int rownum = 0;

		// Data
		for (ProjektInformationen pr : list) {

			rownum++;

			System.out.println("rownum++ " + "XXXXXXXXXXXXXXXXXXXXXXXXXXX     " + rownum);
			// Insert die Daten in SQLITE
			dd.sQLITEJDBCInsertData(pr, rownum, variable.split(" ")[1], variable.split(" ")[2], currentDateTime());

		}

		ExtentListeners.test.log(Status.PASS, "Projektsergebnisse sind in die Datenbank erfolgreich exportiert worden");

	}

	public static String currentDateTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		Date date = new Date();

		System.out.println(formatter.format(date));
		return formatter.format(date).toString();

	}
}
