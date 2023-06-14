package dev.quarris.stickutils.items;

import dev.quarris.stickutils.ModRef;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModRef.ID);

    public static final RegistryObject<Item> CRAFTING_STICK = ITEMS.register("crafting_stick", () -> new CraftingStick(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CAT_STICK = ITEMS.register("cat_stick", () -> new CatStick(new Item.Properties().durability(100)));

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
