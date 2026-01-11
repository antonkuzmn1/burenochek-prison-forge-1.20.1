package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ShowerBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShowerBlockModel extends GeoModel<ShowerBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ShowerBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/shower.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShowerBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/shower.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShowerBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/shower.animation.json");
    }
}
