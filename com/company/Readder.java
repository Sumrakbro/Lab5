package com.company;

import commands.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.TreeSet;

public class Readder {
    String path;

    Readder(String path) {
        this.path = path;
    }

    public TreeSet<Ticket> Readfile() throws IOException {
        // открываем файл
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.path));
        } catch (IOException|NullPointerException e) {
            System.out.println(Color.ANSI_RED+"Проблемы с чтением из файла!"+Color.ANSI_RESET);
            System.out.println(Color.ANSI_RED+"Путь до файла input.csv должен быть прописан в переменной окружения FILE_PATH"+Color.ANSI_RESET);
            System.exit(-1);
        }

        // считываем построчно
        String line;
        Scanner scanner;
        int column = 0;
        int row = 0;
        TreeSet<Ticket> empList = new TreeSet<>();
        assert reader != null;
        while ((line = reader.readLine()) != null) {
            Ticket ticket = new Ticket();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next().trim();
                if (column == 3)
                    try {
                        ticket.setCoordinates(new Coordinates(Float.parseFloat(data.substring(1, data.indexOf(","))),
                                Integer.parseInt(data.substring(data.indexOf(",") + 1, data.length() - 1))));
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        System.out.println("Неверные данные в файле!\n" + "Строка:" + (row + 1) + "\nСтолбец:" + (column + 1));
                        System.exit(-1);
                    }

                else if (column == 0)
                    if (!data.isEmpty()) ticket.setName(data);
                    else {
                        System.out.println("Неверные данные в файле!\n" + "Строка:" + (row + 1) + "\nСтолбец:" + (column + 1));
                        System.exit(-1);
                    }
                else if (column == 1)
                    try {
                        ticket.setPrice(Float.parseFloat(data));
                    } catch (NumberFormatException e) {
                        System.out.println("Неверные данные в файле!\n" + "Строка:" + (row + 1) + "\nСтолбец:" + (column + 1));
                        System.exit(-1);
                    }
                else if (column == 2)
                    try {
                        if (!data.isEmpty()) ticket.setType(TicketType.valueOf(data.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверные данные в файле!\n" + "Строка:" + (row + 1) + "\nСтолбец:" + (column + 1));
                        System.exit(-1);
                    }
                else if (column == 4) {
                    try {

                        String par1 = null;
                        String par2 = null;
                        if (data.substring(1, data.indexOf(",") - 1).compareTo("") > 0)
                            par1 = data.substring(1, data.indexOf(",") - 1);
                        String substring2 = data.substring(data.indexOf(",") + 1, data.indexOf(")")).trim();
                        if (substring2.compareTo("") > 0) par2 = substring2;
                        else throw new NumberFormatException();
                        if (par1 != null && par2 != null) ticket.setPerson(new Person(Long.parseLong(par2), par1));
                        else if (par2 != null) ticket.setPerson(new Person(Long.parseLong(par2)));
                    } catch (StringIndexOutOfBoundsException | NumberFormatException g) {
                        System.out.println("Неверные данные в файле!\n" + "Строка:" + (row + 1) + "\nСтолбец:" + (column + 1));
                        System.exit(-1);
                    }
                } else System.out.println("Некорректные данные::" + data);
                column++;
            }
            column = 0;
            row++;
            empList.add(ticket);
        }

        //закрываем наш ридер
        reader.close();
        return empList;
    }

    public Command ReadCommandFromConsole() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("Введите название комадны и её числовые аругументы(Если они требуются):");
        Command command = null;
        Scanner scanner = new Scanner(System.in);
        String input;
        Class mClassObject;
        while (true) {
            try {
                input = scanner.nextLine() + " ";
                String name = input.substring(0, input.indexOf(" "));
                mClassObject = Class.forName("commands.Command_" + name);
                break;
            } catch (ClassNotFoundException e) {
                System.out.println(Color.ANSI_RED + "Вы ввели несуществующую команду!" + Color.ANSI_RESET);
            }
        }
        input = input.trim();
        Constructor[] constructors = mClassObject.getConstructors();
        Class[] parameterTypes = constructors[0].getParameterTypes();
        if (parameterTypes.length == 0) {
            command = (Command) constructors[0].newInstance();
        } else if (parameterTypes.length == 1) {
            if (parameterTypes[0].toString().compareTo("int") == 0) {
                command = (Command) constructors[0].newInstance(Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1)));
            } else if (parameterTypes[0].toString().compareTo("class com.company.Ticket") == 0) {
                command = (Command) constructors[0].newInstance(MakeTicket());
            } else if (parameterTypes[0].toString().compareTo("class com.company.TicketType") == 0) {
                command = (Command) constructors[0].newInstance(ReadType());
            } else if (parameterTypes[0].toString().compareTo("class com.company.Person") == 0) {
                command = (Command) constructors[0].newInstance(MakePerson());
            } else if (parameterTypes[0].toString().compareTo("class java.lang.String") == 0) {
                try {
                    input.substring(input.indexOf(" "));
                } catch (StringIndexOutOfBoundsException e) {
                  throw new IllegalArgumentException();
                }
                command = (Command) constructors[0].newInstance(input.substring(input.indexOf(" ") + 1));
            }
        } else if (parameterTypes.length == 2) {
            command = (Command) constructors[0].newInstance(Integer.parseInt(input.substring(input.indexOf(" ") + 1)), MakeTicket());
        }
        return command;
    }

    private Ticket MakeTicket() {
        String in;
        System.out.println("Для выполнения команды необходимо создать новый билет.");
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        String name = null; //Поле не может быть null, Строка не может быть пустой
        System.out.println("Введите название:");
        System.out.print("\tName:");
        name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println(Color.ANSI_RED + "Неправильный ввод.Повторите!" + Color.ANSI_RESET);
            System.out.print("\tName:");
            name = scanner.nextLine().trim();
        }
        check = false;
        Coordinates coordinates; //Поле не может быть null
        coordinates = MakeCoordinates();
        Float price; //Поле может быть null, Значение поля должно быть больше 0
        System.out.println("Введите цену:");
        while (true) {
            System.out.print("\tPrice:");
            in = scanner.nextLine();
            if (TryParseFloat(in)) {
                price = Float.parseFloat(in);
                break;
            }
            System.out.println(Color.ANSI_RED + "Вы ввели неправильную цену, повторите ввод." + Color.ANSI_RESET);
        }
        TicketType type; //Поле может быть null

        System.out.println("Хотите задать тип?(Да-если да)");
        if(scanner.nextLine().trim().toUpperCase().compareTo("ДА")==0)type = ReadType();
        else type = null;
        Person person;
        System.out.println("Хотите создать Person'a?(Да-если да)");
        if(scanner.nextLine().trim().toUpperCase().compareTo("ДА")==0)person = MakePerson();
        else person=null;
        return new Ticket(name, coordinates, price, type, person);
    }

    private Coordinates MakeCoordinates() {
        String in;
        Scanner scanner = new Scanner(System.in);
        float x;
        System.out.println("Введите координаты:");
        while (true) {
            System.out.print("\tX:");
            in = scanner.nextLine();
            if (TryParseFloat(in)) x = Integer.parseInt(in);
            else {
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный X, повторите ввод." + Color.ANSI_RESET);
                continue;
            }
            if (x > 176)
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный Y, повторите ввод." + Color.ANSI_RESET);
            else break;
        }
        Integer y;
        while (true) {
            System.out.print("\tY:");
            in = scanner.nextLine();
            if (TryParseInt(in)) y = Integer.parseInt(in);
            else {
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный Y, повторите ввод." + Color.ANSI_RESET);
                continue;
            }
            if (y > 178)
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный Y, повторите ввод." + Color.ANSI_RESET);
            else break;
        }
        return new Coordinates(x, y);
    }

    private TicketType ReadType() {
        System.out.println("Введите тип:");
        int index = 1;
        System.out.println("Доступные варианты:");
        for (TicketType c : TicketType.values()) {
            System.out.println("\t" + index + "." + c);
            index++;
        }
        boolean b = false;
        String type = null;
        Scanner scanner = new Scanner(System.in);
        while (!b) {
            System.out.print("Type:");
            type = scanner.nextLine();
            for (TicketType c : TicketType.values()) {
                if (c.name().equals(type.toUpperCase())) {
                    b = true;
                    break;
                }
            }
            if (!b)
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный тип, повторите ввод." + Color.ANSI_RESET);
        }
        return TicketType.valueOf(type.toUpperCase());
    }

    private Person MakePerson() {
        String in;
        System.out.println("Требуется создать человека:");
        Scanner scanner = new Scanner(System.in);
        String passportID;
        System.out.print("\tPassport ID:");
        passportID = scanner.nextLine();
        long weight;
        while (true) {
            System.out.print("\tWeight:");
            in = scanner.nextLine();
            if (TryParseLong(in)) weight = Long.parseLong(in);
            else {
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный вес, повторите ввод." + Color.ANSI_RESET);
                continue;
            }
            if (weight <= 0)
                System.out.println(Color.ANSI_RED + "Вы ввели неправильный вес, повторите ввод." + Color.ANSI_RESET);
            else break;
        }
        return new Person(weight, passportID);
    }

    private boolean TryParseInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean TryParseFloat(String string) {
        try {
            Float.parseFloat(string);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean TryParseLong(String string) {
        try {
            Long.parseLong(string);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}