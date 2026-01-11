package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonLampBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_lamp.PrisonLampBlock;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrisonLampBlockModel extends GeoModel<PrisonLampBlockEntity> {
    @Override
    public ResourceLocation getModelResource(PrisonLampBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/prison_lamp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PrisonLampBlockEntity animatable) {
        return switch (getLight(animatable)) {
            case 2 -> ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_lamp_light_on.png");
            case 1 -> ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_lamp_light_part.png");
            default -> ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/prison_lamp.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(PrisonLampBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/prison_lamp.animation.json");
    }

    private int getLight(PrisonLampBlockEntity animatable) {
        assert animatable.getLevel() != null;
        var state = animatable.getLevel().getBlockState(animatable.getBlockPos());
        if (state.getBlock() instanceof PrisonLampBlock) {
            return state.getValue(PrisonLampBlock.LIGHT);
        }
        return 0;
    }
}
