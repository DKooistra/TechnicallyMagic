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
                        output.accept(ModItems.AETHERITE_INGOT.get());
                        output.accept(ModBlocks.AETHERITE_BLOCK.get());
                        output.accept(ModBlocks.RUNESTONE_BLOCK.get());
                        output.accept(ModItems.BLOCK_DETECTOR.get());
                        output.accept(ModItems.ENCHANCIUM_CARROT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
