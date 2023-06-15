package dev.quarris.stickutils.items;

import dev.quarris.stickutils.ModRef;
import dev.quarris.stickutils.registry.ModDamages;
import dev.quarris.stickutils.registry.ModItems;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CreeperStick extends UtilityStick {
    public CreeperStick(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            level.explode(null, ModDamages.creeperStick(player), new ExplosionDamageCalculator(), player.getX(), player.getY(), player.getZ(), (float) 3, false, Level.ExplosionInteraction.NONE);
            player.getItemInHand(hand).hurtAndBreak(1, player, p -> p.level().playSound(null, p.blockPosition(), SoundEvents.CREEPER_DEATH, SoundSource.NEUTRAL, 1, 1.1f));
        }
        return new InteractionResultHolder<>(InteractionResult.sidedSuccess(level.isClientSide()), player.getItemInHand(hand));
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID)
    public static class EventHandler {

        @SubscribeEvent
        public static void poorCreeper(PlayerInteractEvent.EntityInteractSpecific event) {
            Player player = event.getEntity();
            Entity target = event.getTarget();
            ItemStack stack = event.getItemStack();
            if (stack.getItem() == Items.STICK) {
                if (target instanceof Creeper) {
                    stack.shrink(1);
                    player.addItem(new ItemStack(ModItems.CREEPER_STICK.get()));
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
            if (!event.getSource().is(ModDamages.CREEPER_STICK)) {
                return;
            }

            event.setAmount(event.getAmount() / 4);
        }
    }
}
