import java.util.Scanner;
import java.util.*;
// Name:Sebastian Mielko
// Class:CS 3305/Section#3
// Term:Spring2023
// Instructor: Prof. Majeed
// Assignment: 5
public class RemoveDuplicates {
    public static void main(String[] args) {
        BST<String> myTree = new BST<String>();
        boolean isFinished = false;
        while (!isFinished) {
            Scanner myScanner = new Scanner(System.in);
            String inputStatement = "";
            DisplayMenu();
            String selection = myScanner.next();
            switch (selection)
            {
                case "1":
                    inputStatement = DelimitStringAndInsert(myTree); //this will intake the input string
                    break;
                case "2":
                    System.out.println(inputStatement); //input values
                    myTree.inorder(); //printing tree in order
                    break;
                case "3":
                    isFinished = true;
                    break;
                default:
                    System.out.println("Enter valid selection");
            }
        }
    }
    public static void DisplayMenu() //simple menu method
    {
        System.out.println("\n-----------------MAIN MENU---------------\n" +
                "1. Read input string\n" +
                "2. Remove duplicates and display outputs\n" +
                "3. Exit program\n");
        System.out.print("Selection: ");
    }

    public static String DelimitStringAndInsert(BST myTree) //takes the input and delimits it
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter string: ");
        String input = myScanner.nextLine();;
        String delimitedArray [] = input.split("\\s+");
        System.out.println(Arrays.toString(delimitedArray));
        for (String word : delimitedArray) //inserts it into a string BST
        {
            myTree.insert(word); //insertion method automatically removes duplicates from the input
        }
        return input;
    }


}

