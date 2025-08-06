package com.code.tama.spysDisguise.mixin;

import com.code.tama.spysDisguise.core.interfaces.IEntityDataSaver;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.code.tama.spysDisguise.client.ClientRendering.renderDisguise;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Shadow protected abstract void setModelProperties(AbstractClientPlayer abstractClientPlayer);

    /**
     * @author Codiak540
     * @reason Render the players disguise, if it exists
     */
    @Overwrite
    public void render(AbstractClientPlayer abstractClientPlayer, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(((IEntityDataSaver) abstractClientPlayer).spymod$getDisguise() != null) {
            renderDisguise(poseStack, abstractClientPlayer, multiBufferSource, i, f, g);
            return;
        }
        this.setModelProperties(abstractClientPlayer);
        super.render(abstractClientPlayer, f, g, poseStack, multiBufferSource, i);
    }
}
