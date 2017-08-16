package ru.ilka.apartments.command;

/**
 * Here could be your advertisement +375 29 3880490
 */
public enum CommandType {
    SHOW_ALL {
        {
            this.command = new ShowAllCommand();
        }
    },
    SHOW_BY_ID {
        {
            this.command = new ShowByIdCommand();
        }
    },
    ADD{
        {
            this.command = new AddApartmentCommand();
        }
    },
    BOOK{
        {
            this.command = new BookApartmentCommand();
        }
    },
    DELETE{
        {
            this.command = new DeleteApartmentCommand();
        }
    },
    DELETE_ALL{
        {
            this.command = new DeleteAllCommand();
        }
    },
    SHOW_AVAILABLE {
        {
            this.command = new ShowAvailableCommand();
        }
    };

    public ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
