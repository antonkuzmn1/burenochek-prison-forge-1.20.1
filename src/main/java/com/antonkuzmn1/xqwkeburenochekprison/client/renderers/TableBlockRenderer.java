package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.TableBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.TableBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TableBlockRenderer extends GeoBlockRenderer<TableBlockEntity> {
    public TableBlockRenderer() {
        super(new TableBlockModel());
    }

    @Override
    public RenderType getRenderType(
            TableBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
