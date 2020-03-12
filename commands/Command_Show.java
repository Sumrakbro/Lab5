package commands;

import com.company.Reciver;

import java.util.TreeSet;
/**
 Данная команда выводит все объекты коллекции
 */
public class Command_Show implements Command{
    public Command_Show(){

   }
    @Override
    public void execute() {
        Reciver.print(A_command.getSet(),1);
   }
    @Override
    public String toString() {
        return "Command Show- выводит эллементы коллекции на экран";
    }
}
