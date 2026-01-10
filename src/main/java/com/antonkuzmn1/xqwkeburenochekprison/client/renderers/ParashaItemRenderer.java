package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.ParashaItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.ParashaItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ParashaItemRenderer extends GeoItemRenderer<ParashaItem> {
    public ParashaItemRenderer() {
        super(new ParashaItemModel());
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

        float bScale = ParashaItemRenderState.baseScale;
        float bTX = ParashaItemRenderState.baseTX;
        float bTY = ParashaItemRenderState.baseTY;
        float bTZ = ParashaItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = ParashaItemRenderState.guiScale;
            float tx = ParashaItemRenderState.guiTX;
            float ty = ParashaItemRenderState.guiTY;
            float rx = ParashaItemRenderState.guiRX;
            float ry = ParashaItemRenderState.guiRY;
            float rz = ParashaItemRenderState.guiRZ;
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
            float scale = ParashaItemRenderState.tpScale;
            float tx = ParashaItemRenderState.tpTX;
            float ty = ParashaItemRenderState.tpTY;
            float tz = ParashaItemRenderState.tpTZ;
            float rx = ParashaItemRenderState.tpRX;
            float ry = ParashaItemRenderState.tpRY;
            float rz = ParashaItemRenderState.tpRZ;
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
            float scale = ParashaItemRenderState.fpScale;
            float tx = ParashaItemRenderState.fpTX;
            float ty = ParashaItemRenderState.fpTY;
            float tz = ParashaItemRenderState.fpTZ;
            float rx = ParashaItemRenderState.fpRX;
            float ry = ParashaItemRenderState.fpRY;
            float rz = ParashaItemRenderState.fpRZ;
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
