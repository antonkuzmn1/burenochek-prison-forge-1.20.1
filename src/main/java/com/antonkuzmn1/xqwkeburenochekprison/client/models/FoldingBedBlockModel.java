package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.FoldingBedBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.folding_bed.FoldingBedBlock;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FoldingBedBlockModel extends GeoModel<FoldingBedBlockEntity> {
    @Override
    public ResourceLocation getModelResource(FoldingBedBlockEntity animatable) {
        if (getFolded(animatable)) {
            return ResourceLocation.tryParse("xqwkeburenochekprison:geo/folding_bed_folded.geo.json");
        }
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/folding_bed.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FoldingBedBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/folding_bed.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FoldingBedBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/folding_bed.animation.json");
    }

    private boolean getFolded(FoldingBedBlockEntity animatable) {
        assert animatable.getLevel() != null;
        var state = animatable.getLevel().getBlockState(animatable.getBlockPos());
        if (state.getBlock() instanceof FoldingBedBlock) {
            return state.getValue(FoldingBedBlock.FOLDED);
        }
        return false;
    }
}
