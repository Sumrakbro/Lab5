package commands;

import com.company.Reciver;

import java.util.TreeSet;
/**
 Команда показывающая информацию о коллекции
 */
public class Command_Info extends A_command implements Command{
   public Command_Info(){
    }
    @Override
    public void execute() {
    Reciver.info(A_command.getSet());
    }
 @Override
 public String toString() {
  return "Command Info- выводит информацию о коллекции";
 }
}
