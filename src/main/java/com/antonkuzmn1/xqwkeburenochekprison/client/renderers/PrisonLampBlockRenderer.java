package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonLampBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.PrisonLampBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PrisonLampBlockRenderer extends GeoBlockRenderer<PrisonLampBlockEntity> {
    public PrisonLampBlockRenderer() {
        super(new PrisonLampBlockModel());
    }

    @Override
    public RenderType getRenderType(
            PrisonLampBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
