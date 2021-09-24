package com.Studio_Shravan.BST;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static class Node{
        Node left;
        Node right;
        int data;

        public Node(int data, Node left, Node right) {
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }

    public static Node construct(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node lc = construct(arr, start, mid-1);
        Node rc = construct(arr, mid + 1, end);
        Node root = new Node(arr[mid], lc, rc);
        return root;
    }
    public static Node construct(ArrayList<Integer> arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node lc = construct(arr, start, mid-1);
        Node rc = construct(arr, mid + 1, end);
        Node root = new Node(arr.get(mid), lc, rc);
        return root;
    }

    public static void display(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        display(root.left);
        display(root.right);
    }

    public static int size(Node root) {
        if (root == null) {
            return 0;
        }
        int size=1;
        int ls = size(root.left);
        int rs = size(root.right);
        return ls+rs+size;
    }

    public static int sum(Node root) {
        if (root == null) {
            return 0;
        }
        int sum=root.data;
        int ls = sum(root.left);
        int rs = sum(root.right);
        return ls+rs+sum;
    }

    public static int min(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null) {
            return root.data;
        }
        int lm = min(root.left);
        return lm;
    }
    public static int max(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.right == null) {
            return root.data;
        }
        int rm = max(root.right);
        return rm;
    }

    public static boolean findNode(Node root, int data) {
        if (root == null) {
            return false;
        }

        if (root.data == data) {
            return true;
        }
        if (data < root.data) {
            return findNode(root.left, data);
        } else if (data > root.data) {
            return findNode(root.right, data);
        }
        return false;
    }

    public static Node Insert(Node root, int data) {
        if (root == null) {
            return new Node(data, null, null);
        }
        if (root.data == data) {
            return null;
        }
        if (data < root.data) {
           root.left= Insert(root.left, data);
        } else if (data > root.data) {
            root.right= Insert(root.right, data);
        }
        return root;
    }

    public static Node removeNode(Node root, int data) {
        if (root == null) {
            return null;
        }
        if (data < root.data) {
            root.left = removeNode(root.left, data);
        } else if (data > root.data) {
            root.right = removeNode(root.right, data);
        }else{
            if (root.left != null && root.right != null) {
                int lmax = max(root.left);
                root.data=lmax;
                root.left=removeNode(root.left, lmax);
            } else if (root.left != null) {
                return root.left;
            } else if (root.right != null) {
                return root.right;
            }else{
                return null;
            }
        }
        return root;
    }
    static int sum=0;
    public static Node sumOfLarger(Node root) {
        if (root == null) {
            return null;
        }
        sumOfLarger(root.right);
        int temp=root.data;
        root.data = sum;
        sum+=temp;
        sumOfLarger(root.left);
        return root;
    }

    public static int lca(Node root, int a, int b) {
        if ((root.data > a && root.data < b) || (root.data < a && root.data > b)) {
            return root.data;
        }
        if (a<root.data && b < root.data) {
            return lca(root.left, a, b);
        } else if (a > root.data && b > root.data) {
            return lca(root.right, a, b);
        }
        return root.data;
    }

    public static void printInRange(Node root, int a, int b) {
        if (root == null) {
            return;
        }
        printInRange(root.left, a, b);
        if(root.data>=a && root.data <= b){
            System.out.print(root.data+" ");
        }
        printInRange(root.right, a, b);
    }

    public static Node leastGreaterElement(Node root) {
        if (root == null) {
            return null;
        }
        leastGreaterElement(root.left);
        int k=root.data;
        ArrayList<Integer> a =new ArrayList<>();
        inOrder(root,a, k);
        if (!a.isEmpty()) {
            root.data = a.get(0);
        }else {
            root.data = -1;
        }
        leastGreaterElement(root.right);
        return root;
    }

//    public static Node mergeBST(Node root1, Node root2) {
//            ArrayList<Integer> a1=new ArrayList<>();
//            ArrayList<Integer> a2=new ArrayList<>();
//            inOrder(root1, a1);
//            inOrder(root2, a2);
//        for (int i = 0; i < a2.size(); i++) {
//            a1.add(a2.get(i));
//        }
//        Collections.sort(a1);
//        return construct(a1, 0, a1.size() - 1);
//    }

    public static void inOrder(Node root, ArrayList<Integer> arrayList,int k) {
        if (root == null) {
            return ;
        }
        inOrder(root.left, arrayList,k);
        if(root.data>k) {
            arrayList.add(root.data);
        }
        inOrder(root.right, arrayList,k);
    }

//    public static int medianBST(Node root) {
//        ArrayList<Integer> a = new ArrayList<>();
//        inOrder(root, a);
//        int size=0;
//        if (a.size() % 2 != 0) {
//            size=a.size()-1/2;
//            return a.get(size);
//        }else{
//            size=a.size()/2;
//            int temp=size-1;
//            return (a.get(temp)+a.get(size))/2;
//        }
//    }
    static int count=1;
    public static int newBst(Node root,int median){
        if (root == null) {
            return count;
        }
         newBst(root.left, median);
        count++;
        if(count==median){
            return root.data;
        }
        count++;
         newBst(root.right, median);
        return root.data;
    }

    public static int median(Node root) {
        int size = size(root);
        if (size % 2 != 0) {
            return newBst(root, (size-1)/2);
        }else{
            int a=newBst(root,size/2);
            int b = newBst(root, a - 1);
            return (a+b)/2;
        }

    }
    public static void main(String[] args) {
        int[] a = {1, 4, 8, 12, 15, 19, 21, 42, 52, 59};
        int[] b = {13, 17, 22, 34, 57, 80, 89, 91, 99};
        Node root1= construct(a, 0, a.length-1);
        Node root2 = construct(b, 0, b.length-1);
//        Insert(root, 10);
//        removeNode(root, 42);
//        sumOfLarger(root);
//        root1 = mergeBST(root1, root2);
        root1 = leastGreaterElement(root1);
        display(root1);
        System.out.println();
//        System.out.println(medianBST(root1));
//        System.out.println(median(root1));
//        System.out.println();
//        display(root2);
//        System.out.println(lca(root,59,21));
//        printInRange(root,12,45);
//        System.out.println(size(root));
//        System.out.println(sum(root));
//        System.out.println(min(root));
//        System.out.println(max(root));
//        System.out.println(findNode(root,20));
    }
}
