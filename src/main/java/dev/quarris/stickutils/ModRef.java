package dev.quarris.stickutils;

import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModRef {

    public static final String ID = "stickutils";
    public static final String NAME = "StickUtils";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static final CreativeModeTab TAB = CreativeModeTab.builder()
        .icon(() -> new ItemStack(ItemSetup.CRAFTING_STICK.get()))
        .title(Component.literal("Utilities on a Stick"))
        .displayItems((params, output) -> {
            ItemSetup.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
        })
        .build();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(ID, name);
    }
}
