package commands;
import com.company.Reciver;
import com.company.Ticket;

import java.util.TreeSet;

public class Command_RemoveObjects extends Command_Remove{
Ticket ticket;
   public Command_RemoveObjects( Ticket ticket) {
    this.ticket=ticket;
    }

    public Command_RemoveObjects() {
    }

    @Override
    public void execute() {
        Reciver.remove(A_command.getSet(),this.ticket,-1);
    }

}
