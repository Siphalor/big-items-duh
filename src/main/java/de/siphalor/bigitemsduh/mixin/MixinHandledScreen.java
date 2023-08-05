package de.siphalor.bigitemsduh.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import de.siphalor.bigitemsduh.BIDConfig;
import de.siphalor.bigitemsduh.BigItemsDuh;
import de.siphalor.bigitemsduh.HorizontalAlignment;
import de.siphalor.bigitemsduh.compat.REIProxy;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class MixinHandledScreen extends Screen {
	@Shadow
	@Nullable
	protected abstract Slot getSlotAt(double xPosition, double yPosition);

	@Shadow
	protected abstract void drawItem(MatrixStack matrices, ItemStack stack, int xPosition, int yPosition, String amountText);

	@Shadow
	protected int x;

	@Shadow protected int backgroundHeight;

	@Shadow @Final protected ScreenHandler handler;

	protected MixinHandledScreen(Text title) {
		super(title);
	}

	@Inject(method = "render", at = @At("RETURN"))
	public void onRendered(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		if (BigItemsDuh.shallRender()) {
			float size = Math.min(x * BIDConfig.scale, backgroundHeight * BIDConfig.scale);
			float scale = size / 16;
			double ix = (x - size) / 2F;
			if (BIDConfig.horizontalAlignment == HorizontalAlignment.RIGHT) {
				ix = width - ix - size;
			}
			double iy = (height - size) / 2F;

			Slot slot = getSlotAt(mouseX, mouseY);
			ItemStack stack;

			if (slot != null && !slot.getStack().isEmpty()) {
				stack = slot.getStack();
			} else {
				if (BigItemsDuh.reiLoaded) {
					if (REIProxy.renderFocusedOverlayEntry(matrices, (int) ix, (int) iy, scale)) {
						return;
					}
				}

				stack = handler.getCursorStack();
				if (stack == null || stack.isEmpty()) {
					return;
				}
			}
			MatrixStack matrices_ = RenderSystem.getModelViewStack();
			matrices_.push();

			matrices_.translate(ix, iy, -10);
			matrices_.scale(scale, scale, 1F);

			drawItem(matrices, stack, 0, 0, "");
			matrices_.pop();

			RenderSystem.applyModelViewMatrix();
			RenderSystem.enableDepthTest();
		}
	}
}
