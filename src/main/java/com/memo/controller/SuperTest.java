package com.memo.controller;

public class SuperTest extends Animal{


    public SuperTest(int age) {
        super(age);
    }

    public static void main(String[] args) {


        SuperTest s = new SuperTest(3);
        System.out.println("s.age = " + s.age);

    }
}

class Animal{
    protected int age;

    public Animal(int age) {
        this.age = age+5;
    }
}


