package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.ChairItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.ChairItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ChairItemRenderer extends GeoItemRenderer<ChairItem> {
    public ChairItemRenderer() {
        super(new ChairItemModel());
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

        float bScale = ChairItemRenderState.baseScale;
        float bTX = ChairItemRenderState.baseTX;
        float bTY = ChairItemRenderState.baseTY;
        float bTZ = ChairItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = ChairItemRenderState.guiScale;
            float tx = ChairItemRenderState.guiTX;
            float ty = ChairItemRenderState.guiTY;
            float rx = ChairItemRenderState.guiRX;
            float ry = ChairItemRenderState.guiRY;
            float rz = ChairItemRenderState.guiRZ;
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
            float scale = ChairItemRenderState.tpScale;
            float tx = ChairItemRenderState.tpTX;
            float ty = ChairItemRenderState.tpTY;
            float tz = ChairItemRenderState.tpTZ;
            float rx = ChairItemRenderState.tpRX;
            float ry = ChairItemRenderState.tpRY;
            float rz = ChairItemRenderState.tpRZ;
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
            float scale = ChairItemRenderState.fpScale;
            float tx = ChairItemRenderState.fpTX;
            float ty = ChairItemRenderState.fpTY;
            float tz = ChairItemRenderState.fpTZ;
            float rx = ChairItemRenderState.fpRX;
            float ry = ChairItemRenderState.fpRY;
            float rz = ChairItemRenderState.fpRZ;
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
