package de.siphalor.bigitemsduh.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.overlay.ScreenOverlay;
import me.shedaniel.rei.api.common.entry.EntryStack;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Optional;

public class REIProxy {
	public static boolean renderFocusedOverlayEntry(DrawContext context, int x, int y, float scale) {
		REIRuntime reiRuntime = REIRuntime.getInstance();
		if (!reiRuntime.isOverlayVisible()) {
			return false;
		}

		Optional<ScreenOverlay> overlay = reiRuntime.getOverlay();
		if (overlay.isPresent()) {
			EntryStack<?> focusedStack = overlay.get().getEntryList().getFocusedStack();
			if (focusedStack != null && !focusedStack.isEmpty()) {
				MatrixStack matrices = context.getMatrices();
				matrices.push();
				matrices.translate(x, y, 100);
				matrices.scale(scale, scale, 1F);
				focusedStack.render(
						context,
						new Rectangle(0, 0, 16, 16), -1, -1, 0
				);
				matrices.pop();
				return true;
			}
		}
		return false;
	}
}
