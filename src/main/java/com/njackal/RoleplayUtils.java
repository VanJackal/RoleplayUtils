package com.njackal;

import com.njackal.command.CommandGlow;
import com.njackal.command.CommandLore;
import com.njackal.command.CommandRename;
import com.njackal.lib.commands.CommandManager;
import com.njackal.logic.glow.GlowManager;
import com.njackal.logic.glow.IGlowManager;
import com.njackal.logic.text.IItemTextManager;
import com.njackal.logic.text.ItemTextManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RoleplayUtils implements ModInitializer {
	public static final String MOD_ID = "roleplay_utils";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandManager commandManager = new CommandManager();
		IItemTextManager itemTextManager = new ItemTextManager();
		IGlowManager glowManager = new GlowManager();

		commandManager.register(
				new CommandRename(itemTextManager),
				new CommandLore(itemTextManager),
				new CommandGlow(glowManager)
		);

		LOGGER.info("RoleplayUtils initialized!");
	}

}