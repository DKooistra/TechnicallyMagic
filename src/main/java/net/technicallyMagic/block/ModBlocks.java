package net.technicallyMagic.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.block.custom.RunestoneBlock;
import net.technicallyMagic.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TechnicallyMagic.MOD_ID);

    public static final RegistryObject<Block> ELDER_COPPER_BLOCK = registerBlock("elder_copper_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> AETHERITE_BLOCK = registerBlock("aetherite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
                    .strength(60.0F, 2000.0F)));

    public static final RegistryObject<Block> RUNESTONE_BLOCK = registerBlock("runestone_block",
            () -> new RunestoneBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_BLOCK).sound(SoundType.AMETHYST)
                    .strength(10.0F, 10.0F)));



    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
