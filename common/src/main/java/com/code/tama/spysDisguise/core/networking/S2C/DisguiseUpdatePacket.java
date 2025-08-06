package com.code.tama.spysDisguise.core.networking.S2C;

import com.code.tama.spysDisguise.SpysDisguise;
import com.code.tama.spysDisguise.core.interfaces.IEntityDataSaver;
import dev.architectury.networking.NetworkManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;

public record DisguiseUpdatePacket(EntityType<?> entityType)  {
    public static final ResourceLocation ID = new ResourceLocation(SpysDisguise.MODID, "disguise_update");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ID, (packet, context) -> {
            ResourceLocation id = packet.readResourceLocation();
            EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(id);

            System.out.println("Server received disguise packet for: " + id);
             ((IEntityDataSaver) context.getPlayer()).spymod$setDisguise(entityType);

            // TODO: After setting the data, broadcast the change to all other players.
            // This ensures everyone sees the change.
        });
    }
}