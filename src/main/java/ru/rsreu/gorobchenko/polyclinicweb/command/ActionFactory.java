package ru.rsreu.gorobchenko.polyclinicweb.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.error.Get404Command;

import java.lang.reflect.Constructor;

public class ActionFactory {
    public static final String PARAMETER_COMMAND = "command";

    public static ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter(PARAMETER_COMMAND);
        Command currentCommand = Command.valueOf(action.toUpperCase());
        return ActionFactory.reflexConstructCommand(currentCommand, request, response);

    }

    public static ActionCommand reflexConstructCommand(Command command, HttpServletRequest request, HttpServletResponse response) {
        try {
            Class<? extends ActionCommand> commandClassName = command.getCurrentCommand();
            Constructor<? extends ActionCommand> commandConstructor = commandClassName.getConstructor(
                    HttpServletRequest.class, HttpServletResponse.class
            );
            return commandConstructor.newInstance(request, response);
        } catch (Exception e) {
            return new Get404Command(request, response);
        }
    }
}
