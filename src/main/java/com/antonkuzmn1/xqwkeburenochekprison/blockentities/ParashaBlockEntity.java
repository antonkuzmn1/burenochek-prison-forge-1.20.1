package com.antonkuzmn1.xqwkeburenochekprison.blockentities;

import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlockEntities;
import com.antonkuzmn1.xqwkeburenochekprison.utils.ParashaFlushTicker;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;

public class ParashaBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final Map<BlockPos, ParashaFlushTicker> isPlaying = new HashMap<>();

    public ParashaBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PARASHA.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // pass
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition).inflate(3.0);
    }

    public static void playFlushAnimation(BlockPos pos) {
        System.out.println("playFlushAnimation");
        isPlaying.put(pos, new ParashaFlushTicker());
    }

    public static void tickAnimation() {
        var toRemove = new java.util.ArrayList<BlockPos>();

        isPlaying.forEach((pos, ticker) -> {
            ticker.incrementTick();
            if (ticker.getTick() >= ParashaFlushTicker.ticksPerFrame) {
                ticker.resetTick();
                ticker.incrementCurrentFrame();
                if (ticker.getCurrentFrame() > ParashaFlushTicker.framesCount) {
                    ticker.resetCurrentFrame();
                    toRemove.add(pos);
                }
            }
        });

        toRemove.forEach(isPlaying::remove);
    }

    public static ResourceLocation getCurrentFrameTexture(BlockPos pos) {
        ParashaFlushTicker ticker = isPlaying.get(pos);
        if (ticker != null && ticker.getCurrentFrame() > 0) {
            return ResourceLocation.tryParse("xqwkeburenochekprison:textures/animation/parasha_flush/" + ticker.getCurrentFrame() + ".png");
        }
        return ResourceLocation.tryParse("xqwkeburenochekprison:textures/block/parasha.png");
    }
}
