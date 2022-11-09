package com.example;

import java.util.Scanner;

public class CatAndWhale {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Node head = new Node("живет на суше");

        head.left = new Node("Кит");
        head.right = new Node("Кот");


        while (true){
            System.out.println("Загадай животное, а я попробую угадать...");

            Node node = head;
            Node lastQuestion = null;

            while (node.left != null && node.right != null){
                System.out.println("Это животное " + node.phrase + "? (Да/Нет)");
                String answer = scanner.nextLine();

                lastQuestion = node;

                if (answer.equalsIgnoreCase("Да")) node = node.right;
                else node = node.left;
            }

            System.out.println("Твое животное - " + node.phrase + "? (Да/Нет)");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Нет")){

                System.out.print("Какое животное ты загадал? ");
                Node animal = new Node(scanner.nextLine());

                System.out.print("Чем “"+ animal.phrase +"” отличается от “" + node.phrase + "”: ");
                Node newProperty = new Node(scanner.nextLine());
                newProperty.left = node;
                newProperty.right = animal;

                assert lastQuestion != null;
                if (lastQuestion.left == node) lastQuestion.left = newProperty;
                else lastQuestion.right = newProperty;
            }

            System.out.println("Сыграем еще раз? (Да/Нет)");
            if (scanner.nextLine().equalsIgnoreCase("Нет")) break;

        }
    }
}

class Node{
    String phrase;

    Node left = null;
    Node right = null;

    public Node(String phrase) {
        this.phrase = phrase;
    }
}
