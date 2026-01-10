package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.ParashaItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ParashaItemModel extends GeoModel<ParashaItem> {
    @Override
    public ResourceLocation getModelResource(ParashaItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/parasha.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParashaItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/parasha.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ParashaItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/parasha.animation.json");
    }
}
