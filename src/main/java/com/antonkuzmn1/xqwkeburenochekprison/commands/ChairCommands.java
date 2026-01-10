package com.antonkuzmn1.xqwkeburenochekprison.commands;

import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.ChairItemRenderState;
import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.TableItemRenderState;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


@Mod.EventBusSubscriber
public class ChairCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                literal("chair")
                        .then(literal("setBaseScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ChairItemRenderState.baseScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setBaseTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            ChairItemRenderState.baseTX = FloatArgumentType.getFloat(ctx, "x");
                                                            ChairItemRenderState.baseTY = FloatArgumentType.getFloat(ctx, "y");
                                                            ChairItemRenderState.baseTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setGuiScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ChairItemRenderState.guiScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setGuiTranslation")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .executes(ctx -> {
                                                    ChairItemRenderState.guiTX = FloatArgumentType.getFloat(ctx, "x");
                                                    ChairItemRenderState.guiTY = FloatArgumentType.getFloat(ctx, "y");
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
                                                            ChairItemRenderState.guiRX = FloatArgumentType.getFloat(ctx, "x");
                                                            ChairItemRenderState.guiRY = FloatArgumentType.getFloat(ctx, "y");
                                                            ChairItemRenderState.guiRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setTPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ChairItemRenderState.tpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setTPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            ChairItemRenderState.tpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            ChairItemRenderState.tpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            ChairItemRenderState.tpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            ChairItemRenderState.tpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            ChairItemRenderState.tpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            ChairItemRenderState.tpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setFPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ChairItemRenderState.fpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setFPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            ChairItemRenderState.fpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            ChairItemRenderState.fpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            ChairItemRenderState.fpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            ChairItemRenderState.fpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            ChairItemRenderState.fpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            ChairItemRenderState.fpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
        );
    }
}
