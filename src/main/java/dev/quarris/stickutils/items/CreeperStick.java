package dev.quarris.stickutils.items;

import dev.quarris.stickutils.ModRef;
import dev.quarris.stickutils.registry.DamageSetup;
import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CreeperStick extends UtilityStick {

    public static ForgeConfigSpec.BooleanValue canObtain;
    public static ForgeConfigSpec.IntValue durability;
    public static ForgeConfigSpec.DoubleValue damageMultiplier;

    public CreeperStick(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            level.explode(null, DamageSetup.creeperStick(player), new ExplosionDamageCalculator(), player.getX(), player.getY(), player.getZ(), (float) 3, false, Level.ExplosionInteraction.NONE);
            player.getItemInHand(hand).hurtAndBreak(1, player, p -> p.level().playSound(null, p.blockPosition(), SoundEvents.CREEPER_DEATH, SoundSource.NEUTRAL, 1, 1.1f));
        }
        return new InteractionResultHolder<>(InteractionResult.sidedSuccess(level.isClientSide()), player.getItemInHand(hand));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return durability.get() > 0 ? durability.get() : super.getMaxDamage(stack);
    }

    @Override
    public boolean canBeDepleted() {
        return durability.get() > 0;
    }

    public static void buildConfigs(ForgeConfigSpec.Builder builder) {
        builder.comment("Creeper on a Stick").push("creeper_stick");
        canObtain = builder.comment(
            "Can you obtain a Creeper on a Stick by Right Clicking a Creeper with a Stick?",
            "Set to false if you would like to effectively disable this in survival, or have a custom way of obtaining the item (eg. via recipe)"
        ).define("can_obtain", true);
        durability = builder.comment(
            "Amount of durability the Creeper on a Stick will have."
        ).defineInRange("durability", 25, 0, 8192);
        damageMultiplier = builder.comment(
            "The multiplier on the explosion damage.",
            "Setting this to 1 will trigger a creeper-like explosion with default damage",
            "Anything less than 1 will reduce damage, anything more will increase the damage."
        ).defineInRange("damage_multiplier", 0.25, 0, 10);
        builder.pop();
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID)
    public static class EventHandler {

        @SubscribeEvent
        public static void poorCreeper(PlayerInteractEvent.EntityInteractSpecific event) {
            if (!canObtain.get()) return;

            Player player = event.getEntity();
            Entity target = event.getTarget();
            ItemStack stack = event.getItemStack();
            if (stack.getItem() == Items.STICK) {
                if (target instanceof Creeper) {
                    stack.shrink(1);
                    player.addItem(new ItemStack(ItemSetup.CREEPER_STICK.get()));
                    target.discard();
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CREEPER_HURT, SoundSource.PLAYERS, 20, 1.1f);
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CREEPER_HURT, SoundSource.PLAYERS, 10, 1.3f);
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CREEPER_HURT, SoundSource.PLAYERS, 5, 1.5f);
                }
            }
        }

        @SubscribeEvent
        public static void reducePlayerDamage(LivingHurtEvent event) {
            // Only reduce for the creeper stick damage
            if (!event.getSource().is(DamageSetup.CREEPER_STICK)) {
                return;
            }

            if (damageMultiplier.get() == 1) {
                event.setCanceled(true);
                return;
            }

            if (damageMultiplier.get() == 1) {
                return;
            }

            event.setAmount((float) (event.getAmount() * damageMultiplier.get()));
        }
    }
}
