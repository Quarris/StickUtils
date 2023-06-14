package dev.quarris.stickutils.items;

import dev.quarris.stickutils.ModRef;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CatStick extends UtilityStick {

    public CatStick(Properties props) {
        super(props);
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID)
    public static class EventHandler {

        @SubscribeEvent
        public static void poorKitty(PlayerInteractEvent.EntityInteractSpecific event) {
            Player player = event.getEntity();
            Entity target = event.getTarget();
            ItemStack stack = event.getItemStack();
            if (stack.getItem() == Items.STICK) {
                if ((target instanceof Ocelot ocelot && !ocelot.isBaby()) || (target instanceof Cat cat && !(cat.isTame() || cat.isBaby()))) {
                    player.getItemInHand(event.getHand()).shrink(1);
                    player.addItem(new ItemStack(ModItems.CAT_STICK.get()));
                    target.kill();
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 20, 1.1f);
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 10, 1.3f);
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 5, 1.5f);
                }
            }
        }

    }
}
