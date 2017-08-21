package ru.ilka.apartments.command;

import ru.ilka.apartments.util.ContextHolder;

public enum CommandType {
    SHOW_ALL {
        {
            this.command = ContextHolder.getApplicationContext().getBean(ShowAllCommand.class);
        }
    },
    SHOW_BY_ID {
        {
            this.command = ContextHolder.getApplicationContext().getBean(ShowByIdCommand.class);
        }
    },
    ADD{
        {
            this.command = ContextHolder.getApplicationContext().getBean(AddApartmentCommand.class);
        }
    },
    BOOK{
        {
            this.command = ContextHolder.getApplicationContext().getBean(BookApartmentCommand.class);
        }
    },
    DELETE{
        {
            this.command = ContextHolder.getApplicationContext().getBean(DeleteApartmentCommand.class);
        }
    },
    DELETE_ALL{
        {
            this.command = ContextHolder.getApplicationContext().getBean(DeleteAllCommand.class);
        }
    },
    SHOW_AVAILABLE {
        {
            this.command = ContextHolder.getApplicationContext().getBean(ShowAvailableCommand.class);
        }
    };

    public ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
