package com.antonkuzmn1.xqwkeburenochekprison;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(XqwkeBurenochekPrisonMod.MODID)
public class XqwkeBurenochekPrisonMod {
    public static final String MODID = "xqwkeburenochekprison";

    public XqwkeBurenochekPrisonMod(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
