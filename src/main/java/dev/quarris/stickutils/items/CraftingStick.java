package dev.quarris.stickutils.items;

import dev.quarris.stickutils.ModRef;
import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CraftingStick extends UtilityStick {

    public static ForgeConfigSpec.BooleanValue canObtain;

    public CraftingStick(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.openMenu(new SimpleMenuProvider((id, inv, p) -> new CraftingMenu(id, inv, ContainerLevelAccess.create(level, player.blockPosition())) {
            @Override
            public boolean stillValid(Player player) {
                return true;
            }
        }, Component.literal("Crafting Table on a Stick")));
        return super.use(level, player, hand);
    }

    public static void buildConfigs(ForgeConfigSpec.Builder builder) {
        builder.comment("Cat on a Stick").push("cat_stick");
        canObtain = builder.comment(
            "Can you obtain a Crafting Table on a Stick by Sneak & Right-Clicking a Crafting Table with a Stick?",
            "Set to false if you would like to effectively disable this in survival, or have a custom way of obtaining the item (eg. via recipe)"
        ).define("can_obtain", true);
        builder.pop();
    }

    @Mod.EventBusSubscriber(modid = ModRef.ID)
    public static class EventHandler {

        @SubscribeEvent
        public static void poorCreeper(PlayerInteractEvent.RightClickBlock event) {
            if (!canObtain.get()) return;

            if (!event.getEntity().isShiftKeyDown()) return; // Not sneaking
            if (!event.getLevel().getBlockState(event.getPos()).is(Blocks.CRAFTING_TABLE)) return; // Not a crafting table

            ItemStack held = event.getEntity().getItemInHand(event.getHand());
            if (!held.is(Tags.Items.RODS_WOODEN)) return; // Not a stick

            event.getLevel().removeBlock(event.getPos(), false);
            if (event.getEntity().getAbilities().instabuild) {
                held.shrink(1);
            }
            event.getEntity().getInventory().placeItemBackInInventory(new ItemStack(ItemSetup.CRAFTING_STICK.get()));
            event.setCanceled(true);
        }
    }
}
