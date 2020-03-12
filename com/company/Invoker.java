package com.company;


import commands.Command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Invoker {
    /**
    Инвокер. Начинает выполнение любой комманды
     */

    private Command command;

    public Invoker() {
    }

    public void setCommand(Command com) {
        this.command = com;
        this.command.add(this.command);
    }

    public void executeCommand() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IllegalArgumentException, UncorrectedScriptException {
        command.execute();
    }

    public Command getCommand() {
        return command;
    }
}

