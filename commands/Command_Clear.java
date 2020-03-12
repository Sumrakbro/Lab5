package commands;

import com.company.Reciver;

import java.util.TreeSet;
/**
Данная команда очищает коллекцию
 */
public class Command_Clear implements Command {
    public Command_Clear() {
    }

    @Override
    public void execute() {
        Reciver.clear(A_command.getSet());
    }

    @Override
    public String toString() {
        return "Command Clear- очищает коллекцию";
    }
}

