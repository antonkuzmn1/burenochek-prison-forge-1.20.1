package com.antonkuzmn1.xqwkeburenochekprison.commands;

import com.antonkuzmn1.xqwkeburenochekprison.client.renderers.PrisonDoorItemRenderState;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


@Mod.EventBusSubscriber
public class PrisonDoorCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                literal("prisonDoor")
                        .then(literal("setBaseScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            PrisonDoorItemRenderState.baseScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setBaseTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            PrisonDoorItemRenderState.baseTX = FloatArgumentType.getFloat(ctx, "x");
                                                            PrisonDoorItemRenderState.baseTY = FloatArgumentType.getFloat(ctx, "y");
                                                            PrisonDoorItemRenderState.baseTZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setGuiScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            PrisonDoorItemRenderState.guiScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setGuiTranslation")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .executes(ctx -> {
                                                    PrisonDoorItemRenderState.guiTX = FloatArgumentType.getFloat(ctx, "x");
                                                    PrisonDoorItemRenderState.guiTY = FloatArgumentType.getFloat(ctx, "y");
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
                                                            PrisonDoorItemRenderState.guiRX = FloatArgumentType.getFloat(ctx, "x");
                                                            PrisonDoorItemRenderState.guiRY = FloatArgumentType.getFloat(ctx, "y");
                                                            PrisonDoorItemRenderState.guiRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setTPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            PrisonDoorItemRenderState.tpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setTPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            PrisonDoorItemRenderState.tpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            PrisonDoorItemRenderState.tpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            PrisonDoorItemRenderState.tpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            PrisonDoorItemRenderState.tpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            PrisonDoorItemRenderState.tpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            PrisonDoorItemRenderState.tpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                        .then(literal("setFPScale")
                                .then(argument("scale", FloatArgumentType.floatArg(0f))
                                        .executes(ctx -> {
                                            PrisonDoorItemRenderState.fpScale = FloatArgumentType.getFloat(ctx, "scale");
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("setFPTransition")
                                .then(argument("x", FloatArgumentType.floatArg())
                                        .then(argument("y", FloatArgumentType.floatArg())
                                                .then(argument("z", FloatArgumentType.floatArg())
                                                        .executes(ctx -> {
                                                            PrisonDoorItemRenderState.fpTX = FloatArgumentType.getFloat(ctx, "x");
                                                            PrisonDoorItemRenderState.fpTY = FloatArgumentType.getFloat(ctx, "y");
                                                            PrisonDoorItemRenderState.fpTZ = FloatArgumentType.getFloat(ctx, "z");
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
                                                            PrisonDoorItemRenderState.fpRX = FloatArgumentType.getFloat(ctx, "x");
                                                            PrisonDoorItemRenderState.fpRY = FloatArgumentType.getFloat(ctx, "y");
                                                            PrisonDoorItemRenderState.fpRZ = FloatArgumentType.getFloat(ctx, "z");
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
        );
    }
}
