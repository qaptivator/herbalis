package com.captivator.herbalis;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class NettleBlock extends BushBlock {
    public NettleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8D, 0.75D, 0.8D));
            if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    entity.hurt(DamageSource.SWEET_BERRY_BUSH, 1.0F);
                }
            }
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, net.minecraft.world.entity.player.Player player) {
        // We only care about logic on the server side
        if (!level.isClientSide) {
            ItemStack tool = player.getMainHandItem();

            // Check if the player is NOT using Shears
            // We use the Tag for better mod compatibility (e.g., thermal or copper shears)
            if (!tool.is(net.minecraftforge.common.Tags.Items.SHEARS)) {
                // Deal damage. You can use SWEET_BERRY_BUSH or MAGIC/GENERIC
                player.hurt(DamageSource.CACTUS, 3.0F); // 1 full heart

                // Optional: Add a "sting" sound effect
                // net.minecraft.world.item.enchantment.Enchantments.THORNS > 0 ? net.minecraft.sounds.SoundEvents.THORNS_HIT : net.minecraft.sounds.SoundEvents.PLAYER_HURT
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.THORNS_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}
