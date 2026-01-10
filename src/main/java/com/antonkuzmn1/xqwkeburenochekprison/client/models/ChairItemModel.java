package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.ChairItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChairItemModel extends GeoModel<ChairItem> {
    @Override
    public ResourceLocation getModelResource(ChairItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/chair.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChairItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/chair.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChairItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/chair.animation.json");
    }
}
