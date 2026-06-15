import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try(Scanner input = new Scanner(System.in)) {
            //Integer[] values = { 90, 70, 50, 20, 40 };
            //CompleteBinaryTree tree = new CompleteBinaryTree(values);
            System.out.print("Enter a binary tree: ");
            String treeString = input.nextLine();
            CompleteBinaryTree tree = new CompleteBinaryTree(treeString);
            tree.preorder();
        } catch (InvalidTreeException e) {
            System.out.println(e.getMessage());
        }

    }


}
