package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.ShowerItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShowerItemModel extends GeoModel<ShowerItem> {
    @Override
    public ResourceLocation getModelResource(ShowerItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/shower.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShowerItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/shower.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShowerItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/shower.animation.json");
    }
}
