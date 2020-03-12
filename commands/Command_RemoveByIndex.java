package commands;
import com.company.Reciver;

import java.util.TreeSet;
/**
 Данная команда удаляет  объекты из коллекции по индексу
 */
public class Command_RemoveByIndex extends  Command_Remove{
    int index;
    public Command_RemoveByIndex( int index){
        this.index=index;
    }

    public Command_RemoveByIndex() {

    }

    @Override
    public void execute() {
    Reciver.remove(A_command.getSet(),this.index);
    }

    @Override
    public String toString() {
        return "CommandRemoveIndex <Index>- удаляет эллемент с таким индексом";
    }

}
