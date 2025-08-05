package com.code.tama.spiesDisguise.forge;

import com.code.tama.spiesDisguise.SpiesDisguise;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpiesDisguise.MOD_ID)
public final class SpiesDisguiseForge {
    public SpiesDisguiseForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(SpiesDisguise.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        SpiesDisguise.init();
    }
}
