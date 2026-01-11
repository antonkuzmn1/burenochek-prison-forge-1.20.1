package com.antonkuzmn1.xqwkeburenochekprison.client;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.*;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
        modid = XqwkeBurenochekPrisonMod.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.PARASHA.get(),
                ctx -> new ParashaBlockRenderer()
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.TABLE.get(),
                ctx -> new TableBlockRenderer()
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.CHAIR.get(),
                ctx -> new ChairBlockRenderer()
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.FOLDING_BED.get(),
                ctx -> new FoldingBedBlockRenderer()
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.PRISON_DOOR.get(),
                ctx -> new PrisonDoorBlockRenderer()
        );
        event.registerBlockEntityRenderer(
                ModBlockEntities.PRISON_LAMP.get(),
                ctx -> new PrisonLampBlockRenderer()
        );
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }
}
