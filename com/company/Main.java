package com.company;

import commands.A_command;
import commands.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

/**
 Данная программа- пример управления коллекцией.
 Выполнена по паттерну: Команда
 Тип коллекции:TreeSet
 @author Balakin Artem
 @since Java8
 */
public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        A_command.addNewCommand(new Command_RemoveLower(), new Command_Save(), new Command_Show(), new Command_Update(),
                new Command_Add(), new Command_Clear(), new Command_ExecuteFile(), new Command_Exit(),
                new Command_Help(), new Command_History(), new Command_Info(), new Command_PrintDescending(),
                new Command_PrintLessThenType(), new Command_PrintUniquePerson(), new Command_RemoveByIndex(), new Command_RemoveGreater());
        Readder readder = new Readder(System.getenv("FILE_PATH"));
        TreeSet<Ticket> collection;
        collection = readder.Readfile();
        A_command.setSet(collection);
        Invoker invoker = new Invoker();
        while (true) {
            try {
                invoker.setCommand(readder.ReadCommandFromConsole());
            } catch (IllegalArgumentException e) {
                System.out.println(Color.ANSI_RED+"Вы ввели неправильные аргументы команды.Повторите ввод."+Color.ANSI_RESET);
                continue;
            }
            try {
                invoker.executeCommand();
            } catch (UncorrectedScriptException e) {
                System.out.println(Color.ANSI_RED+"Ошибка в выполнении скрипта!"+Color.ANSI_RESET);
            }
        }
    }
}

