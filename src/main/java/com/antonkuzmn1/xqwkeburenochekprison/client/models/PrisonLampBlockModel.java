package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonLampBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrisonLampBlockModel extends GeoModel<PrisonLampBlockEntity> {
    @Override
    public ResourceLocation getModelResource(PrisonLampBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_lamp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PrisonLampBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_lamp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PrisonLampBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/prison_lamp.animation.json");
    }
}
