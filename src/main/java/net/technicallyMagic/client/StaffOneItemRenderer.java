package net.technicallyMagic.client;

import net.technicallyMagic.item.custom.staffOneItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StaffOneItemRenderer extends GeoItemRenderer<staffOneItem> {
    public StaffOneItemRenderer() {
        //super(new DefaultedItemGeoModel<>(new ResourceLocation(TechnicallyMagic.MOD_ID, "staff1_test")));
        super(new StaffOneItemModel());
    }
}
