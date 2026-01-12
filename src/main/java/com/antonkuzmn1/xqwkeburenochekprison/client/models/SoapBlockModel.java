package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.SoapBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SoapBlockModel extends GeoModel<SoapBlockEntity> {
    @Override
    public ResourceLocation getModelResource(SoapBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/soap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoapBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/soap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoapBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/soap.animation.json");
    }
}
