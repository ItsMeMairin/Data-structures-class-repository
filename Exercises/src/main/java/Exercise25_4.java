/*
(Implement set operations in Tree)

When you create a new submission, you'll notice a template code. The implementations of the methods containsAll(), addAll, removeAll, retainAll, and toArray(T[]) are omitted in the Tree interface.

Your task is to implement these methods by filling in the missing code parts indicated by "WRITE YOUR CODE".

For a hint on this program, please see https://liveexample.pearsoncmg.com/javarevel13e.html.
*/

// Look for WRITE YOUR CODE to write your code
import java.util.*;

public class Exercise25_4{
  public static void main(String[] args) {
    new Exercise25_4();
  }

  public Exercise25_4() {
    Scanner input = new Scanner(System.in);
    String[] name1 = new String[5];
    String[] name2 = new String[5];
    String[] name3 = new String[2];
    System.out.print("Enter five strings for array name1 separated by space: ");
    for (int i = 0; i < 5; i++) {
      name1[i] = input.next();
    }
    
    System.out.print("Enter five strings for array name2 separated by space: ");
    for (int i = 0; i < 5; i++) {
      name2[i] = input.next();
    }

    System.out.print("Enter two strings for array name3 separated by space: ");
    for (int i = 0; i < 2; i++) {
      name3[i] = input.next();
    }
    
    BST<String> tree1 = new BST<>(name1);   
    BST<String> tree2 = new BST<>(name2);   
    System.out.println("tree1: " + toString(tree1));
    System.out.println("tree2: " + toString(tree2));
    tree1.addAll(tree2);
    System.out.println("After addAll: tree1 is " + toString(tree1) + "\n");
    
    tree1 = new BST<>(name1);
    tree2 = new BST<>(name2);   
    System.out.println("tree1: " + toString(tree1));
    System.out.println("tree2: " + toString(tree2));
    tree1.removeAll(tree2);
    System.out.println("After removeAll: tree1 is " + toString(tree1) + "\n");
    
    tree1 = new BST<>(name1);
    tree2 = new BST<>(name2);   
    System.out.println("tree1: " + toString(tree1));
    System.out.println("tree2: " + toString(tree2));
    tree1.retainAll(toString(tree2));
    System.out.println("After retainAll: tree1 is " + toString(tree1) + "\n");
    
    tree1 = new BST<>(name1);
    tree2 = new BST<>(name2);   
    System.out.println("tree1: " + toString(tree1));
    System.out.println("tree2: " + toString(tree2));
    tree1.retainAll(tree2);
    System.out.println("tree1 contains all tree2? " + tree1.containsAll(tree2) + "\n");
    
    tree1 = new BST<>(name1);
    tree2 = new BST<>(name3);
    System.out.println("tree1: " + toString(tree1));
    System.out.println("tree2: " + toString(tree2));
    System.out.println("tree1 contains all tree2? " + tree1.containsAll(tree2) + "\n");
    
    Object[] name4 = tree1.toArray();
    System.out.print("name4: ");
    for (Object e: name4) {
      System.out.print(e + " ");
    }
    
    String[] name5 = new String[tree1.size()];
    String[] name6 = tree1.toArray(name5);
    System.out.print("\nname6: ");
    for (Object e: name6) {
      System.out.print(e + " ");
    }
  }
  
  public static <E> List<E> toString(Tree tree) {
    List<E> list = new ArrayList<>();
    for (Object e: tree)
      list.add((E)e);
    return list;
  }
}

class BST<E> implements Tree<E> {
  protected TreeNode<E> root;
  protected int size = 0;
  protected java.util.Comparator<E> c; 

  /** Create a default BST with a natural order comparator */
  public BST() {
    this.c = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
  }

  /** Create a BST with a specified comparator */
  public BST(java.util.Comparator<E> c) {
    this.c = c;
  }

  /** Create a binary tree from an array of objects */
  public BST(E[] objects) {
    this.c = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  @Override /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      if (c.compare(e, current.element) < 0) {
        current = current.left;
      }
      else if (c.compare(e, current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }

    return false;
  }

  @Override /** Insert element e into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (c.compare(e, current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (c.compare(e, current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false; // Duplicate node not inserted

      // Create the new node and attach it to the parent node
      if (c.compare(e, parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }

    size++;
    return true; // Element inserted successfully
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<>(e);
  }

  @Override /** Inorder traversal from the root */
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  /** This inner class is static, because it does not access 
      any instance members defined in its outer class */
  public static class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  @Override /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode<E> getRoot() {
    return root;
  }

  /** Returns a path from the root leading to the specified element */
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list =
      new java.util.ArrayList<>();
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      list.add(current); // Add the node to the list
      if (c.compare(e, current.element) < 0) {
        current = current.left;
      }
      else if (c.compare(e, current.element) > 0) {
        current = current.right;
      }
      else
        break;
    }

    return list; // Return an array list of nodes
  }

  @Override /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (c.compare(e, current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (c.compare(e, current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed at by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left child
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      }
      else {
        if (c.compare(e, parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;     
    }

    size--; // Reduce the size of the tree
    return true; // Element deleted successfully
  }

  @Override /** Obtain an iterator. Use inorder. */
  public java.util.Iterator<E> iterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  private class InorderIterator implements java.util.Iterator<E> {
    // Store the elements in a list
    private java.util.ArrayList<E> list =
      new java.util.ArrayList<>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null) return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    @Override /** More elements for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;

      return false;
    }

    @Override /** Get the current element and move to the next */
    public E next() {
      return list.get(current++);
    }

    @Override // Remove the element returned by the last next()
    public void remove() {
      if (current == 0) // next() has not been called yet
        throw new IllegalStateException(); 

      delete(list.get(--current)); 
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  @Override /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
}

interface Tree<E> extends Collection<E> {
  /** Return true if the element is in the tree */
  public boolean search(E e);

  /** Insert element e into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e);

  /** Delete the specified element from the tree
   * Return true if the element is deleted successfully */
  public boolean delete(E e);
  
  /** Get the number of elements in the tree */
  public int getSize();
  
  /** Inorder traversal from the root*/
  public default void inorder() {
  }

  /** Postorder traversal from the root */
  public default void postorder() {
  }

  /** Preorder traversal from the root */
  public default void preorder() {
  }
  
  @Override /** Return true if the tree is empty */
  public default boolean isEmpty() {
    return this.size() == 0;
  }

  @Override
  public default boolean contains(Object e) {
    return search((E)e);
  }
  
  @Override
  public default boolean add(E e) {
    return insert(e);
  }
  
  @Override
  public default boolean remove(Object e) {
    return delete((E)e);
  }
  
  @Override
  public default int size() {
    return getSize();
  }
  
  @Override
  public default boolean containsAll(Collection<?> c) {
    // WRITE YOUR CODE
  }

  @Override
  public default boolean addAll(Collection<? extends E> c) {
    // WRITE YOUR CODE
  }

  @Override
  public default boolean removeAll(Collection<?> c) {
    // WRITE YOUR CODE 
  }

  @Override
  public default boolean retainAll(Collection<?> c) {
    // WRITE YOUR CODE
  }

  @Override
  public default Object[] toArray() {
    // WRITE YOUR CODE
  }

  @Override
  public default <T> T[] toArray(T[] a) {
    // WRITE YOUR CODE
  }
}