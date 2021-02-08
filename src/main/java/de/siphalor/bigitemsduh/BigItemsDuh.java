package de.siphalor.bigitemsduh;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigItemsDuh implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "big_items_duh";
	public static final String MOD_NAME = "Big items, duh!";

	@Override
	public void onInitialize() {
		log(Level.INFO, "Initializing");
		//TODO: Initializer
	}

	public static void log(Level level, String message){
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}

}
