//Coding Polynomials (Linked List) - Assignment 4
//CS 113 - Data Structures and Algorithms
//Chris Ledet

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        int userSelection;
        ArrayList<Polynomial> polynomials = new ArrayList<>();

        int mainMenu = 0,
                subMenu = 1,
                selectPoly = 2,
                secondPolyIndex;

        do {
            userSelection = menuBuilder(mainMenu);

            switch(userSelection) {
                case 1: //Sub Menu for selection (1) in Main Menu
                    userSelection = menuBuilder(subMenu);
                    switch (userSelection) {
                        case 1:
                            //Add Polynomial
                            Polynomial p = new Polynomial();
                            polynomials.add(p);
                            addTermToPoly(p);
                            break;

                        case 2:
                            //Add terms to a Poly
                            if (polynomials.size() > 0) {
                                System.out.println("\nSelect the # of the Polynomial you want to edit");
                                printAllPolys(polynomials);
                                userSelection = menuBuilder(selectPoly);
                                addTermToPoly(polynomials.get(userSelection));
                            } else
                                System.out.println("\nNo Polynomials have been stored yet!");
                            break;

                        case 3:
                            //Delete a Polynomial
                            if (polynomials.size() > 0) {
                                System.out.println("Select the # of the Polynomial you want to delete");
                                printAllPolys(polynomials);
                                userSelection = menuBuilder(selectPoly);
                                polynomials.remove(userSelection);
                                System.out.println("Polynomial removed...");
                            } else
                                System.out.println("\nNo Polynomials have been stored yet!");
                            break;

                        default:
                            System.out.println("Invalid selection, please try again!");
                    }
                    break;

                case 2:
                    //Combine Polys
                    System.out.print("\nSelect the # of the first Polynomial");
                    printAllPolys(polynomials);
                    userSelection = menuBuilder(selectPoly);
                    System.out.print("\nSelect the # of the second Polynomial");
                    printAllPolys(polynomials);
                    secondPolyIndex = menuBuilder(selectPoly);

                    polynomials.add(polynomials.get(userSelection).add(polynomials.get(secondPolyIndex)));
                    break;

                case 3:
                    printAllPolys(polynomials);
                    break;

                default:
                    System.out.println("Invalid selection, please try again!");
            }
        } while(userSelection != 4);
    }

    //Method 1
    public static int menuBuilder(int menuNumber) {

        int userSelection = -1;

        switch (menuNumber) {
            case 0:
                System.out.println("\n~~~Polynomial Tracker~~~");
                System.out.println("Enter (1) to Input/Edit a Polynomial");
                System.out.println("Enter (2) to Combine your Polynomials");
                System.out.println("Enter (3) to View all Polynomials");
                System.out.println("Enter (4) to Quit");
                userSelection = keyboard.nextInt();
                keyboard.nextLine();
                if (userSelection == 4) {
                    System.out.println("Closing...");
                    System.exit(0);
                }
                break;

            case 1:
                System.out.println("\nEnter (1) to Create a new Polynomial");
                System.out.println("Enter (2) to Add Terms to a Polynomial");
                System.out.println("Enter (3) to Delete a Polynomial");
                userSelection = keyboard.nextInt();
                keyboard.nextLine();
                break;

            case 2:
                //Select a Polynomial by index
                System.out.print("Select a Polynomial: ");
                userSelection = keyboard.nextInt() - 1;
                keyboard.nextLine();
                break;
        }

        return userSelection;
    }

    //Method 2
    public static void printAllPolys(ArrayList<Polynomial> p) {
        System.out.println("\nAll Polynomials:");
        for (int i = 0; i < p.size(); i++) {
            System.out.println("(" + (i+1) + ") " + p.get(i));
        }
    }

    //Method 3
    public static void addTermToPoly(Polynomial p) {
        String userTerm;

        do {
            System.out.println("\nEnter a term to add to your polynomial (\"stop\" to quit): ");
            userTerm = keyboard.nextLine();
            userTerm = userTerm.replaceAll(" ", "");

            if (!(userTerm.equalsIgnoreCase("stop") || userTerm.equalsIgnoreCase("\"stop\""))) {
                p.addTerm(new Term(userTerm));
            }
        } while (!(userTerm.equalsIgnoreCase("stop") || userTerm.equalsIgnoreCase("\"stop\"")));
    }
}