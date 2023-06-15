package dev.quarris.stickutils.datagen;

import dev.quarris.stickutils.ModRef;
import dev.quarris.stickutils.datagen.client.EnUsLangGen;
import dev.quarris.stickutils.datagen.client.ItemModelGen;
import dev.quarris.stickutils.datagen.server.DamageTagsGen;
import dev.quarris.stickutils.datagen.server.RecipeGen;
import dev.quarris.stickutils.registry.ModDamages;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvents {

    @SubscribeEvent
    public static void gatherDataGens(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        gen.addProvider(event.includeClient(), new ItemModelGen(output, ModRef.ID, event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new EnUsLangGen(output, ModRef.ID, "en_us"));

        gen.addProvider(event.includeServer(), new RecipeGen(output));
        gen.addProvider(event.includeServer(), new DamageTagsGen(output, event.getLookupProvider(), ModRef.ID, event.getExistingFileHelper()));
    }

}
