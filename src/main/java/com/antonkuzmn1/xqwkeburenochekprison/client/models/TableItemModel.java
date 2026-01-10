package com.antonkuzmn1.xqwkeburenochekprison.client.models;

import com.antonkuzmn1.xqwkeburenochekprison.items.TableItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TableItemModel extends GeoModel<TableItem> {
    @Override
    public ResourceLocation getModelResource(TableItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/table.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TableItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/table.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TableItem animatable) {
        return ResourceLocation.tryParse("xqwkeburenochekprison:geo/animations/table.animation.json");
    }
}
