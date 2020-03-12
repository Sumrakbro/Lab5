package commands;

import com.company.Reciver;

/**
 Данная команда осуществялет выход из программы
 */
public class Command_Exit implements Command {
    public Command_Exit() {

    }

    @Override
    public void execute(){
        Reciver.exit();
    }

    @Override
    public String toString() {
        return "Command Exit- выходит из программы без сохранения изменений";
    }
}
