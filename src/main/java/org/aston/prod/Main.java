package org.aston.prod;

import org.aston.prod.additionalTasks.task3.CustomList;
import org.aston.prod.menu.TestProgramMenu;

public class Main {
    public static void main(String[] args) {

        //TestProgramMenu.startMenu();
        CustomList<String> strings = new CustomList<>();
        strings.add("Hello");
        strings.add("W");
        strings.add("He");
        strings.add("Wor");
        strings.add("Hel");
        strings.remove("W");
        System.out.println(strings);
    }
}