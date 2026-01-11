package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.PrisonDoorItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.client.models.ShowerItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.ShowerItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShowerItemRenderer extends GeoItemRenderer<ShowerItem> {
    public ShowerItemRenderer() {
        super(new ShowerItemModel());
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

        float bScale = ShowerItemRenderState.baseScale;
        float bTX = ShowerItemRenderState.baseTX;
        float bTY = ShowerItemRenderState.baseTY;
        float bTZ = ShowerItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = ShowerItemRenderState.guiScale;
            float tx = ShowerItemRenderState.guiTX;
            float ty = ShowerItemRenderState.guiTY;
            float rx = ShowerItemRenderState.guiRX;
            float ry = ShowerItemRenderState.guiRY;
            float rz = ShowerItemRenderState.guiRZ;
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
            float scale = ShowerItemRenderState.tpScale;
            float tx = ShowerItemRenderState.tpTX;
            float ty = ShowerItemRenderState.tpTY;
            float tz = ShowerItemRenderState.tpTZ;
            float rx = ShowerItemRenderState.tpRX;
            float ry = ShowerItemRenderState.tpRY;
            float rz = ShowerItemRenderState.tpRZ;
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
            float scale = ShowerItemRenderState.fpScale;
            float tx = ShowerItemRenderState.fpTX;
            float ty = ShowerItemRenderState.fpTY;
            float tz = ShowerItemRenderState.fpTZ;
            float rx = ShowerItemRenderState.fpRX;
            float ry = ShowerItemRenderState.fpRY;
            float rz = ShowerItemRenderState.fpRZ;
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
