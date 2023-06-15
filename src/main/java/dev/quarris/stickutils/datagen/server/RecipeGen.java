package dev.quarris.stickutils.datagen.server;

import dev.quarris.stickutils.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    public RecipeGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> builder) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.CRAFTING_STICK.get())
            .requires(Tags.Items.RODS_WOODEN)
            .requires(Items.CRAFTING_TABLE)
            .group("utility_sticks")
            .unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
            .save(builder);
    }
}
