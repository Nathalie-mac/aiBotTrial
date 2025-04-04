package org.example;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        String answ = BotClient.sendToBot("Что такое кандибобер", "123");
        System.out.println(answ);
    }
}