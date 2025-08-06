package com.code.tama.spysDisguise.core.interfaces;

import net.minecraft.world.entity.EntityType;

public interface IEntityDataSaver {
    EntityType<?> spymod$getDisguise();

    void spymod$setDisguise(EntityType<?> type);
}
