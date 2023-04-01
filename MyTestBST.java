import java.util.Scanner;
import java.util.*;

// Name:Sebastian Mielko
// Class:CS 3305/Section#3
// Term:Spring2023
// Instructor: Prof. Majeed
// Assignment: 5


public class MyTestBST {
    public static void main(String[] args) {
        boolean isTreeAssigned = false; //used for the forced loop
        boolean isFinished = false; //used in while loop
        boolean isStringTree = false; //used throughout program, signals if the tree is type string

        BST myTree = new BST();
        do {
            Scanner myScanner = new Scanner(System.in);
            DisplayMenu();
            if (isTreeAssigned == true) {
                switch (myScanner.nextLine()) {
                    case "0":
                        System.out.println("Tree is already assigned");
                        break;
                    case "1":
                        if (isStringTree == true)
                            InsertString(myTree);
                        else InsertInteger(myTree);
                        break;
                    case "2":
                        if (isStringTree == true) DeleteString(myTree);
                        else DeleteInteger(myTree);
                        break;
                    case "3":
                        SearchForNode(myTree, isStringTree);
                        break;
                    case "4":
                        System.out.println("Tree size = " + myTree.size);
                        break;
                    case "5":
                        Path(myTree,isStringTree);
                        break;
                    case "6":
                        if (myTree.isEmpty() == false)
                            System.out.println("Tree is not empty");
                        else {
                            System.out.println("Tree is empty");
                        }
                        break;
                    case "7":
                        myTree.preorder();
                        break;
                    case "8":
                        myTree.inorder();
                        break;
                    case "9":
                        myTree.postorder();
                        break;
                    case "10":
                        isFinished = true;
                        break;
                    default:
                        System.out.println("Please provide a valid input");
                }
            } else { //forces user to choose tree type
                switch (myScanner.nextLine()) {
                    case "0":
                        System.out.print("\ninteger or string?: ");
                        switch (myScanner.next()) {
                            case "string":
                                isStringTree = true;
                                isTreeAssigned = true;
                                break;
                            case "integer":
                                isTreeAssigned = true;
                                break;
                            default:
                                System.out.println("Please indicate tree type");
                        }
                }
            }

        } while (!isFinished);
    }

    public static void InsertString(BST myTree) { //insertion, only strings
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter Input: ");
        String input = myScanner.next();
        myTree.insert(input);
        System.out.println("Successfully Inserted " + input);
        System.out.println("After Insertion (inorder):");
        myTree.inorder();
    }

    public static boolean SearchForNode(BST myTree, boolean isStringTree) { // returns a boolean, used in other method
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter the element that you are looking for: ");
        if (isStringTree == true) { //for tree strings
            String nodeToSearchFor = myScanner.next();
            boolean isFound = myTree.search(nodeToSearchFor); //invoking BST search method
            if (isFound == true) {
                System.out.println(nodeToSearchFor + " is in the Tree"); //verification methods
                return true;
            } else {
                System.out.println(nodeToSearchFor + " is not in the Tree");
                return false;
            }
        } else {
            try { //for int strings
                int nodeToSearchFor = myScanner.nextInt();
                myTree.search(nodeToSearchFor);
                boolean isFound = myTree.search(nodeToSearchFor); //calling search method in BST
                if (isFound == true) {
                    System.out.println(nodeToSearchFor + " is in the Tree");
                    return true;
                } else {
                    System.out.println(nodeToSearchFor + " is not in the Tree");
                    return false;
                }
            } catch (Exception e) { //catching strings entered instead of integer
                System.out.println("Enter a valid integer");
            }
        }
        return false; //by default = false
    }
    public static void DeleteString(BST myTree) { //same method as integer, without try/catch
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter the String that you want deleted: ");
        String NodeToDelete = myScanner.next();
        if (myTree.delete(NodeToDelete) == true) {
            System.out.println("Successfully deleted " + NodeToDelete);
            System.out.println("After Deletion (inorder):");
            myTree.inorder();
        } else {
            System.out.println("Failed to delete " + NodeToDelete);
            System.out.println("Current Tree (inorder):");
            myTree.inorder();
        }
    }

    public static void DeleteInteger(BST myTree) {
        try {
            Scanner myScanner = new Scanner(System.in);
            System.out.print("Enter the integer that you want deleted: ");
            int NodeToDelete = myScanner.nextInt();
            if (myTree.delete(NodeToDelete) == true) {//node found in string, and deleted
                System.out.println("Successfully deleted " + NodeToDelete);
                System.out.println("After Deletion (inorder):"); //verification print
                myTree.inorder();
            } else { //node not in tree
                System.out.println("Failed to delete " + NodeToDelete);
                System.out.println("Current Tree (inorder):");
                myTree.inorder();
            }
        } catch (Exception e) { //catching strings
            System.out.println("Please enter a valid integer");
        }
    }

    public static void InsertInteger(BST myTree) {
        try {
            Scanner myScanner = new Scanner(System.in);
            System.out.print("Enter Input: ");
            int input = myScanner.nextInt();
            myTree.insert(input);
            System.out.println("Successfully Inserted " + input);
            System.out.println("After Insertion (inorder):");
            myTree.inorder();
        } catch (InputMismatchException e) { //catching strings
            System.out.println("Please Enter an integer");

        }
    }
    public static void Path(BST myTree, boolean isStringTree) { //the bulk of this code is copied from the example
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter the element that you are looking for: ");
        if (isStringTree == true) //dealing with string trees
        {
            String pathElement = myScanner.next();
            if (myTree.search(pathElement) == true) { //checks if it exists
                System.out.print("\nA path from the root to "+ pathElement+ "is ");//verifying search element
                java.util.ArrayList<BST.TreeNode<String>> path = myTree.path(pathElement); /*creating an arraylist of strings
                                                                                            invokes path method with the pathElement as its argument
                                                                                            path element returns an arraylist
                                                                                            */
                for (int i = 0; path != null && i < path.size(); i++)
                    System.out.print(path.get(i).element + " "); //printing out the returned arraylist
                return;
            }
            else {
                System.out.println("No path available, element is not in the tree.");
            }
        }
        if (isStringTree == false) //dealing with int trees
        {
            int pathElement = myScanner.nextInt();
            System.out.print("\nA path from the root to "+ pathElement +" is: ");
            java.util.ArrayList<BST.TreeNode<Integer>> path = myTree.path(pathElement);
            for (int i = 0; path != null && i < path.size(); i++)
                System.out.print(path.get(i).element + " ");
            return;
        }
        else {
            System.out.println("No path available, element is not in the tree.");
        }
    }
    public static void DisplayMenu() { //simple menu display method
        System.out.print("\n----------------MAIN MENU---------------\n" +
                "0. Enter Tree Data Type (integer or string)\n" +
                "1. Insert Data Element\n" +
                "2. Delete Data Element\n" +
                "3. Search for Data Element\n" +
                "4. Print Tree Size\n" +
                "5. Path from Root to Data Element\n" +
                "6. Check if Empty Tree\n" +
                "7. Print Preorder Traversal\n" +
                "8. Print Inorder Traversal\n" +
                "9. Print Postorder Traversal\n" +
                "10. Exit program\n" +
                "Enter option number:");
    }
}