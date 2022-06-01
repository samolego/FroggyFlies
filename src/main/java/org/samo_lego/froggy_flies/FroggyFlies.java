package org.samo_lego.froggy_flies;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FroggyFlies implements ModInitializer {
	public static final String MOD_ID = "froggy_flies";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Ribbit! Don't feed fireflies to frogs!");
	}
}
