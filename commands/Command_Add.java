package commands;

import com.company.Reciver;
import com.company.Ticket;

/**
 Данная команда добавляет объект в коллекцию
 */

public class Command_Add extends A_command implements Command {
    Ticket ticket;

    public Command_Add(Ticket ticket) {
        this.ticket = ticket;
    }

    public Command_Add() {

    }

    @Override
    public void execute() {
        Reciver.add(A_command.getSet(), this.ticket);
    }

    @Override
    public String toString() {
        return "Command Add <Ticket>-добавляет эллемент в коллекцию";
    }
}
