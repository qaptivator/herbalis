package com.captivator.herbalis.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class MortarAndPestleBlockEntityRenderer implements BlockEntityRenderer<MortarAndPestleBlockEntity> {
    private final ItemRenderer itemRenderer;

    public MortarAndPestleBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(MortarAndPestleBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        ItemStack stack = blockEntity.getItem();
        if (stack.isEmpty()) return;

        poseStack.pushPose();
        
        // Position inside the mortar
        poseStack.translate(0.5, 0.325, 0.5);
        
        // Lie flat
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90f));
        
        // Scale down to fit
        poseStack.scale(0.4f, 0.4f, 0.4f);

        this.itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, (int) blockEntity.getBlockPos().asLong());

        poseStack.popPose();
    }
}
