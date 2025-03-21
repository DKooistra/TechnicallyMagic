package net.technicallyMagic.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TechnicallyMagic.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.AETHERITE_INGOT);
        simpleItem(ModItems.ELDER_COPPER);
        simpleItem(ModItems.RUNESTONE);
        simpleItem(ModItems.THAUMIC_METAL_INGOT);
        simpleItem(ModItems.ENCHANCIUM_INGOT);
        simpleItem(ModItems.ETHERSHARD);
        simpleItem(ModItems.WYRMSHARD);
        simpleItem(ModItems.ENCHANCIUM_CARROT);
        simpleItem(ModItems.BLOCK_DETECTOR);
        simpleItem(ModItems.KILL_PROJECTILE);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TechnicallyMagic.MOD_ID, "item/" + item.getId().getPath()));
    }
}
