package net.technicallyMagic.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TechnicallyMagic.MOD_ID);

    public static final RegistryObject<CreativeModeTab> Tech_Magic_Tab = CREATIVE_MODE_TABS.register("tech_magic_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AETHERITE_INGOT.get()))
                    .title(Component.translatable("creativetab.tech_magic_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ELDER_COPPER.get());
                        output.accept(ModBlocks.ELDER_COPPER_BLOCK.get());
                        output.accept(ModItems.RUNESTONE.get());
                        output.accept(ModBlocks.RUNESTONE_BLOCK.get());
                        output.accept(ModItems.THAUMIC_METAL_INGOT.get());
                        output.accept(ModBlocks.THAUMIC_METAL_BLOCK.get());
                        output.accept(ModItems.ENCHANCIUM_INGOT.get());
                        output.accept(ModBlocks.ENCHANCIUM_BLOCK.get());
                        output.accept(ModItems.ETHERSHARD.get());
                        output.accept(ModBlocks.ETHERSHARD_BLOCK.get());
                        output.accept(ModItems.WYRMSHARD.get());
                        output.accept(ModBlocks.WYRMSHARD_BLOCK.get());
                        output.accept(ModItems.AETHERITE_INGOT.get());
                        output.accept(ModBlocks.AETHERITE_BLOCK.get());
                        output.accept(ModItems.BLOCK_DETECTOR.get());
                        output.accept(ModItems.STAFF1_TEST.get());
                        output.accept(ModItems.ENCHANCIUM_CARROT.get());
                        output.accept(ModItems.KILL_PROJECTILE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
