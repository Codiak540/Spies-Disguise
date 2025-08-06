package com.code.tama.spysDisguise;

import com.code.tama.spysDisguise.core.items.SItems;
import com.code.tama.spysDisguise.core.items.STabs;
import com.code.tama.spysDisguise.core.networking.S2C.DisguiseUpdatePacket;

public final class SpysDisguise {
    public static final String MODID = "spysdisguise";

    public static void init() {
        SItems.init();
        STabs.initTabs();
        DisguiseUpdatePacket.register();
    }
}
