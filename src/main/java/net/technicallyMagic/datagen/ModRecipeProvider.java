package net.technicallyMagic.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.technicallyMagic.block.ModBlocks;
import net.technicallyMagic.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    //List of blocks to be turned into 9 single materials
    private static final List<ItemLike> MATERIAL_BLOCKS = List.of(
            ModBlocks.AETHERITE_BLOCK.get(),
            ModBlocks.RUNESTONE_BLOCK.get(),
            ModBlocks.ELDER_COPPER_BLOCK.get());

    //List of materials to be turned into 1 single block (same order as list above)
    //Need to change second entry to be runestone rather than aetherite
    private static final List<ItemLike> MATERIAL_ITEMS = List.of(
            ModItems.AETHERITE_INGOT.get(),
            ModItems.AETHERITE_INGOT.get(),
            ModItems.ELDER_COPPER.get());



    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //For each entry in mat blocks (and items) add two recipes for them
        for (int i = 0; i < MATERIAL_BLOCKS.size(); i++) {
            fillBench(consumer, RecipeCategory.MISC, MATERIAL_ITEMS.get(i), MATERIAL_BLOCKS.get(i));
            oneToNine(consumer, RecipeCategory.MISC, MATERIAL_BLOCKS.get(i), MATERIAL_ITEMS.get(i));
        }


    }

    protected static void oneToNine(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike in, ItemLike out) {
        ShapelessRecipeBuilder.shapeless(category, out, 9)
                .requires(in)
                .unlockedBy(getHasName(in), has(in))
                .save(consumer);
    }

    protected static void fillBench(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike in, ItemLike out) {
        ShapedRecipeBuilder.shaped(category, out)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', in)
                .unlockedBy(getHasName(in), has(in))
                .save(consumer);
    }

}
