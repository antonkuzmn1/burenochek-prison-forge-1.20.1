package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ShowerBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.ShowerBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ShowerBlockRenderer extends GeoBlockRenderer<ShowerBlockEntity> {
    public ShowerBlockRenderer() {
        super(new ShowerBlockModel());
    }

    @Override
    public RenderType getRenderType(
            ShowerBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
