package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.PrisonLampItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrisonLampItemModel extends GeoModel<PrisonLampItem> {
    @Override
    public ResourceLocation getModelResource(PrisonLampItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_lamp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PrisonLampItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_lamp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PrisonLampItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/prison_lamp.animation.json");
    }
}
