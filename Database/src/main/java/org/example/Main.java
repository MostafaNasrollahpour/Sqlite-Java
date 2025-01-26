package org.example;


public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.selectData();
        dataBase.updateData("razi@gmail.com", 2);
        dataBase.deleteData(3);
        System.out.println();
        dataBase.selectData();

        dataBase.closeDataBase();
    }
}