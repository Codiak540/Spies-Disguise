package com.code.tama.spysDisguise.core.items;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.code.tama.spysDisguise.SpysDisguise.MODID;

public class STabs {
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(MODID, Registries.CREATIVE_MODE_TAB);

    public static RegistrySupplier<CreativeModeTab> MAIN_TAB;

    //Init Creative Tabs
    public static void initTabs(){
        MAIN_TAB = TABS.register("main_tab",
                () -> CreativeTabRegistry.create(Component.translatable("category.main_tab"),
                        () -> new ItemStack(SItems.DISGUISE_WATCH.get())
                )
        );
        TABS.register();
    }
}
