package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.PrisonDoorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrisonDoorItemModel extends GeoModel<PrisonDoorItem> {
    @Override
    public ResourceLocation getModelResource(PrisonDoorItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_door.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PrisonDoorItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_door.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PrisonDoorItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/prison_door.animation.json");
    }
}
