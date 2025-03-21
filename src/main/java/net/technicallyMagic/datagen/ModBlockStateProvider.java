package net.technicallyMagic.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TechnicallyMagic.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Register Blocks
        blockWithItem(ModBlocks.AETHERITE_BLOCK);
        blockWithItem(ModBlocks.ELDER_COPPER_BLOCK);
        blockWithItem(ModBlocks.RUNESTONE_BLOCK);
        blockWithItem(ModBlocks.THAUMIC_METAL_BLOCK);
        blockWithItem(ModBlocks.ENCHANCIUM_BLOCK);
        blockWithItem(ModBlocks.ETHERSHARD_BLOCK);
        blockWithItem(ModBlocks.WYRMSHARD_BLOCK);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
