package com.antonkuzmn1.xqwkeburenochekprison.registry;

import com.antonkuzmn1.xqwkeburenochekprison.XqwkeBurenochekPrisonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            XqwkeBurenochekPrisonMod.MODID
    );

    public static final RegistryObject<CreativeModeTab> XQWKE_BURENOCHEK_PRISON = CREATIVE_MODE_TABS.register(
            "xqwkeburenochekprison",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.xqwkeburenochekprison"))
                    .icon(() -> ModItems.PARASHA.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.PARASHA.get());
                        output.accept(ModItems.TABLE.get());
                        output.accept(ModItems.CHAIR.get());
                        output.accept(ModItems.FOLDING_BED.get());
                        output.accept(ModItems.PRISON_DOOR.get());
                        output.accept(ModItems.PRISON_LAMP.get());
                        output.accept(ModItems.SHOWER.get());
                        output.accept(ModItems.SOAP.get());
                    })
                    .build()
    );
}
