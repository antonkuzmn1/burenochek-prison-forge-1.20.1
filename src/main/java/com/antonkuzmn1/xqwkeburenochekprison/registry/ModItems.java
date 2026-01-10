package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.items.ChairItem;
import com.antonkuzmn1.xqwkeburenochekprison.items.ParashaItem;
import com.antonkuzmn1.xqwkeburenochekprison.items.TableItem;
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
}
