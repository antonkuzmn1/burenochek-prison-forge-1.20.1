package com.antonkuzmn1.xqwkeburenochekprison.client.renderers;

import com.antonkuzmn1.xqwkeburenochekprison.client.models.TableItemModel;
import com.antonkuzmn1.xqwkeburenochekprison.items.TableItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TableItemRenderer extends GeoItemRenderer<TableItem> {
    public TableItemRenderer() {
        super(new TableItemModel());
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

        float bScale = TableItemRenderState.baseScale;
        float bTX = TableItemRenderState.baseTX;
        float bTY = TableItemRenderState.baseTY;
        float bTZ = TableItemRenderState.baseTZ;
        poseStack.scale(bScale, bScale, bScale);
        poseStack.translate(bTX, bTY, bTZ);

        if (transformType == ItemDisplayContext.GUI) {
            float scale = TableItemRenderState.guiScale;
            float tx = TableItemRenderState.guiTX;
            float ty = TableItemRenderState.guiTY;
            float rx = TableItemRenderState.guiRX;
            float ry = TableItemRenderState.guiRY;
            float rz = TableItemRenderState.guiRZ;
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
            float scale = TableItemRenderState.tpScale;
            float tx = TableItemRenderState.tpTX;
            float ty = TableItemRenderState.tpTY;
            float tz = TableItemRenderState.tpTZ;
            float rx = TableItemRenderState.tpRX;
            float ry = TableItemRenderState.tpRY;
            float rz = TableItemRenderState.tpRZ;
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
            float scale = TableItemRenderState.fpScale;
            float tx = TableItemRenderState.fpTX;
            float ty = TableItemRenderState.fpTY;
            float tz = TableItemRenderState.fpTZ;
            float rx = TableItemRenderState.fpRX;
            float ry = TableItemRenderState.fpRY;
            float rz = TableItemRenderState.fpRZ;
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
