package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.TableBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.table.TableBlock;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TableBlockModel extends GeoModel<TableBlockEntity> {
    @Override
    public ResourceLocation getModelResource(TableBlockEntity animatable) {
        assert animatable.getLevel() != null;
        var state = animatable.getLevel().getBlockState(animatable.getBlockPos());
        if (state.getBlock() instanceof TableBlock) {
            boolean chair = state.getValue(TableBlock.CHAIR);
            if (chair) {
                return ResourceLocation.tryParse("xqwkeburenochekprison:geo/table_with_chair.geo.json");
            }
        }
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/table.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TableBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/table.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TableBlockEntity animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/table.animation.json");
    }
}
