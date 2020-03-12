package commands;

import com.company.Reciver;

/**
 Даннавя комманда показывает все доступные комманды.
 */

public class Command_Help extends A_command implements Command{
   public Command_Help(){

   }
    @Override
    public void execute() {
        Reciver.printHelp();
   }

    @Override
    public String toString() {
        return "Command Help- показывает доступные комманды";
    }
}
