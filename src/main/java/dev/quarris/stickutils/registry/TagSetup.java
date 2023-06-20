package dev.quarris.stickutils.registry;

import dev.quarris.stickutils.ModRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class TagSetup {

    public static class Entities {
        public static final TagKey<EntityType<?>> LEAD_STICK_BLACKLIST = TagKey.create(Registries.ENTITY_TYPE, ModRef.res("lead_stick_blacklist"));
    }
}
