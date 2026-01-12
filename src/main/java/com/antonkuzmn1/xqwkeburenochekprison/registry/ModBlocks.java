package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.folding_bed.FoldingBedBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.folding_bed.FoldingBedGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.chair.ChairBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha.ParashaBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha.ParashaCisternGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha.ParashaRimGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_door.PrisonDoorBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_door.PrisonDoorGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_lamp.PrisonLampBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.shower.ShowerBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.shower.ShowerGhostBlock;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.soap.SoapBlock;
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

    public static final RegistryObject<ChairBlock> CHAIR = BLOCKS.register(
            "chair",
            ChairBlock::new
    );

    public static final RegistryObject<FoldingBedBlock> FOLDING_BED = BLOCKS.register(
            "folding_bed",
            FoldingBedBlock::new
    );

    public static final RegistryObject<FoldingBedGhostBlock> FOLDING_BED_GHOST = BLOCKS.register(
            "folding_bed_ghost",
            FoldingBedGhostBlock::new
    );

    public static final RegistryObject<PrisonDoorBlock> PRISON_DOOR = BLOCKS.register(
            "prison_door",
            PrisonDoorBlock::new
    );

    public static final RegistryObject<PrisonDoorGhostBlock> PRISON_DOOR_GHOST = BLOCKS.register(
            "prison_door_ghost",
            PrisonDoorGhostBlock::new
    );

    public static final RegistryObject<PrisonLampBlock> PRISON_LAMP = BLOCKS.register(
            "prison_lamp",
            PrisonLampBlock::new
    );

    public static final RegistryObject<ShowerBlock> SHOWER = BLOCKS.register(
            "shower",
            ShowerBlock::new
    );

    public static final RegistryObject<ShowerGhostBlock> SHOWER_GHOST = BLOCKS.register(
            "shower_ghost",
            ShowerGhostBlock::new
    );

    public static final RegistryObject<SoapBlock> SOAP = BLOCKS.register(
            "soap",
            SoapBlock::new
    );
}
