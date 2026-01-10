package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.TableBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TableBlockModel extends GeoModel<TableBlockEntity> {
    @Override
    public ResourceLocation getModelResource(TableBlockEntity animatable) {
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
