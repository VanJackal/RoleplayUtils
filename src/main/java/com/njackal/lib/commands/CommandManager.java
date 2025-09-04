package com.njackal.lib.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandManager {
    public void register(Object controller) {
        Class<?> clazz = controller.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            Command command = method.getAnnotation(Command.class);
            if (command != null) {
                registerMcCommand(controller, method, command.value());
            }
        }
    }

    private void registerMcCommand(Object controller, Method method, String name) {
        CommandRegistrationCallback.EVENT.register(
                (dispatcher,
                registryAccess,
                registrationEnvironment) ->
                        dispatcher.register(net.minecraft.server.command.CommandManager.literal(name)
                                .executes(commandFromMethod(controller, method))));
    }

    com.mojang.brigadier.Command<ServerCommandSource> commandFromMethod(Object controller, Method method) {
        return (CommandContext<ServerCommandSource> ctx) -> {
            try {
                method.invoke(controller, ctx);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to execute command",e);
            }
            return 1;
        };
    }
}
