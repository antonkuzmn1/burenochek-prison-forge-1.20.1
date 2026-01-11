package com.antonkuzmn1.xqwkeburenochekprison.commands;

import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.ShowerItemRenderState;
import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.ShowerItemRenderState;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


@Mod.EventBusSubscriber
public class ShowerCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                literal("shower")
                        .then(literal("setBaseScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ShowerItemRenderState.baseScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setBaseTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            ShowerItemRenderState.baseTX = FloatArgumentType.getFloat(ctx, "x");
                                                            ShowerItemRenderState.baseTY = FloatArgumentType.getFloat(ctx, "y");
                                                            ShowerItemRenderState.baseTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setGuiScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ShowerItemRenderState.guiScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setGuiTranslation")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .executes(ctx -> {
                                                    ShowerItemRenderState.guiTX = FloatArgumentType.getFloat(ctx, "x");
                                                    ShowerItemRenderState.guiTY = FloatArgumentType.getFloat(ctx, "y");
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
                                                            ShowerItemRenderState.guiRX = FloatArgumentType.getFloat(ctx, "x");
                                                            ShowerItemRenderState.guiRY = FloatArgumentType.getFloat(ctx, "y");
                                                            ShowerItemRenderState.guiRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setTPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ShowerItemRenderState.tpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setTPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            ShowerItemRenderState.tpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            ShowerItemRenderState.tpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            ShowerItemRenderState.tpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            ShowerItemRenderState.tpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            ShowerItemRenderState.tpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            ShowerItemRenderState.tpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setFPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            ShowerItemRenderState.fpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setFPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            ShowerItemRenderState.fpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            ShowerItemRenderState.fpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            ShowerItemRenderState.fpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            ShowerItemRenderState.fpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            ShowerItemRenderState.fpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            ShowerItemRenderState.fpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
        );
    }
}
