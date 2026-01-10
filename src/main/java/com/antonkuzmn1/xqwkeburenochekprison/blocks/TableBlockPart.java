package com.antonkuzmn1.xqwkeburenochekprison.blocks;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum TableBlockPart implements StringRepresentable {
    BEHIND,
    RIGHT,
    BEHIND_RIGHT;

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }
}