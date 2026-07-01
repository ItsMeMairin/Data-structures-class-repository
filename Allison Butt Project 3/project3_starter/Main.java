/*
Allison Butt CMSC 315 6380 Project 3 
22 June 26
This Main class is responsible for prompting user input and calling  methods within the CompleteBinaryTree.java class, printing them in response. 
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try(Scanner input = new Scanner(System.in)) { 

            System.out.print("Enter a binary tree: ");
            String treeString = input.nextLine();

            CompleteBinaryTree tree = new CompleteBinaryTree(treeString);

            tree.preorder();

            System.out.println("Is a max-heap: " + tree.isMaxHeap());
            System.out.println("Is a BST: " + tree.isBinarySearchTree());

            System.out.println("Inorder List: " + tree.inorderList());

        } catch (InvalidTreeException e) {
            System.out.println(e.getMessage());
        }

    }


}
