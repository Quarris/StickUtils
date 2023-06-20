package dev.quarris.stickutils.datagen.client;

import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGen extends ItemModelProvider {

    public ItemModelGen(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(ItemSetup.CRAFTING_STICK.get());
        this.basicItem(ItemSetup.CAT_STICK.get());
        this.basicItem(ItemSetup.CREEPER_STICK.get());
        this.basicItem(ItemSetup.LEAD_STICK.get());
    }
}
