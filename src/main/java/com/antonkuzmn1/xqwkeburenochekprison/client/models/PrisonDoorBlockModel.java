package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonDoorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrisonDoorBlockModel extends GeoModel<PrisonDoorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(PrisonDoorBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_door.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PrisonDoorBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_door.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PrisonDoorBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/prison_door.animation.json");
    }
}
