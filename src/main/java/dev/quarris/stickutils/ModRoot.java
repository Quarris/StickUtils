package dev.quarris.stickutils;

import dev.quarris.stickutils.config.CommonConfigs;
import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModRef.ID)
public class ModRoot {

    public ModRoot() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        CommonConfigs.register(builder);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemSetup.init(modBus);
    }
}
