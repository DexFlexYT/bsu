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
import net.minecraft.text.Style;
import net.minecraft.text.Text;

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
                            context.getSource().sendFeedback(()->Text.literal("Display name changed to \"" + toApply + "\""), false);
                            return Command.SINGLE_SUCCESS;
                        })))

                        .then(literal("color")
                            .then(argument("hex", StringArgumentType.word()).executes(context -> {
                                PlayerEntity player = context.getSource().getPlayerOrThrow();
                                PlayerDataComponent data = PlayerDataComponent.KEY.get(player);
                                String hex = StringArgumentType.getString(context, "hex");

                                if (hex.startsWith("#")) hex = hex.substring(1);

                                if (hex.length() != 6 || !hex.matches("[0-9a-fA-F]+")) {
                                    context.getSource().sendError(Text.literal("Invalid hex color, use format #RRGGBB"));
                                    return 0;
                                }
                                int r = Integer.parseInt(hex.substring(0, 2), 16);
                                int g = Integer.parseInt(hex.substring(2, 4), 16);
                                int b = Integer.parseInt(hex.substring(4, 6), 16);

                                data.setR(r);
                                data.setG(g);
                                data.setB(b);
                                final String color = hex;
                                int packed = (r << 16) | (g << 8) | b;

                                context.getSource().sendFeedback(() ->
                                    Text.literal("Name color changed to \"")
                                        .append(Text.literal("#" + color)
                                            .setStyle(Style.EMPTY.withColor(packed)))
                                        .append(Text.literal("\"")),
                                false);
                                return Command.SINGLE_SUCCESS;
                            })))
                        )
                );
    }
}
