package net.chemthunder.bsu.impl.event;

import net.chemthunder.bsu.impl.BasicallyServerUtils;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public class CoordReadoutEvent implements HudRenderCallback {
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (BasicallyServerUtils.Config.compassFunctionality) {
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player == null) return;

            if (player.getStackInHand(player.getActiveHand()).isOf(Items.COMPASS)) {
                drawContext.drawCenteredTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        Text.literal(
                                "[" + player.getBlockX() + ", " + player.getBlockY() + ", " + player.getBlockZ() + "]"
                        ),
                        drawContext.getScaledWindowWidth() / 2,
                        drawContext.getScaledWindowHeight() / 2 - 20,
                        0xffffff
                );
            }
        }
    }
}
