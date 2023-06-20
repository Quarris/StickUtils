package dev.quarris.stickutils.items;

import dev.quarris.stickutils.registry.TagSetup;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LeadStick extends UtilityStick {

    public LeadStick(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (hasMob(stack)) {
            return super.interactLivingEntity(stack, player, target, hand);
        }

        // If the item is one in a stack we can just set the tag onto it.
        ItemStack capturedLeadStick = captureMob(stack.getCount() == 1 ? stack : stack.copy(), target);
        if (capturedLeadStick.isEmpty()) {
            player.displayClientMessage(Component.translatable("stick.lead.cannot_capture").withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }

        if (!player.level().isClientSide() && stack.getCount() > 1 || player.getAbilities().instabuild) {
            player.addItem(capturedLeadStick);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(player.level().isClientSide());
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (!hasMob(ctx.getItemInHand())) {
            ctx.getPlayer().displayClientMessage(Component.translatable("stick.lead.empty").withStyle(ChatFormatting.RED), true);
            return InteractionResult.PASS;
        }

        if (!ctx.getLevel().isClientSide() && !loadMob(ctx.getLevel(), ctx.getItemInHand(), ctx.getClickedPos().relative(ctx.getClickedFace()))) {
            ctx.getPlayer().displayClientMessage(Component.translatable("stick.lead.cannot_place").withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }


        return InteractionResult.sidedSuccess(ctx.getLevel().isClientSide());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasMob(stack);
    }

    private static boolean hasMob(ItemStack stack) {
        return stack.getTagElement("Mob") != null;
    }

    private static ItemStack captureMob(ItemStack stack, LivingEntity target) {
        if (target.getType().is(TagSetup.Entities.LEAD_STICK_BLACKLIST)) {
            return ItemStack.EMPTY;
        }
        CompoundTag dataTag = target.serializeNBT();
        stack.addTagElement("Mob", dataTag);
        stack.setCount(1);
        if (!target.level().isClientSide()) {
            target.discard();
        }
        return stack;
    }

    private static boolean loadMob(Level level, ItemStack stack, BlockPos pos) {
        CompoundTag dataTag = stack.getTagElement("Mob");
        if (dataTag == null) {
            return false;
        }

        boolean placed = EntityType.create(dataTag, level).map(e -> {
            e.setPos(Vec3.atBottomCenterOf(pos));
            return level.addFreshEntity(e);
        }).orElse(false);

        if (!placed) {
            return false;
        }

        stack.removeTagKey("Mob");
        return true;
    }
}
