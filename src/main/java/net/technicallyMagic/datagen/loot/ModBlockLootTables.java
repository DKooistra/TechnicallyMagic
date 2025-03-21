package net.technicallyMagic.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.AETHERITE_BLOCK.get());
        this.dropSelf(ModBlocks.ELDER_COPPER_BLOCK.get());
        this.dropSelf(ModBlocks.RUNESTONE_BLOCK.get());
        this.dropSelf(ModBlocks.THAUMIC_METAL_BLOCK.get());
        this.dropSelf(ModBlocks.ENCHANCIUM_BLOCK.get());
        this.dropSelf(ModBlocks.ETHERSHARD_BLOCK.get());
        this.dropSelf(ModBlocks.WYRMSHARD_BLOCK.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
