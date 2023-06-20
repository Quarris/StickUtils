package dev.quarris.stickutils.datagen.server;

import dev.quarris.stickutils.registry.ItemSetup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    public RecipeGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> builder) {
        utilityStick(builder, ItemSetup.CRAFTING_STICK.get(), Items.CRAFTING_TABLE);
        utilityStick(builder, ItemSetup.LEAD_STICK.get(), Items.LEAD);
    }

    private static void utilityStick(Consumer<FinishedRecipe> builder, Item stick, ItemLike material) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, stick)
            .requires(Tags.Items.RODS_WOODEN)
            .requires(material)
            .group("utility_sticks")
            .unlockedBy("has_material", has(material))
            .save(builder);
    }
}
