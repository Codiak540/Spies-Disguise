package com.code.tama.spysDisguise.core.items;

import com.google.common.base.Supplier;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static com.code.tama.spysDisguise.SpysDisguise.MODID;

public class SItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MODID, Registries.ITEM);

    public static RegistrySupplier<Item> DISGUISE_WATCH = registerItem("disguise_watch", () -> new DisguiseWatchItem(baseProperties().arch$tab(STabs.MAIN_TAB)));

    public static void init() {
        ITEMS.register();
    }

    //Register an item
    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item){
        return ITEMS.register(new ResourceLocation(MODID, name), item);
    }

    //Construct an item property base
    public static Item.Properties baseProperties(){
        return new Item.Properties();
    }
}
