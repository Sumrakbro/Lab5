package commands;

import com.company.Reciver;
import com.company.UncorrectedScriptException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 Данная команда выполняет скрипт
 */
public class Command_ExecuteFile implements Command {
    String path;

    public Command_ExecuteFile(String path) {
        this.path = path;
    }

    public Command_ExecuteFile() {

    }

    @Override
    public void execute() throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException, IllegalArgumentException, UncorrectedScriptException {
        ArrayList<Command> commands = Reciver.ReadCommandFromFile(this.path);
       if (commands != null) {
            for (Command command : commands) {
                if (command != null)
                    command.execute();
            }
        }
    }

    @Override
    public String toString() {
        return "Command ExecuteFile <Path>- выпоняет скрипт";
    }
}
