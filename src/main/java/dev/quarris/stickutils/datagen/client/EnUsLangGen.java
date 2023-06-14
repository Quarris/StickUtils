package dev.quarris.stickutils.datagen.client;

import dev.quarris.stickutils.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsLangGen extends LanguageProvider {

    public EnUsLangGen(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ModItems.CRAFTING_STICK.get(), "Crafting Table on a Stick");
        this.add(ModItems.CAT_STICK.get(), "Cat on a Stick");
    }
}
