package dev.quarris.stickutils.datagen.server;

import dev.quarris.stickutils.registry.TagSetup;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTagGen extends EntityTypeTagsProvider {

    public EntityTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookup, modId, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        this.tag(TagSetup.Entities.LEAD_STICK_BLACKLIST).addTags(Tags.EntityTypes.BOSSES);
    }
}
