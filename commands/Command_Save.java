package commands;
import com.company.Reciver;

import java.io.IOException;

/**
 Данная команда сохраняет коллекцию в файл
 */
public class Command_Save implements Command{
   String path;
    public Command_Save(String path){
        this.path=path;
    }

    public Command_Save() {

    }

    @Override
    public void execute() throws IOException {
    Reciver.save(A_command.getSet(),this.path);
    }

    public String getPath() {
        return path;
    }
    @Override
    public String toString() {
        return "Command Save <Path>- сохраняет коллекцию в файл";
    }
}


