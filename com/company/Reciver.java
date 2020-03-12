package com.company;


import commands.A_command;
import commands.Command;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Reciver {

    public static void printHelp() {
        int index = 1;
        System.out.println("Доступные команды:");
        for (Command command : A_command.getCommands()) {
            System.out.println("\t" + index + "." + command);
            index++;
        }
    }

    public static void replace(int index, Ticket ticket, TreeSet<Ticket> collection) {
        try {
            found(index, collection);
        } catch (IllegalArgumentException e) {
            return;
        }

        collection.removeIf(t -> t.getid() == index);
        collection.add(ticket);
    }


    public static Ticket found(int index, TreeSet<Ticket> collection) throws IllegalArgumentException {
        if (index < 0) throw new IllegalArgumentException();
        for (Ticket ticket : collection) {
            if (ticket.getid() == index) return ticket;
        }
        System.out.println(Color.ANSI_RED + "Объекта с индексом: " + index + " не найдено в коллекции!" + Color.ANSI_RESET);
        throw new IllegalArgumentException();
    }

    public static void clear(TreeSet<Ticket> collection) {
        collection.clear();
    }

    public static void remove(TreeSet<Ticket> collection, int index) {
        try {
            found(index, A_command.getSet());
        } catch (IllegalArgumentException e) {
            System.out.println(Color.ANSI_RED + "Объекта с таким индексом не существует" + Color.ANSI_RESET);
            return;
        }
        collection.removeIf(t -> t.getid() == index);
    }

    public static void remove(TreeSet<Ticket> collection, Ticket ticket, int i) {
        if (i > 0) {
            collection.removeIf(t -> t.compareTo(ticket) > 0);
        } else collection.removeIf(t -> t.compareTo(ticket) < 0);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void add(TreeSet<Ticket> collection, Ticket ticket) {
        collection.add(ticket);
    }

    public static void save(TreeSet<Ticket> set, String path) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка в пути к файлу!");
            return;
        }
        for (Ticket ticket : set) {
            fileOutputStream.write(ticket.toString().getBytes());
        }
        fileOutputStream.close();
        System.out.println(Color.ANSI_PURPLE + "Коллекция сохранена!" + Color.ANSI_RESET);
    }

    public static void print(TreeSet<Ticket> set, int type) {
        if (set.size() > 0) {
            if (type > 0) for (Object ticket : set) System.out.println(ticket);
            else if (type < 0) for (Object ticket : set.descendingSet()) System.out.println(ticket);
        } else System.out.println(Color.ANSI_CYAN + "Коллекция пуста!" + Color.ANSI_RESET);
    }


    public static void print(TreeSet<Ticket> set, Person person) {
        for (Ticket ticket1 : set) {
            if (ticket1.getPerson() != null) {
                if (!ticket1.getPerson().equals(person)) {
                    System.out.println(ticket1.getPerson());
                }
            }
        else System.out.println(Color.ANSI_YELLOW+"Билет с номером: "+ticket1.getid()+" не содержит значения Person"+Color.ANSI_RESET);
        }
    }


    public static void print(TreeSet<Ticket> set, TicketType type) {
        for (Ticket ticket : set) {
            if (ticket.getType() != null) {
                if (ticket.getType().compareTo(type) < 0) System.out.println(ticket);
            } else System.out.println(ticket);
        }
    }

    public static void info(TreeSet<Ticket> set) {
        System.out.println(Color.ANSI_CYAN+"Общая информация о коллекции:");
        System.out.println("\tРазмер коллекции:"+set.size()+Color.ANSI_RESET);
    }

    public static ArrayList<Command> ReadCommandFromFile(String path) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, IllegalArgumentException, UncorrectedScriptException {
        ArrayList<Command> commands = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            System.out.println(Color.ANSI_RED + "Ошибка в пути к файлу!" + Color.ANSI_RESET);
            return null;
        }
        // считываем построчно
        String line;
        Scanner scanner;
        String input;
        Class mClassObject;
        Command command = null;
        String name = null;
        while ((line = reader.readLine()) != null) {
            System.out.println("input: " + line);
            scanner = new Scanner(line);
            while (scanner.hasNext()) {
                while (true) {
                    try {
                        input = scanner.nextLine() + " ";
                        name = input.substring(0, input.indexOf(" "));
                        mClassObject = Class.forName("commands.Command_" + name);
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(Color.ANSI_RED + "Несуществующая команда  " + name + "!" + Color.ANSI_RESET);
                        return null;
                    }
                }
                input = input.trim();
                Constructor[] constructors = mClassObject.getConstructors();
                Class[] parameterTypes = constructors[0].getParameterTypes();
                try {
                    if (parameterTypes.length == 0) {
                        command = (Command) constructors[0].newInstance();
                        System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                    } else if (parameterTypes.length == 1) {
                        if (parameterTypes[0].toString().compareTo("int") == 0) {
                            command = (Command) constructors[0].newInstance(Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1)));
                            System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                        } else if (parameterTypes[0].toString().compareTo("class com.company.Ticket") == 0) {
                            command = (Command) constructors[0].newInstance(MakeTicket(reader));
                            System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                        } else if (parameterTypes[0].toString().compareTo("class com.company.TicketType") == 0) {
                            command = (Command) constructors[0].newInstance(MakeType(reader));
                            System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                        } else if (parameterTypes[0].toString().compareTo("class com.company.Person") == 0) {
                            command = (Command) constructors[0].newInstance(MakePerson(reader));
                            System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                        } else if (parameterTypes[0].toString().compareTo("class java.lang.String") == 0) {
                            command = (Command) constructors[0].newInstance(input.substring(input.indexOf(" ") + 1));
                            System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                        }

                    } else if (parameterTypes.length == 2) {
                        command = (Command) constructors[0].newInstance(Integer.parseInt(input.substring(input.indexOf(" ") + 1)),
                                MakeTicket(reader));
                        System.out.println("Полученная из скрипта команда:\n" + Color.ANSI_CYAN + command + Color.ANSI_RESET);
                    }
                } catch (NumberFormatException | InputMismatchException | UncorrectedScriptException e) {
                    throw new UncorrectedScriptException();
                }
            }
            commands.add(command);
        }
        return commands;
    }

    private static Ticket MakeTicket(BufferedReader reader) throws IOException, UncorrectedScriptException {
        String name = null; //Поле не может быть null, Строка не может быть пустой
        Float price = null; //Поле может быть null, Значение поля должно быть больше 0
        TicketType type; //Поле может быть null
        Scanner scanner;
        String input = "";
        while (input.isEmpty()) {
            input = reader.readLine();
            scanner = new Scanner(input);
            if (!input.isEmpty()) name = scanner.next().trim();
        }
        System.out.println("Name:" + name);
        price = getFloat(reader);
        System.out.println("Price:" + price);
        type = MakeType(reader);
        return new Ticket(name, MakeCoordinates(reader), price, type, MakePerson(reader));

    }

    private static Float getFloat(BufferedReader reader) throws IOException, UncorrectedScriptException {
        Float result = null;
        Scanner scanner;
        String input = "";
        while (input.isEmpty()) {
            input = reader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) result = scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println(Color.ANSI_RED + "Неверные данные в скрипте: " + Color.ANSI_YELLOW + input + Color.ANSI_RED + ". Проверьте Скрипт!\n" + Color.ANSI_YELLOW + "Данная строка должна содержать дробное число." + Color.ANSI_RESET);
                throw new UncorrectedScriptException();
            }
        }
        return result;
    }

    private static Coordinates MakeCoordinates(BufferedReader reader) throws IOException, InputMismatchException, UncorrectedScriptException {
        Float x; //Значение поля должно быть больше -373, Поле не может быть null
        Integer y;
        x = getFloat(reader);
        System.out.println("X:" + x);
        y = getInteger(reader);
        System.out.println("Y:" + y);
        return new Coordinates(x, y);
    }

    private static Integer getInteger(BufferedReader reader) throws IOException, UncorrectedScriptException {
        String input = "";
        Scanner scanner;
        Integer result = null;
        while (input.isEmpty()) {
            input = reader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) result = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Color.ANSI_RED + "Неверные данные в скрипте: " + Color.ANSI_YELLOW + input + Color.ANSI_RED + ". Проверьте Скрипт!\n" + Color.ANSI_YELLOW + "Данная строка должна содержать целое число." + Color.ANSI_RESET);
                throw new UncorrectedScriptException();
            }
        }
        return result;
    }

    private static Long getLong(BufferedReader reader) throws IOException, UncorrectedScriptException {
        String input = "";
        Scanner scanner;
        Long result = null;
        while (input.isEmpty()) {
            input = reader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) result = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println(Color.ANSI_RED + "Неверные данные в скрипте: " + Color.ANSI_YELLOW + input + Color.ANSI_RED + ". Проверьте Скрипт!\n" + Color.ANSI_YELLOW + "Данная строка должна содержать целое число." + Color.ANSI_RESET);
                throw new UncorrectedScriptException();
            }
        }
        return result;
    }

    private static Person MakePerson(BufferedReader reader) throws IOException, UncorrectedScriptException {
        String passportID = null; //Поле может быть null
        long weight; //Поле может быть null, Значение поля должно быть больше 0
        Scanner scanner;
        String input = "";
        while (input.isEmpty()) {
            input = reader.readLine();
            scanner = new Scanner(input);
            if (!input.isEmpty()) passportID = scanner.nextLine();

        }
        System.out.println("PassportID" + passportID);
        weight = getLong(reader);
        System.out.println("Weight" + weight);
        return new Person(weight, passportID);
    }

    private static TicketType MakeType(BufferedReader reader) throws IOException, UncorrectedScriptException {
        TicketType type = null; //Поле может быть null
        Scanner scanner;
        String input = "";
        while (input.isEmpty()) {
            input = reader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) type = TicketType.valueOf(scanner.next().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(Color.ANSI_RED + "Неверные данные в скрипте: " + Color.ANSI_YELLOW + input + Color.ANSI_RED + ". Проверьте Скрипт!");
                throw new UncorrectedScriptException();
            }
        }
        System.out.println("Type:" + type);
        return type;

    }
}
