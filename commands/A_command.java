package commands;

import com.company.Ticket;

import java.util.ArrayList;
import java.util.TreeSet;

public abstract class A_command {
    private static TreeSet<Ticket> set;

    public static void setSet(TreeSet<Ticket> set) {
        A_command.set = set;
    }

    public static TreeSet<Ticket> getSet() {
        return set;
    }

    private static ArrayList<Command> commands = new ArrayList<>();

    public static ArrayList<Command> getCommands() {
        return commands;
    }

    public static void addNewCommand(Command... commandsList) {
        for (Command command :
                commandsList) {
            commands.add(command);
        }

    }

}
