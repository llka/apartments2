package ru.ilka.apartments.command;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Here could be your advertisement +375 29 3880490
 */
@Component
public class ActionFactory {
    private static final String COMMAND_PARAM = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand currentCommand = new EmptyCommand();



        String commandAction = request.getParameter(COMMAND_PARAM);
        if (commandAction == null || commandAction.isEmpty()) {
            return currentCommand;
        }

        CommandType currentType = CommandType.valueOf(commandAction.toUpperCase());
        currentCommand = currentType.getCurrentCommand();

        System.out.println("ActionFactory currCommand = " + currentCommand);

        return currentCommand;
    }
}
