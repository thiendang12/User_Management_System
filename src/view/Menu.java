/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import util.Library;

/**
 *
 * @author ADMIN
 */
public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChon;
    Library l = new Library();

    public Menu() {
        mChon = new ArrayList<>();
    }

    public Menu(String td, String[] mc) {
        title = td;
        mChon = new ArrayList<>();
        for (String s : mc) {
            mChon.add((T) s);
        }
    }

    //-------------------------------------------
    public void display() {
        System.out.println(title);
        System.out.println("--------------------------------");
        for (int i = 0; i < mChon.size(); i++) {
            System.out.println((i + 1) + "." + mChon.get(i));
        }
        System.out.println("--------------------------------");
    }
//-------------------------------------------

public int getSelected() {
    int choice;
    while (true) {
        display();
        String input = l.getString("Enter your choice");
        try {
            choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= mChon.size() + 1) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        }
    }
    return choice;
}

//-------------------------------------------
    public abstract boolean execute(int n);
//-------------------------------------------

    public void run() {
        boolean check = true;
        do {
            int n = getSelected();
            check = execute(n);
            if (n > mChon.size()) {
                break;
            }
        } while (check);
    }

}
