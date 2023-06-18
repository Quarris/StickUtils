package dev.quarris.stickutils.mixins;

import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public abstract class CreeperMixin extends Monster {

    protected CreeperMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void creepersBegoneThot(CallbackInfo ci) {
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 8, 1.0D, 1.2D, (player) -> player.getMainHandItem().is(ItemSetup.CAT_STICK.get()) || player.getOffhandItem().is(ItemSetup.CAT_STICK.get())) {
            @Override
            public void start() {
                this.mob.level().playSound(null, this.toAvoid.blockPosition(), SoundEvents.CAT_AMBIENT, SoundSource.NEUTRAL, 1, 1.1f);
                super.start();
                ItemStack catStick = this.toAvoid.getMainHandItem().is(ItemSetup.CAT_STICK.get()) ? this.toAvoid.getMainHandItem() : this.toAvoid.getOffhandItem();
                catStick.hurtAndBreak(1, this.toAvoid, player -> player.level().playSound(null, player.blockPosition(), SoundEvents.CAT_DEATH, SoundSource.NEUTRAL, 1, 1.1f));
            }
        });
    }
}
