package dev.quarris.stickutils.datagen.server;

import dev.quarris.stickutils.registry.ModDamages;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DamageTagsGen extends TagsProvider<DamageType> {

    public DamageTagsGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, Registries.DAMAGE_TYPE, lookup, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(DamageTypeTags.IS_EXPLOSION).add(ModDamages.CREEPER_STICK);
    }
}
