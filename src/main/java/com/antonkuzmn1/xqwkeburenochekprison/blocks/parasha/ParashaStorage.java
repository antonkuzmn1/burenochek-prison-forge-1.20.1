package com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class ParashaStorage extends SavedData {
    private static final String NAME = "parasha_storage";

    private final NonNullList<ItemStack> items = NonNullList.create();

    public static ParashaStorage get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                ParashaStorage::load,
                ParashaStorage::new,
                NAME
        );
    }

    public static ParashaStorage load(CompoundTag tag) {
        ParashaStorage data = new ParashaStorage();
        ContainerHelper.loadAllItems(tag, data.items);
        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        ContainerHelper.saveAllItems(tag, items);
        return tag;
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }
}
