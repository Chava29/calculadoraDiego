package com.example.actcalcu;

public class Clasesita {
    private double datito1;
    private double datito2;

    public void setDatito1(double datito1) {
        this.datito1 = datito1;
    }

    public void setDatito2(double datito2) {
        this.datito2 = datito2;
    }

    public double sumita() {
        return datito1 + datito2;
    }

    public double restita() {
        return datito1 - datito2;
    }

    public double multiplicadita() {
        return datito1 * datito2;
    }

    public double divisioncita() {
        if (Math.abs(datito2) < 1e-9) {
            throw new IllegalArgumentException("Division entre cero");
        }
        return datito1 / datito2;
    }
}
