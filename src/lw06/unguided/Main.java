package lw06.unguided;

import java.util.Scanner;
import java.io.File;
import java.util.*;

class BinarySearchTree {
    Node root;

    public void insert(int data){
        root = insertHelper(root, data);
    }

    private Node insertHelper(Node root, int data){
        if(root==null){
            root = new Node(data);
        }else if(data < root.data){
            root.left = insertHelper(root.left, data);
        }else if(data > root.data){
            root.right = insertHelper(root.right, data);
        }
        return root;
    }

    public boolean search(int target){
        return searchHelper(root, target);
    }

    private boolean searchHelper(Node root, int target){
        if(root==null){
            return false;
        }

        if(target == root.data){
            return true;
        }else if(target < root.data){
            return searchHelper(root.left, target);
        }else{
            return searchHelper(root.right, target);
        }
    }

    public int countNodes(){
        return countNodesHelper(root);
    }

    public int countLeaves(){
        return countLeavesHelper(root);
    }

    public int countNodesHelper(Node root){
        if(root==null){
            return 0;
        }
        return 1 + countNodesHelper(root.left) + countNodesHelper(root.right);
    }

    public int countLeavesHelper(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return 1;
        }
        return countLeavesHelper(root.left) + countLeavesHelper(root.right);
    }

    public void inOrder(){
        inOrderHelper(root);
        System.out.println();
    }

    public void postOrder(){
        postOrderHelper(root);
        System.out.println();
    }

    public void preOrder(){
        preOrderHelper(root);
        System.out.println();
    }

    public void inOrderHelper(Node root){
        if(root==null){
            return;
        }
        inOrderHelper(root.left);
        System.out.print(root.data + " ");
        inOrderHelper(root.right);
    }

    public void postOrderHelper(Node root){
        if(root==null){
            return;
        }
        postOrderHelper(root.left);
        postOrderHelper(root.right);
        System.out.print(root.data + " ");
    }

    public void preOrderHelper(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data + " ");
        preOrderHelper(root.left);
        preOrderHelper(root.right);
    }
}

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
Node root = null;

        // Write your main code here


        BinarySearchTree bst = new BinarySearchTree();
    if(read.hasNextInt()){
        String line1 = read.nextLine();
        String[] numbers = line1.split(" ");
        for(String numStr : numbers){
            int number = Integer.parseInt(numStr);
            bst.insert(number);
        }

        int target = read.nextInt();
        read.close();
        
        // in order
        System.out.println("Inorder :");
        bst.inOrder();

        //  post order
        System.out.println("Postorder :");
        bst.postOrder();

        // pre order
        System.out.println("Preorder :");
        bst.preOrder();


        System.out.println(" Search " + target + " : " + bst.search(target));


        //  nodes 
        System.out.println("Number of nodes: " + bst.countNodes());

        // leaves 
        System.out.println("Number of leaves: " + bst.countLeaves());


        

    }
}

}
