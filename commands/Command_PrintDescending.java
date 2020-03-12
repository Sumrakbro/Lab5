package commands;

import com.company.Reciver;
/**
 Данная команда выводит коллекцию в обратном порядке
 */
public class Command_PrintDescending extends Command_Print{
int i=-1;
    public Command_PrintDescending() {
    }

    @Override
    public void execute() {
    Reciver.print(A_command.getSet(),this.i);
    }

    @Override
    public String toString() {
        return "Command PrintDescending";
    }
}
