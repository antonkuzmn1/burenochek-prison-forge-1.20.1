package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonDoorBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.PrisonDoorBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PrisonDoorBlockRenderer extends GeoBlockRenderer<PrisonDoorBlockEntity> {
    public PrisonDoorBlockRenderer() {
        super(new PrisonDoorBlockModel());
    }

    @Override
    public RenderType getRenderType(
            PrisonDoorBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
