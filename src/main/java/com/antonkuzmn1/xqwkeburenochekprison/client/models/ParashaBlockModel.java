package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ParashaBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ParashaBlockModel extends GeoModel<ParashaBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ParashaBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/parasha.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParashaBlockEntity animatable) {
//        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/parasha.png");
        return ParashaBlockEntity.getCurrentFrameTexture(animatable.getBlockPos());
    }

    @Override
    public ResourceLocation getAnimationResource(ParashaBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/parasha.animation.json");
    }
}
