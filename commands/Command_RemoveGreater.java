package commands;

import com.company.Reciver;
import com.company.Ticket;
/**
 Данная команда удаляет все объекты коллекции,  которые больше заданного
 */
public class Command_RemoveGreater extends  Command_RemoveObjects{
    int index;
    public Command_RemoveGreater(Ticket ticket) {
        super(ticket);
        this.addNewCommand(this);
        this.index=1;
    }

    public Command_RemoveGreater() {

    }

    @Override
    public void execute() {
      Reciver.remove(A_command.getSet(),this.ticket,this.index);
    }
    @Override
    public String toString() {
        return "Command RemoveGreater <Element>- удаляет все эллменты, которые больше заданного";
    }
}
