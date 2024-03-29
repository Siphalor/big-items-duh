package de.siphalor.bigitemsduh;

import de.siphalor.amecs.api.KeyModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigItemsDuh implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "big_items_duh";
	public static final String MOD_NAME = "Big items, duh!";

	public static final String KEY_BINDING_CATEGORY = "key.categories." + MOD_ID;
	public static final KeyBinding TOGGLE_KEY_BINDING = new ToggleKeyBinding(new Identifier(MOD_ID, "toggle_zoom"), InputUtil.Type.KEYSYM, 91, KEY_BINDING_CATEGORY, new KeyModifiers(false, false, true));
	public static final KeyBinding HOLD_KEY_BINDING = new KeyBinding("key." + MOD_ID + ".hold_zoom", InputUtil.Type.KEYSYM, InputUtil.UNKNOWN_KEY.getCode(), KEY_BINDING_CATEGORY);

	public static boolean reiLoaded = FabricLoader.getInstance().isModLoaded("roughlyenoughitems");

	private static boolean enabled = true;

	@Override
	public void onInitialize() {
		log(Level.INFO, "duh");
		KeyBindingHelper.registerKeyBinding(TOGGLE_KEY_BINDING);
		KeyBindingHelper.registerKeyBinding(HOLD_KEY_BINDING);
	}

	public static void log(Level level, String message){
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static void setEnabled(boolean enabled) {
		BigItemsDuh.enabled = enabled;
	}

	public static boolean shallRender() {
		return enabled || HOLD_KEY_BINDING.isPressed();
	}
}
