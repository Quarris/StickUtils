package dev.quarris.stickutils;

import dev.quarris.stickutils.registry.ModDamages;
import dev.quarris.stickutils.registry.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModRef.ID)
public class ModRoot {

    public ModRoot() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.init(modBus);
    }
}
