package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.PrisonDoorItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.PrisonDoorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PrisonDoorItemRenderer extends GeoItemRenderer<PrisonDoorItem> {
    public PrisonDoorItemRenderer() {
        super(new PrisonDoorItemModel());
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

        float bScale = PrisonDoorItemRenderState.baseScale;
        float bTX = PrisonDoorItemRenderState.baseTX;
        float bTY = PrisonDoorItemRenderState.baseTY;
        float bTZ = PrisonDoorItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = PrisonDoorItemRenderState.guiScale;
            float tx = PrisonDoorItemRenderState.guiTX;
            float ty = PrisonDoorItemRenderState.guiTY;
            float rx = PrisonDoorItemRenderState.guiRX;
            float ry = PrisonDoorItemRenderState.guiRY;
            float rz = PrisonDoorItemRenderState.guiRZ;
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
            float scale = PrisonDoorItemRenderState.tpScale;
            float tx = PrisonDoorItemRenderState.tpTX;
            float ty = PrisonDoorItemRenderState.tpTY;
            float tz = PrisonDoorItemRenderState.tpTZ;
            float rx = PrisonDoorItemRenderState.tpRX;
            float ry = PrisonDoorItemRenderState.tpRY;
            float rz = PrisonDoorItemRenderState.tpRZ;
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
            float scale = PrisonDoorItemRenderState.fpScale;
            float tx = PrisonDoorItemRenderState.fpTX;
            float ty = PrisonDoorItemRenderState.fpTY;
            float tz = PrisonDoorItemRenderState.fpTZ;
            float rx = PrisonDoorItemRenderState.fpRX;
            float ry = PrisonDoorItemRenderState.fpRY;
            float rz = PrisonDoorItemRenderState.fpRZ;
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
