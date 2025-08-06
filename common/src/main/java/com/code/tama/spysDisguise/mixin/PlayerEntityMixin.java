package com.code.tama.spysDisguise.mixin;

import com.code.tama.spysDisguise.core.interfaces.IEntityDataSaver;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public class PlayerEntityMixin implements IEntityDataSaver {
    private EntityType<?> disguiseType = null;

    @Unique
    @Override
    public EntityType<?> spymod$getDisguise() {
        return this.disguiseType;
    }

    @Unique
    @Override
    public void spymod$setDisguise(EntityType<?> type) {
        this.disguiseType = type;
    }
}