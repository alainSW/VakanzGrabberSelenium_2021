package de.alain.FreelancerDe.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:src\\test\\resources\\propertyFiles\\${environment}.properties" // mention the property file name
})
public interface ConfigProperties extends Config {

	@Key("webSeite")
	String getWebSeite();

	@Key("e_Mail")
	String getEmail();

	@Key("passwort")
	String getPasswort();

	@Key("sheetName")
	String getSheetName();

}
