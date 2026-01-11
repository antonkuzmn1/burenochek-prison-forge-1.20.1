package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.FoldingBedBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.FoldingBedBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FoldingBedBlockRenderer extends GeoBlockRenderer<FoldingBedBlockEntity> {
    public FoldingBedBlockRenderer() {
        super(new FoldingBedBlockModel());
    }

    @Override
    public RenderType getRenderType(
            FoldingBedBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
