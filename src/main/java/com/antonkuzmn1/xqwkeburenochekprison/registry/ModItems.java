package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.items.ParashaItem;
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
}
