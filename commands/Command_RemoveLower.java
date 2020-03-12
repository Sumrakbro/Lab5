package commands;

import com.company.Reciver;
import com.company.Ticket;
/**
 Данная команда удаляет все объекты коллекции,которые меньше заданного
 */
public class Command_RemoveLower extends  Command_RemoveObjects{
    int index;
    public Command_RemoveLower(Ticket ticket) {
        super(ticket);
        this.index=-1;
    }

    public Command_RemoveLower() {
    }

    @Override
    public void execute() {
        Reciver.remove(A_command.getSet(),this.ticket,this.index);
    }
    @Override
    public String toString() {
        return "Command RemoveLower <Element>- удаляет все эллементы, которые меньше заданного";
    }
}
