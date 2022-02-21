package de.siphalor.bigitemsduh.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.overlay.ScreenOverlay;
import me.shedaniel.rei.api.common.entry.EntryStack;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Optional;

public class REIProxy {
	public static boolean renderFocusedOverlayEntry(MatrixStack matrices, int x, int y, float scale) {
		Optional<ScreenOverlay> overlay = REIRuntime.getInstance().getOverlay();
		if (overlay.isPresent()) {
			EntryStack<?> focusedStack = overlay.get().getEntryList().getFocusedStack();
			if (focusedStack != null && !focusedStack.isEmpty()) {
				matrices.push();
				matrices.translate(x, y, 100);
				matrices.scale(scale, scale, 1F);
				focusedStack.render(
						matrices,
						new Rectangle(0, 0, 16, 16), -1, -1, 0
				);
				matrices.pop();
				return true;
			}
		}
		return false;
	}
}
