package net.technicallyMagic.client;

import net.minecraft.resources.ResourceLocation;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.item.custom.staffOneItem;
import software.bernie.geckolib.model.GeoModel;

public class StaffOneItemModel extends GeoModel<staffOneItem> {
    @Override
    public ResourceLocation getModelResource(staffOneItem staffOneItem) {
        return new ResourceLocation(TechnicallyMagic.MOD_ID, "geo/item/staff1_test.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(staffOneItem staffOneItem) {
        return new ResourceLocation(TechnicallyMagic.MOD_ID, "textures/item/staff1_test.png");
    }

    @Override
    public ResourceLocation getAnimationResource(staffOneItem staffOneItem) {
        return new ResourceLocation(TechnicallyMagic.MOD_ID, "animations/item/staff1_test.animation.json");
    }
}
