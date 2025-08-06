package com.code.tama.spysDisguise.core.items;

import com.code.tama.spysDisguise.core.interfaces.IEntityDataSaver;
import com.code.tama.spysDisguise.core.networking.S2C.DisguiseUpdatePacket;
import com.code.tama.spysDisguise.core.tts.RayTraceUtils;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class DisguiseWatchItem extends Item {

    public DisguiseWatchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            // Perform a raycast to find the entity the player is looking at
            // Max distance for the raycast in blocks
            double reachDistance = 16.0;
            EntityHitResult hitResult = RayTraceUtils.rayTraceEntity(player, reachDistance);

            EntityType<?> targetType = EntityType.PLAYER; // Default to un-disguise

            if (hitResult != null) {
                    targetType = hitResult.getEntity().getType();
            }

            // Create and send the packet to the server with the new target
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeResourceLocation(BuiltInRegistries.ENTITY_TYPE.getKey(targetType));
            NetworkManager.sendToServer(DisguiseUpdatePacket.ID, buf);
            ((IEntityDataSaver) player).spymod$setDisguise(targetType);

        }

        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
}