package de.siphalor.bigitemsduh;

import de.siphalor.amecs.api.KeyModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigItemsDuh implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "big_items_duh";
	public static final String MOD_NAME = "Big items, duh!";

	public static final String KEY_BINDING_CATEGORY = MOD_ID.replace('_', '-');
	public static final KeyBinding TOGGLE_KEY_BINDING = new ToggleKeyBinding(new Identifier(MOD_ID, "toggle-zoom"), InputUtil.Type.KEYSYM, 91, KEY_BINDING_CATEGORY, new KeyModifiers(false, false, true));
	public static final KeyBinding HOLD_KEY_BINDING = new KeyBinding("key." + MOD_ID + ".hold", InputUtil.Type.KEYSYM, InputUtil.UNKNOWN_KEY.getCode(), KEY_BINDING_CATEGORY);

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
