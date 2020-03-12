package com.company;

public class Coordinates {
    private double x; //Максимальное значение поля: 176, Поле не может быть null
    private float y; //Максимальное значение поля: 178


    Coordinates(double x, float y) {
        if (x > 176) throw new IllegalArgumentException("Значение x должно быть < 176");
        else this.x = x;
        if (y > 178) throw new IllegalArgumentException("Значение y должно быть < 178");
        else this.y = y;

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
