package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.blockentities.FoldingBedBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ChairBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ParashaBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blockentities.TableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES,
            XqwkeBurenochekPrisonMod.MODID
    );

    public static final RegistryObject<BlockEntityType<ParashaBlockEntity>> PARASHA = BLOCK_ENTITIES.register(
            "parasha",
            () -> BlockEntityType.Builder.of(
                    ParashaBlockEntity::new,
                    ModBlocks.PARASHA.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<TableBlockEntity>> TABLE = BLOCK_ENTITIES.register(
            "table",
            () -> BlockEntityType.Builder.of(
                    TableBlockEntity::new,
                    ModBlocks.TABLE.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<ChairBlockEntity>> CHAIR = BLOCK_ENTITIES.register(
            "chair",
            () -> BlockEntityType.Builder.of(
                    ChairBlockEntity::new,
                    ModBlocks.CHAIR.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<FoldingBedBlockEntity>> FOLDING_BED = BLOCK_ENTITIES.register(
            "folding_bed",
            () -> BlockEntityType.Builder.of(
                    FoldingBedBlockEntity::new,
                    ModBlocks.FOLDING_BED.get()
            ).build(null)
    );
}
