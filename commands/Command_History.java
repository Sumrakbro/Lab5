package commands;


import com.company.Color;

import java.util.ArrayList;

/**
 Показывает историю выполненых команд
 */
public class Command_History extends A_command implements Command {
    public Command_History() {
    }

    @Override
    public void execute() {
        int index = 1;
        ArrayList<Command> history = this.getHistory();
        System.out.println(Color.ANSI_YELLOW+"История комманд:");
        for (Command com : history) {
            System.out.println("\t"+index + "." + com);
            index++;
        }
    System.out.print(Color.ANSI_RESET);
    }
    @Override
    public String toString() {
        return "Command History- выводит 10 последних,выполненных комманд";
    }
}

