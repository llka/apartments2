package ru.ilka.apartments.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ActionFactory {
    private static final String COMMAND_PARAM = "command";

    private static Logger logger = LogManager.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand currentCommand = new EmptyCommand();

        String commandAction = request.getParameter(COMMAND_PARAM);
        if (commandAction == null || commandAction.isEmpty()) {
            return currentCommand;
        }

        CommandType currentType = CommandType.valueOf(commandAction.toUpperCase());
        currentCommand = currentType.getCurrentCommand();

        logger.info("ActionFactory currCommand = " + currentCommand);

        return currentCommand;
    }
}
