package net.chemthunder.bsu.impl.event.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
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
public class PronounCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(literal("pronoun").then(argument("pronouns", StringArgumentType.string()).executes(context -> {
            PlayerEntity player = context.getSource().getPlayerOrThrow();
            PlayerDataComponent data = PlayerDataComponent.KEY.get(player);
            String toApply = StringArgumentType.getString(context, "pronouns");

            data.setPronoun(toApply);
            return Command.SINGLE_SUCCESS;
        })));
    }
}
