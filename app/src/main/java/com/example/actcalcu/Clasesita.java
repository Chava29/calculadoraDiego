package com.example.actcalcu;

public class Clasesita
{
    private int numero1;
    private int numero2;

    public void setNumero1(int numero1) {
        this.numero1 = numero1;}

    public void setNumero2(int numero2) {
        this.numero2 = numero2;}

    public int sumita(){
        return numero1 + numero2;
    }

    public int restita()
    {
        return numero1 - numero2;
    }

    public int multiplicadita(){
        return numero1 * numero2;
    }

    public int divisioncita(){
        return numero1 / numero2;
    }
}
