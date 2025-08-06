package com.code.tama.spysDisguise.forge;

import com.code.tama.spysDisguise.SpysDisguise;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpysDisguise.MODID)
public final class SpysDisguiseForge {
    @SuppressWarnings("removal")
    public SpysDisguiseForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(SpysDisguise.MODID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        SpysDisguise.init();
    }
}
