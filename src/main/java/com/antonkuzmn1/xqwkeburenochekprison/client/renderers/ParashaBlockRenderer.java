package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ParashaBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.ParashaBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ParashaBlockRenderer extends GeoBlockRenderer<ParashaBlockEntity> {
    public ParashaBlockRenderer() {
        super(new ParashaBlockModel());
    }

    @Override
    public RenderType getRenderType(
            ParashaBlockEntity animatable,
            ResourceLocation texture,
            MultiBufferSource buffer,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
