package com.company;


import java.util.Objects;

public class Person {
    private String passportID; //Поле может быть null
    private long weight; //Значение поля должно быть больше 0

    public Person(long weight, String passportID) {
        if (!(weight < 0)) {
            this.weight = weight;
        } else throw new IllegalArgumentException("Значение weight должно быть >0");
        this.passportID = passportID;
    }

    public Person(long weight) {
        if (!(weight < 0)) this.weight = weight;
        else throw new IllegalArgumentException("Значение height должно быть >0");
    }

    @Override
    public String toString() {
        return "Person{passportID: " + passportID +
                " weight: " + weight+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return this.passportID.equals(person.passportID) ||
                this.weight == person.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportID, weight);
    }
}
