package com.captivator.herbalis;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

public class DryingRackBlockEntityRenderer implements BlockEntityRenderer<DryingRackBlockEntity> {
    private final ItemRenderer itemRenderer;

    public DryingRackBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DryingRackBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        ItemStack stack = blockEntity.getItem();
        if (stack.isEmpty()) return;

        poseStack.pushPose();
        
        Direction facing = blockEntity.getBlockState().getValue(DryingRackBlock.FACING);
        
        // Center of the block
        poseStack.translate(0.5, 0.7, 0.5);

        // Rotate based on facing
        float rotation = 0f;
        switch (facing) {
            case SOUTH: rotation = 180f; break;
            case WEST: rotation = 90f; break;
            case EAST: rotation = -90f; break;
            case NORTH:
            default: rotation = 0f; break;
        }
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));

        // Move to the rack surface (at the North face of the local space)
        poseStack.translate(0.0, 0.0, -0.4);
        
        // Rotate the item itself 180 degrees so it faces the correct way
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180f));
        
        // Scale down for the rack
        poseStack.scale(0.5f, 0.5f, 0.5f);

        this.itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, bufferSource, (int) blockEntity.getBlockPos().asLong());

        poseStack.popPose();
    }
}
