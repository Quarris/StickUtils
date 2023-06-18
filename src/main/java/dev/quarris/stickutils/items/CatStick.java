package dev.quarris.stickutils.items;

import dev.quarris.stickutils.ModRef;
import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CatStick extends UtilityStick {

    public static ForgeConfigSpec.BooleanValue canObtain;
    public static ForgeConfigSpec.IntValue durability;

    public CatStick(Properties props) {
        super(props);
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
        builder.comment("Cat on a Stick").push("cat_stick");
        canObtain = builder.comment(
            "Can you obtain a Cat on a Stick by Right Clicking a Cat with a Stick?",
            "Set to false if you would like to effectively disable this in survival, or have a custom way of obtaining the item (eg. via recipe)"
        ).define("can_obtain", true);
        durability = builder.comment(
            "Amount of durability the Cat on a Stick will have."
        ).defineInRange("durability", 25, 0, 8192);
        builder.pop();
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID)
    public static class EventHandler {

        @SubscribeEvent
        public static void poorKitty(PlayerInteractEvent.EntityInteractSpecific event) {
            if (!canObtain.get()) return;

            Player player = event.getEntity();
            Entity target = event.getTarget();
            ItemStack stack = event.getItemStack();
            if (stack.getItem() == Items.STICK) {
                if ((target instanceof Ocelot ocelot && !ocelot.isBaby()) || (target instanceof Cat cat && !(cat.isTame() || cat.isBaby()))) {
                    stack.shrink(1);
                    player.addItem(new ItemStack(ItemSetup.CAT_STICK.get()));
                    target.discard();
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 20, 1.1f);
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 10, 1.3f);
                    event.getLevel().playSound(player, target.getX(), target.getY(), target.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 5, 1.5f);
                }
            }
        }

    }
}
