package net.chemthunder.bsu.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.chemthunder.bsu.impl.cca.entity.PlayerDataComponent;
import net.chemthunder.bsu.impl.event.command.NickCommand;
import net.chemthunder.bsu.impl.event.command.PronounCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicallyServerUtils implements ModInitializer, EntityComponentInitializer {
	public static final String MOD_ID = "bsu";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
		LOGGER.info("BSU INIT");

        try {
            CommandRegistrationCallback.EVENT.register(new NickCommand());
            CommandRegistrationCallback.EVENT.register(new PronounCommand());
        } catch (Exception e) {
            LOGGER.info("Command registration failure: {}", e.toString());
        }

        MidnightConfig.init(MOD_ID, Config.class);
	}

    public static Identifier id(String src) {
        return Identifier.of(MOD_ID, src);
    }

    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PlayerDataComponent.KEY, PlayerDataComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }

    public static class Config extends MidnightConfig {
        @Entry public static boolean compassFunctionality = false;
    }
}