package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.FoldingBedItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.FoldingBedItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FoldingBedItemRenderer extends GeoItemRenderer<FoldingBedItem> {
    public FoldingBedItemRenderer() {
        super(new FoldingBedItemModel());
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

        float bScale = FoldingBedItemRenderState.baseScale;
        float bTX = FoldingBedItemRenderState.baseTX;
        float bTY = FoldingBedItemRenderState.baseTY;
        float bTZ = FoldingBedItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = FoldingBedItemRenderState.guiScale;
            float tx = FoldingBedItemRenderState.guiTX;
            float ty = FoldingBedItemRenderState.guiTY;
            float rx = FoldingBedItemRenderState.guiRX;
            float ry = FoldingBedItemRenderState.guiRY;
            float rz = FoldingBedItemRenderState.guiRZ;
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
            float scale = FoldingBedItemRenderState.tpScale;
            float tx = FoldingBedItemRenderState.tpTX;
            float ty = FoldingBedItemRenderState.tpTY;
            float tz = FoldingBedItemRenderState.tpTZ;
            float rx = FoldingBedItemRenderState.tpRX;
            float ry = FoldingBedItemRenderState.tpRY;
            float rz = FoldingBedItemRenderState.tpRZ;
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
            float scale = FoldingBedItemRenderState.fpScale;
            float tx = FoldingBedItemRenderState.fpTX;
            float ty = FoldingBedItemRenderState.fpTY;
            float tz = FoldingBedItemRenderState.fpTZ;
            float rx = FoldingBedItemRenderState.fpRX;
            float ry = FoldingBedItemRenderState.fpRY;
            float rz = FoldingBedItemRenderState.fpRZ;
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
