package dev.quarris.stickutils.datagen;

import dev.quarris.stickutils.ModRef;
import dev.quarris.stickutils.datagen.client.EnUsLangGen;
import dev.quarris.stickutils.datagen.client.ItemModelGen;
import dev.quarris.stickutils.datagen.server.DamageTagsGen;
import dev.quarris.stickutils.datagen.server.EntityTagGen;
import dev.quarris.stickutils.datagen.server.RecipeGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvents {

    @SubscribeEvent
    public static void gatherDataGens(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();

        // Client
        gen.addProvider(event.includeClient(), new ItemModelGen(output, ModRef.ID, event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new EnUsLangGen(output, ModRef.ID, "en_us"));

        // Server
        gen.addProvider(event.includeServer(), new RecipeGen(output));
        gen.addProvider(event.includeServer(), new DamageTagsGen(output, event.getLookupProvider(), ModRef.ID, event.getExistingFileHelper()));
        gen.addProvider(event.includeServer(), new EntityTagGen(output, event.getLookupProvider(), ModRef.ID, event.getExistingFileHelper()));
    }

}
