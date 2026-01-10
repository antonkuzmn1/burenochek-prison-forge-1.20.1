package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha.ParashaBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha.ParashaCisternGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha.ParashaRimGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.table.TableBehindGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.table.TableBehindRightGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.table.TableBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.table.TableRightGhostBlock;
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

    public static final RegistryObject<TableBlock> TABLE = BLOCKS.register(
            "table",
            TableBlock::new
    );

    public static final RegistryObject<TableBehindGhostBlock> TABLE_BEHIND_GHOST = BLOCKS.register(
            "table_behind_ghost",
            TableBehindGhostBlock::new
    );

    public static final RegistryObject<TableRightGhostBlock> TABLE_RIGHT_GHOST = BLOCKS.register(
            "table_right_ghost",
            TableRightGhostBlock::new
    );

    public static final RegistryObject<TableBehindRightGhostBlock> TABLE_BEHIND_RIGHT_GHOST = BLOCKS.register(
            "table_behind_right_ghost",
            TableBehindRightGhostBlock::new
    );
}
