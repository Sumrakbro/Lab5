package commands;

import com.company.Reciver;
import com.company.TicketType;
/**
 Данная команда выводит все объекты коллекции, тип которых меньше заданного
 */
public class Command_PrintLessThenType extends Command_Print{
    TicketType type;
    public Command_PrintLessThenType(TicketType type) {
    this.type=type;
    }

    public Command_PrintLessThenType() {

    }

    @Override
    public void execute() {
        Reciver.print(A_command.getSet(),this.type);
    }
    @Override
    public String toString() {
        return "Command PrintLessThenType <Type>- выводит элементы, значения Type которых меньше";
    }
}
