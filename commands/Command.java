package commands;

import com.company.UncorrectedScriptException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Общий интерфейс для всех комманд
 */
public interface Command {
    ArrayList<Command> history = new ArrayList<Command>();

    void execute() throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, UncorrectedScriptException;

    default void add(Command command) {
        if (history.size() >=10) delete();
        history.add(command);
    }

    default void delete() {
        while (history.size() >= 10)
            history.remove(0);
    }

    default ArrayList<Command> getHistory() {
        return history;
    }
}
