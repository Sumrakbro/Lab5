package com.company;


import java.util.Date;
/**
 Класс объектов, которые содержаться в коллекции
 */
public class Ticket implements Comparable {
    private static Long ID = 1L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле может быть null

public Ticket(){
    this.id = ID;
    ID++;
    this.creationDate = new Date();
}

  public Ticket(String name, Coordinates coordinates, double price, TicketType type, Person person) {
        this.creationDate = new Date();
        this.id = ID;
        this.name = name;
        this.coordinates = coordinates;
        if (!(price < 0)) this.price = price;
        else throw new IllegalArgumentException("Значение price должно быть >0");
        this.type = type;
        this.person=person;
        this.creationDate = new Date();
        ID++;
    }

    public Ticket(String name, Coordinates coordinates, double price, Person person) {
        this.creationDate = new Date();
        this.id = ID;
        this.name = name;
        this.coordinates = coordinates;
        if (!(price < 0)) this.price = price;
        else throw new IllegalArgumentException("Значение price должно быть >0");
        this.person=person;
        this.creationDate = new Date();
        ID++;
    }

   public Ticket(String name, Coordinates coordinates, double price, TicketType type) {
       this.creationDate = new Date();
       this.id = ID;
       this.name = name;
       this.coordinates = coordinates;
       if (!(price < 0)) this.price = price;
       else throw new IllegalArgumentException("Значение price должно быть >0");
       this.type = type;
       this.creationDate = new Date();
       ID++;}

    public Ticket(String name, Coordinates coordinates, double price) {
        this.creationDate = new Date();
        this.id = ID;
        this.name = name;
        this.coordinates = coordinates;
        if (!(price < 0)) this.price = price;
        else throw new IllegalArgumentException("Значение price должно быть >0");
        this.creationDate = new Date();
        ID++;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Long getid() {
        return this.id;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Ticket))
            throw new IllegalArgumentException("Объект класса Ticket можно сравнить только с объектами этого класса ");
        else {
            int result = 0;
            Ticket t = (Ticket) o;
            if(!(this.type==null||t.type==null)) {
                if (t.type.equals(this.type)) result = this.id.compareTo(t.id);
                else result = this.type.compareTo(t.type);
            }
            else if(this.type==null&&t.type==null)  result = this.id.compareTo(t.id);
            else  if(this.type==null) return -1;
            else if(t.type==null )return 1;
            return result;
        }
    }

    @Override
    public String toString() {
        return "Ticket;" +
                "id:" + id +
                ";name:'" + name + '\'' +
                ";coordinates:" + coordinates +
                ";creationDate:" + creationDate +
                ";price:" + price +
                ";type:" + type +
                ";person:" + person +
                "\n";
    }
}
