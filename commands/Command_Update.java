package commands;

import com.company.Reciver;
import com.company.Ticket;

import java.util.TreeSet;

public class Command_Update extends A_command implements Command {
    int index;
    Ticket ticket;
    /**
     Данная команда заменяет объект коллекции по id
     */
    public Command_Update(int index, Ticket ticket) {
       this.addNewCommand(this);
        this.index = index;
        this.ticket = ticket;
    }

    public Command_Update() {

    }

    @Override
    public void execute() {
        Reciver.replace(this.index, this.ticket, A_command.getSet());
    }
    @Override
    public String toString() {
        return "Command Update <Index,Element>- обновляет эллемент id,которого равно заданному";
    }
}
