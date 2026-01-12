package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.SoapBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.SoapBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SoapBlockRenderer extends GeoBlockRenderer<SoapBlockEntity> {
    public SoapBlockRenderer() {
        super(new SoapBlockModel());
    }

    @Override
    public RenderType getRenderType(
            SoapBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
