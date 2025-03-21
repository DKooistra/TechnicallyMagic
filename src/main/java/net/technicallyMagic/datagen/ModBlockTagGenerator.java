package net.technicallyMagic.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.block.ModBlocks;
import net.technicallyMagic.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TechnicallyMagic.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //Transmutable blocks tag creator
        this.tag(ModTags.Blocks.TRANSMUTABLE_BLOCKS)
                .add(
                        Blocks.COPPER_BLOCK,
                        Blocks.IRON_BLOCK,
                        Blocks.GOLD_BLOCK,
                        Blocks.REDSTONE_BLOCK,
                        Blocks.EMERALD_BLOCK,
                        Blocks.DIAMOND_BLOCK,
                        Blocks.NETHERITE_BLOCK);

        //Mineable with pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.RUNESTONE_BLOCK.get(),
                        ModBlocks.ELDER_COPPER_BLOCK.get(),
                        ModBlocks.THAUMIC_METAL_BLOCK.get(),
                        ModBlocks.ENCHANCIUM_BLOCK.get(),
                        ModBlocks.ETHERSHARD_BLOCK.get(),
                        ModBlocks.WYRMSHARD_BLOCK.get(),
                        ModBlocks.AETHERITE_BLOCK.get());


        //Additional blocks iron tool needed for
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.RUNESTONE_BLOCK.get(),
                        ModBlocks.ELDER_COPPER_BLOCK.get(),
                        ModBlocks.THAUMIC_METAL_BLOCK.get(),
                        ModBlocks.ENCHANCIUM_BLOCK.get());

        //Additional blocks diamond tool needed for
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(
                        ModBlocks.ETHERSHARD_BLOCK.get(),
                        ModBlocks.WYRMSHARD_BLOCK.get());

        //Additional blocks netherite tool needed for
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.AETHERITE_BLOCK.get());

    }
}
