package com.antonkuzmn1.xqwkeburenochekprison.commands;

import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.TableItemRenderState;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


@Mod.EventBusSubscriber
public class TableCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                literal("table")
                        .then(literal("setBaseScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            TableItemRenderState.baseScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setBaseTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            TableItemRenderState.baseTX = FloatArgumentType.getFloat(ctx, "x");
                                                            TableItemRenderState.baseTY = FloatArgumentType.getFloat(ctx, "y");
                                                            TableItemRenderState.baseTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setGuiScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            TableItemRenderState.guiScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setGuiTranslation")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .executes(ctx -> {
                                                    TableItemRenderState.guiTX = FloatArgumentType.getFloat(ctx, "x");
                                                    TableItemRenderState.guiTY = FloatArgumentType.getFloat(ctx, "y");
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
                                                            TableItemRenderState.guiRX = FloatArgumentType.getFloat(ctx, "x");
                                                            TableItemRenderState.guiRY = FloatArgumentType.getFloat(ctx, "y");
                                                            TableItemRenderState.guiRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setTPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            TableItemRenderState.tpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setTPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            TableItemRenderState.tpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            TableItemRenderState.tpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            TableItemRenderState.tpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            TableItemRenderState.tpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            TableItemRenderState.tpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            TableItemRenderState.tpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setFPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            TableItemRenderState.fpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setFPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            TableItemRenderState.fpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            TableItemRenderState.fpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            TableItemRenderState.fpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            TableItemRenderState.fpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            TableItemRenderState.fpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            TableItemRenderState.fpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
        );
    }
}
