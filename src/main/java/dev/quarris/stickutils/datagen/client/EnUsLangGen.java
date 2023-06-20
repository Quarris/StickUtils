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
        this.add(ItemSetup.LEAD_STICK.get(), "Lead on a Stick");

        this.add("stick.lead.cannot_capture", "You cannot capture this mob");
        this.add("stick.lead.cannot_place", "You cannot place this mob here");
        this.add("stick.lead.empty", "Lead on a Stick is empty");
    }
}
