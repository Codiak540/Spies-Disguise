package com.code.tama.spysDisguise.fabric;

import com.code.tama.spysDisguise.SpysDisguise;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import static com.code.tama.spysDisguise.client.ClientRendering.renderDisguise;

public final class SpysDisguiseFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        SpysDisguise.init();
    }
}
