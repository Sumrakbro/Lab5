package commands;

import com.company.Person;
import com.company.Reciver;
/**
 Данная команда выводит все объекты коллекции, которые имеют уникальное поле Person
 */
public class Command_PrintUniquePerson extends Command_Print {
    Person person;
    public Command_PrintUniquePerson(Person person) {
        this.person=person;
    }

    public Command_PrintUniquePerson() {

    }

    @Override
    public void execute() {
        Reciver.print(A_command.getSet(),this.person);
    }

    @Override
    public String toString() {
        return "Command PrintUniquePerson <Person>- выводит уникальные значения Person";
    }
}
