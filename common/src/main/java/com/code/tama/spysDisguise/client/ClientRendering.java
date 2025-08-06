package com.code.tama.spysDisguise.client;

import com.code.tama.spysDisguise.core.interfaces.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;

import java.util.HashMap;
import java.util.Map;

public class ClientRendering {
    public static void renderDisguise(PoseStack poseStack, LivingEntity player, MultiBufferSource bufferSource, int packedLight, float f, float g) {
        EntityType<?> disguiseType = ((IEntityDataSaver)player).spymod$getDisguise();

        if (disguiseType != null && disguiseType != EntityType.PLAYER) {
            if (disguiseType.create(player.level()) instanceof LivingEntity fakeEntity) {
                // Get the renderer for the disguise entity.
                EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
                EntityRenderer<?> renderer = dispatcher.getRenderer(fakeEntity);

                if (renderer instanceof LivingEntityRenderer livingRenderer) {

                    fakeEntity.copyPosition(player);
                    fakeEntity.yBodyRot = player.yBodyRot;
                    fakeEntity.yHeadRot = player.yHeadRot;
                    fakeEntity.yRotO = player.yRotO;
                    fakeEntity.yBodyRotO = player.yBodyRotO;
                    fakeEntity.yHeadRotO = player.yHeadRotO;
                    fakeEntity.setSwimming(player.isSwimming());
                    fakeEntity.setArrowCount(player.getArrowCount());
                    fakeEntity.setSprinting(player.isSprinting());
                    fakeEntity.setNoGravity(player.isNoGravity());
                    fakeEntity.setPose(player.getPose());
                    fakeEntity.walkDist = player.walkDist;
                    fakeEntity.walkDistO = player.walkDistO;
                    fakeEntity.xRotO = player.xRotO;
                    fakeEntity.xo = player.xo;
                    fakeEntity.yo = player.yo;
//                    fakeEntity.walkAnimation.setSpeed(player.walkAnimation.speed());
//                    fakeEntity.walkAnimation.position(player.walkAnimation.position());
                    fakeEntity.yya = player.yya;
                    fakeEntity.setSpeed(player.getSpeed());
                    fakeEntity.setDeltaMovement(player.getDeltaMovement());
                    fakeEntity.setOnGround(player.onGround());
                    fakeEntity.attackAnim = player.attackAnim;
                    fakeEntity.oAttackAnim = player.oAttackAnim;
                    fakeEntity.swinging = player.swinging;
                    fakeEntity.setSprinting(player.isSprinting());

                    poseStack.pushPose();

                    // For more complex entities, may need to adjust the position
                    // based on the difference in height, eye level, etc. ex:
                    // poseStack.translate(0, -player.getEyeHeight() + fakeEntity.getEyeHeight(), 0);

                    // Render the fake entity model.
                    EntityModel<LivingEntity> model = livingRenderer.getModel();

//                    System.out.println(g);
                    // Call setupAnim() with the correct parameters
                    model.setupAnim(
                            fakeEntity,
                            g, // limbSwing
                            f, // limbSwingAmount
                            (float)fakeEntity.tickCount + g, // ageInTicks
                            fakeEntity.yHeadRot, // headYaw
                            fakeEntity.getXRot() // headPitch
                    );
                    livingRenderer.render(fakeEntity, f, g, poseStack, bufferSource, packedLight);

                    poseStack.popPose();
                }
            }
        }
    }
}