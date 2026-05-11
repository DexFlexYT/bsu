package net.chemthunder.bsu.impl.event.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.chemthunder.bsu.impl.cca.entity.PlayerDataComponent;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author Chemthunder
 */
public class NickCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(literal("nick")
                .then(literal("set")
                        .then(literal("name").then(argument("text", StringArgumentType.string()).executes(context -> {
                            PlayerEntity player = context.getSource().getPlayerOrThrow();
                            PlayerDataComponent data = PlayerDataComponent.KEY.get(player);
                            String toApply = StringArgumentType.getString(context, "text");

                            data.setName(toApply);
                            return Command.SINGLE_SUCCESS;
                        })))

                        .then(literal("color")
                                .then(literal("r").then(argument("r", IntegerArgumentType.integer()).executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayerOrThrow();
                                    PlayerDataComponent data = PlayerDataComponent.KEY.get(player);
                                    int toApply = IntegerArgumentType.getInteger(context, "r");

                                    data.setR(toApply);
                                    return Command.SINGLE_SUCCESS;
                                })))

                                .then(literal("g").then(argument("g", IntegerArgumentType.integer()).executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayerOrThrow();
                                    PlayerDataComponent data = PlayerDataComponent.KEY.get(player);
                                    int toApply = IntegerArgumentType.getInteger(context, "g");

                                    data.setG(toApply);
                                    return Command.SINGLE_SUCCESS;
                                })))

                                .then(literal("b").then(argument("b", IntegerArgumentType.integer()).executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayerOrThrow();
                                    PlayerDataComponent data = PlayerDataComponent.KEY.get(player);
                                    int toApply = IntegerArgumentType.getInteger(context, "b");

                                    data.setB(toApply);
                                    return Command.SINGLE_SUCCESS;
                                })))
                        )
                )
        );
    }
}
