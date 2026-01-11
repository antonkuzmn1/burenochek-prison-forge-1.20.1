package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonLampBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.PrisonLampItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.PrisonLampItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import javax.annotation.Nullable;

public class PrisonLampItemRenderer extends GeoItemRenderer<PrisonLampItem> {
    public PrisonLampItemRenderer() {
        super(new PrisonLampItemModel());
    }

    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext transformType,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay
    ) {
        poseStack.pushPose();

        float bScale = PrisonLampItemRenderState.baseScale;
        float bTX = PrisonLampItemRenderState.baseTX;
        float bTY = PrisonLampItemRenderState.baseTY;
        float bTZ = PrisonLampItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = PrisonLampItemRenderState.guiScale;
            float tx = PrisonLampItemRenderState.guiTX;
            float ty = PrisonLampItemRenderState.guiTY;
            float rx = PrisonLampItemRenderState.guiRX;
            float ry = PrisonLampItemRenderState.guiRY;
            float rz = PrisonLampItemRenderState.guiRZ;
            poseStack.scale(scale, scale, scale);
            poseStack.translate(tx, ty, 0f);
            poseStack.mulPose(new Quaternionf().rotateXYZ(
                    (float) Math.toRadians(rx),
                    (float) Math.toRadians(ry),
                    (float) Math.toRadians(rz)
            ));
        }

        if (transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND
                || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND
        ) {
            float scale = PrisonLampItemRenderState.tpScale;
            float tx = PrisonLampItemRenderState.tpTX;
            float ty = PrisonLampItemRenderState.tpTY;
            float tz = PrisonLampItemRenderState.tpTZ;
            float rx = PrisonLampItemRenderState.tpRX;
            float ry = PrisonLampItemRenderState.tpRY;
            float rz = PrisonLampItemRenderState.tpRZ;
            poseStack.scale(scale, scale, scale);
            poseStack.translate(tx, ty, tz);
            poseStack.mulPose(new Quaternionf().rotateXYZ(
                    (float) Math.toRadians(rx),
                    (float) Math.toRadians(ry),
                    (float) Math.toRadians(rz)
            ));
        }

        if (transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND
        ) {
            float scale = PrisonLampItemRenderState.fpScale;
            float tx = PrisonLampItemRenderState.fpTX;
            float ty = PrisonLampItemRenderState.fpTY;
            float tz = PrisonLampItemRenderState.fpTZ;
            float rx = PrisonLampItemRenderState.fpRX;
            float ry = PrisonLampItemRenderState.fpRY;
            float rz = PrisonLampItemRenderState.fpRZ;
            poseStack.scale(scale, scale, scale);
            poseStack.translate(tx, ty, tz);
            poseStack.mulPose(new Quaternionf().rotateXYZ(
                    (float) Math.toRadians(rx),
                    (float) Math.toRadians(ry),
                    (float) Math.toRadians(rz)
            ));
        }

        super.renderByItem(stack, transformType, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }

    @Override
    public RenderType getRenderType(
            PrisonLampItem animatable,
            ResourceLocation texture,
            @Nullable MultiBufferSource bufferSource,
            float partialTick
    ) {
        return RenderType.entityTranslucent(texture);
    }
}
