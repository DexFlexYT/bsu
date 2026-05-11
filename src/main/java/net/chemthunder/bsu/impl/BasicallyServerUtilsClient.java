package net.chemthunder.bsu.impl;

import net.chemthunder.bsu.impl.event.CoordReadoutEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class BasicallyServerUtilsClient implements ClientModInitializer {
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new CoordReadoutEvent());
    }
}
