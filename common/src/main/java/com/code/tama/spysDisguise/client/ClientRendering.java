package com.code.tama.spysDisguise.client;

import com.code.tama.spysDisguise.core.interfaces.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.WalkAnimationState;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClientRendering {
    public static void renderDisguise(PoseStack poseStack, LivingEntity player, MultiBufferSource bufferSource, int packedLight, float partialTicks, float yaw) {
        EntityType<?> disguiseType = ((IEntityDataSaver) player).spymod$getDisguise();

        if (disguiseType == null) return;

        // Create the disguise entity
        Entity disguiseEntity = disguiseType.create(player.level());

        if (disguiseEntity == null) return;

        // Match disguise position/rotation to the player
        disguiseEntity.setPos(player.getX(), player.getY(), player.getZ());
        disguiseEntity.setYRot(player.getYRot());
        disguiseEntity.setXRot(player.getXRot());
        disguiseEntity.tickCount = player.tickCount;

        // If it's a LivingEntity, copy pose (sneaking, swimming, etc.)
        // Ensure the disguise is a LivingEntity
        if (disguiseEntity instanceof LivingEntity livingDisguise) {
            livingDisguise.setPose(player.getPose());

            float bodyYaw = player.yBodyRot;
            float headYaw = player.getYHeadRot();
            float pitch = player.getXRot();

            livingDisguise.setYRot(bodyYaw);
            livingDisguise.setYBodyRot(bodyYaw);
            livingDisguise.setYHeadRot(headYaw);
            livingDisguise.setXRot(pitch);

            livingDisguise.yRotO = bodyYaw;
            livingDisguise.yBodyRotO = bodyYaw;
            livingDisguise.yHeadRotO = headYaw;
            livingDisguise.xRotO = pitch;

            disguiseEntity.tickCount = player.tickCount;

            // Sync basic movement
            livingDisguise.setDeltaMovement(player.getDeltaMovement());

            float movementSpeed = (float) disguiseEntity.getDeltaMovement().horizontalDistance(); // or from player
            livingDisguise.walkAnimation.update(movementSpeed * 4F, partialTicks); // tweak factor if needed


//            try {
//                WalkAnimationState source = player.walkAnimation;
//                WalkAnimationState target = livingDisguise.walkAnimation;
//
//                Field speedField = WalkAnimationState.class.getDeclaredField("speed");
//                Field positionField = WalkAnimationState.class.getDeclaredField("position");
//                Field speedOldField = WalkAnimationState.class.getDeclaredField("speedOld");
//
//                speedField.setAccessible(true);
//                positionField.setAccessible(true);
//                speedOldField.setAccessible(true);
//
//                float speed = (float) speedField.get(source);
//                float position = (float) positionField.get(source);
//
//                speedField.set(target, speed);
//                positionField.set(target, position);
//                speedOldField.set(target, speed); // ✅ Copy current speed into speedOld
//            } catch (Exception e) {
//                e.printStackTrace(); // Or log more cleanly
//            }
            System.out.println("Player speed: " + player.walkAnimation.speed()
                    + " | disguise speed: " + livingDisguise.walkAnimation.speed());

            System.out.println("Player position: " + player.walkAnimation.position()
                    + " | disguise position: " + livingDisguise.walkAnimation.position());
        }



        // Render the disguise
        Minecraft.getInstance().getEntityRenderDispatcher().render(
                disguiseEntity,
                0.0, 0.0, 0.0, // Render relative to the player's position
                yaw,
                partialTicks,
                poseStack,
                bufferSource,
                packedLight
        );
    }

}