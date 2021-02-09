package de.siphalor.bigitemsduh;

import de.siphalor.amecs.api.AmecsKeyBinding;
import de.siphalor.amecs.api.KeyModifiers;
import de.siphalor.amecs.api.PriorityKeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class ToggleKeyBinding extends AmecsKeyBinding implements PriorityKeyBinding {
	public ToggleKeyBinding(Identifier id, InputUtil.Type type, int code, String category, KeyModifiers defaultModifiers) {
		super(id, type, code, category, defaultModifiers);
	}

	@Override
	public boolean onPressedPriority() {
		BigItemsDuh.setEnabled(!BigItemsDuh.isEnabled());
		return false;
	}
}
