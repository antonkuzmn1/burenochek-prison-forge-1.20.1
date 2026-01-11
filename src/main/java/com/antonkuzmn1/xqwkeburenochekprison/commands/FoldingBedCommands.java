package com.antonkuzmn1.xqwkeburenochekprison.commands;

import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.FoldingBedItemRenderState;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


@Mod.EventBusSubscriber
public class FoldingBedCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                literal("foldingBed")
                        .then(literal("setBaseScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            FoldingBedItemRenderState.baseScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setBaseTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            FoldingBedItemRenderState.baseTX = FloatArgumentType.getFloat(ctx, "x");
                                                            FoldingBedItemRenderState.baseTY = FloatArgumentType.getFloat(ctx, "y");
                                                            FoldingBedItemRenderState.baseTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setGuiScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            FoldingBedItemRenderState.guiScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setGuiTranslation")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .executes(ctx -> {
                                                    FoldingBedItemRenderState.guiTX = FloatArgumentType.getFloat(ctx, "x");
                                                    FoldingBedItemRenderState.guiTY = FloatArgumentType.getFloat(ctx, "y");
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(literal("setGuiRotation")
                                .then(argument("x", FloatArgumentType.floatArg(0f, 360f))
                                        .then(argument("y", FloatArgumentType.floatArg(0f, 360f))
                                                .then(argument("z", FloatArgumentType.floatArg(0f, 360f))
                                                        .executes(ctx -> {
                                                            FoldingBedItemRenderState.guiRX = FloatArgumentType.getFloat(ctx, "x");
                                                            FoldingBedItemRenderState.guiRY = FloatArgumentType.getFloat(ctx, "y");
                                                            FoldingBedItemRenderState.guiRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setTPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            FoldingBedItemRenderState.tpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setTPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            FoldingBedItemRenderState.tpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            FoldingBedItemRenderState.tpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            FoldingBedItemRenderState.tpTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
                        .then(literal("setTPRotation")
                                .then(argument("x", FloatArgumentType.floatArg(0f, 360f))
                                        .then(argument("y", FloatArgumentType.floatArg(0f, 360f))
                                                .then(argument("z", FloatArgumentType.floatArg(0f, 360f))
                                                        .executes(ctx -> {
                                                            FoldingBedItemRenderState.tpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            FoldingBedItemRenderState.tpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            FoldingBedItemRenderState.tpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setFPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            FoldingBedItemRenderState.fpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setFPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            FoldingBedItemRenderState.fpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            FoldingBedItemRenderState.fpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            FoldingBedItemRenderState.fpTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
                        .then(literal("setFPRotation")
                                .then(argument("x", FloatArgumentType.floatArg(0f, 360f))
                                        .then(argument("y", FloatArgumentType.floatArg(0f, 360f))
                                                .then(argument("z", FloatArgumentType.floatArg(0f, 360f))
                                                        .executes(ctx -> {
                                                            FoldingBedItemRenderState.fpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            FoldingBedItemRenderState.fpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            FoldingBedItemRenderState.fpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
        );
    }
}
