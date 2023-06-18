package dev.quarris.stickutils.registry;

import dev.quarris.stickutils.ModRef;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;

public class DamageSetup {

    public static final ResourceKey<DamageType> CREEPER_STICK = ResourceKey.create(Registries.DAMAGE_TYPE, ModRef.res("creeper_stick"));

    public static DamageSource creeperStick(Player player) {
        return player.damageSources().source(CREEPER_STICK, player);
    }

}
