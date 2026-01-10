package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.ParashaBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.ParashaCisternGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.ParashaRimGhostBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            XqwkeBurenochekPrisonMod.MODID
    );

    public static final RegistryObject<ParashaBlock> PARASHA = BLOCKS.register(
            "parasha",
            ParashaBlock::new
    );

    public static final RegistryObject<ParashaRimGhostBlock> PARASHA_RIM_GHOST = BLOCKS.register(
            "parasha_rim_ghost",
            ParashaRimGhostBlock::new
    );

    public static final RegistryObject<ParashaCisternGhostBlock> PARASHA_CISTERN_GHOST = BLOCKS.register(
            "parasha_cistern_ghost",
            ParashaCisternGhostBlock::new
    );
}
