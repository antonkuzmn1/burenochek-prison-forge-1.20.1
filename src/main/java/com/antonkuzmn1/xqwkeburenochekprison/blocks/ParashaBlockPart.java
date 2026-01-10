package com.antonkuzmn1.xqwkeburenochekprison.blocks;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ParashaBlockPart implements StringRepresentable {
    FRONT,
    TOP;

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }
}