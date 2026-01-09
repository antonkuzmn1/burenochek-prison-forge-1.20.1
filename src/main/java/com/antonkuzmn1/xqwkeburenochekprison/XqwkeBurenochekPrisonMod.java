package com.antonkuzmn1.xqwkeburenochekprison;

import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlockEntities;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModCreativeTabs;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(XqwkeBurenochekPrisonMod.MODID)
public class XqwkeBurenochekPrisonMod {
    public static final String MODID = "xqwkeburenochekprison";

    public XqwkeBurenochekPrisonMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        GeckoLib.initialize();
    }
}
