package dev.quarris.stickutils.datagen.client;

import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsLangGen extends LanguageProvider {

    public EnUsLangGen(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ItemSetup.CRAFTING_STICK.get(), "Crafting Table on a Stick");
        this.add(ItemSetup.CAT_STICK.get(), "Cat on a Stick");
        this.add(ItemSetup.CREEPER_STICK.get(), "Creeper on a Stick");
    }
}
