package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonDoorBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_door.PrisonDoorBlock;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrisonDoorBlockModel extends GeoModel<PrisonDoorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(PrisonDoorBlockEntity animatable) {
        assert animatable.getLevel() != null;
        var state = animatable.getLevel().getBlockState(animatable.getBlockPos());
        if (state.getBlock() instanceof PrisonDoorBlock) {
            boolean chair = state.getValue(PrisonDoorBlock.OPENED);
            boolean shutter = state.getValue(PrisonDoorBlock.SHUTTER);
            if (chair && shutter) {
                return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_door_opened_with_shutter.geo.json");
            } else if (shutter) {
                return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_door_with_shutter.geo.json");
            } else if (chair) {
                return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_door_opened.geo.json");
            }
        }
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
