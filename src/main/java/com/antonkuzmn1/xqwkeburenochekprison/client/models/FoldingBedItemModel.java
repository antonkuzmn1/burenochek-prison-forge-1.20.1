package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.FoldingBedItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FoldingBedItemModel extends GeoModel<FoldingBedItem> {
    @Override
    public ResourceLocation getModelResource(FoldingBedItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/folding_bed.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FoldingBedItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/folding_bed.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FoldingBedItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/folding_bed.animation.json");
    }
}
