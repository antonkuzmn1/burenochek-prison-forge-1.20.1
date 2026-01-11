package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            XqwkeBurenochekPrisonMod.MODID
    );

    public static final RegistryObject<ParashaItem> PARASHA = ITEMS.register(
            "parasha",
            ParashaItem::new
    );

    public static final RegistryObject<TableItem> TABLE = ITEMS.register(
            "table",
            TableItem::new
    );

    public static final RegistryObject<ChairItem> CHAIR = ITEMS.register(
            "chair",
            ChairItem::new
    );

    public static final RegistryObject<FoldingBedItem> FOLDING_BED = ITEMS.register(
            "folding_bed",
            FoldingBedItem::new
    );

    public static final RegistryObject<PrisonDoorItem> PRISON_DOOR = ITEMS.register(
            "prison_door",
            PrisonDoorItem::new
    );

    public static final RegistryObject<PrisonLampItem> PRISON_LAMP = ITEMS.register(
            "prison_lamp",
            PrisonLampItem::new
    );

    public static final RegistryObject<ShowerItem> SHOWER = ITEMS.register(
            "shower",
            ShowerItem::new
    );
}
