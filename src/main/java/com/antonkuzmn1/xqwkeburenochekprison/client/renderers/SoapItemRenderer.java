package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.SoapItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.SoapItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SoapItemRenderer extends GeoItemRenderer<SoapItem> {
    public SoapItemRenderer() {
        super(new SoapItemModel());
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

        float bScale = SoapItemRenderState.baseScale;
        float bTX = SoapItemRenderState.baseTX;
        float bTY = SoapItemRenderState.baseTY;
        float bTZ = SoapItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = SoapItemRenderState.guiScale;
            float tx = SoapItemRenderState.guiTX;
            float ty = SoapItemRenderState.guiTY;
            float rx = SoapItemRenderState.guiRX;
            float ry = SoapItemRenderState.guiRY;
            float rz = SoapItemRenderState.guiRZ;
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
            float scale = SoapItemRenderState.tpScale;
            float tx = SoapItemRenderState.tpTX;
            float ty = SoapItemRenderState.tpTY;
            float tz = SoapItemRenderState.tpTZ;
            float rx = SoapItemRenderState.tpRX;
            float ry = SoapItemRenderState.tpRY;
            float rz = SoapItemRenderState.tpRZ;
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
            float scale = SoapItemRenderState.fpScale;
            float tx = SoapItemRenderState.fpTX;
            float ty = SoapItemRenderState.fpTY;
            float tz = SoapItemRenderState.fpTZ;
            float rx = SoapItemRenderState.fpRX;
            float ry = SoapItemRenderState.fpRY;
            float rz = SoapItemRenderState.fpRZ;
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
}
