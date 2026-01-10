package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ChairBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.ChairBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ChairBlockRenderer extends GeoBlockRenderer<ChairBlockEntity> {
    public ChairBlockRenderer() {
        super(new ChairBlockModel());
    }

    @Override
    public RenderType getRenderType(
            ChairBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
