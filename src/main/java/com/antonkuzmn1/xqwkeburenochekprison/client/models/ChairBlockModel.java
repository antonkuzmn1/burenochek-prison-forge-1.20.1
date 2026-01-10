package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ChairBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChairBlockModel extends GeoModel<ChairBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ChairBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/chair.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChairBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/chair.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChairBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/chair.animation.json");
    }
}
