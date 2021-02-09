package de.siphalor.bigitemsduh.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import de.siphalor.bigitemsduh.BigItemsDuh;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
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
	protected abstract void drawItem(ItemStack stack, int xPosition, int yPosition, String amountText);

	@Shadow
	protected int x;

	@Shadow protected int backgroundHeight;

	@Shadow @Final protected PlayerInventory playerInventory;

	protected MixinHandledScreen(Text title) {
		super(title);
	}

	@SuppressWarnings("deprecation")
	@Inject(method = "render", at = @At("RETURN"))
	public void onRendered(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		if (BigItemsDuh.shallRender()) {
			ItemStack stack;

			Slot slot = getSlotAt(mouseX, mouseY);
			if (slot != null) {
				stack = slot.getStack();
			} else {
				stack = playerInventory.getCursorStack();
			}

			if (stack != null && !stack.isEmpty()) {
				float size = (float) Math.min(x * 0.8F, backgroundHeight * 0.8);
				float scale = size / 16;
				RenderSystem.pushMatrix();
				RenderSystem.disableDepthTest();

				// For lightning to work correctly, the model needs to be scaled in z direction as well.
				// This causes problems when the model gets out of the rendering area and disappears partially or as a whole.
				// To fix this I manually fitted z scale and z translation for a bunch of values and did a linear regression on it.
				// The results look pretty promising.
				RenderSystem.translated((x - size) / 2F, (height - size) / 2F, -385F * scale + 955.5F);
				RenderSystem.scalef(scale, scale, scale);

				drawItem(stack, 0, 0, "");
				RenderSystem.enableDepthTest();
				RenderSystem.popMatrix();
			}
		}
	}
}
