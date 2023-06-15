package dev.quarris.stickutils;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerCreativeTabs(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, ModRef.res("tab"), () -> ModRef.TAB);
    }
}
