package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.SoapItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SoapItemModel extends GeoModel<SoapItem> {
    @Override
    public ResourceLocation getModelResource(SoapItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/soap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoapItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/soap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoapItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/soap.animation.json");
    }
}
