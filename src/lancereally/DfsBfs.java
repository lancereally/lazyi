package lancereally;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DfsBfs {

    public static void main(String[] args) {
        TNode tNode = new TNode(1);
        TNode TNode2 = new TNode(2);
        TNode TNode3 = new TNode(3);
        TNode TNode4 = new TNode(4);
        TNode TNode5 = new TNode(5);
        TNode TNode6 = new TNode(6);
        TNode TNode7 = new TNode(7);


        tNode.left = TNode2;
        tNode.right = TNode3;

        TNode2.left = TNode4;
        TNode2.right = TNode5;

        TNode3.left = TNode6;
        TNode3.right = TNode7;

        System.out.println(BFS(tNode).get(1));
        for(int i : BFS(tNode)) {
           int j = i;
            System.out.print(i + " ");
        }
    }

    static class TNode{
        int val;
        TNode left;
        TNode right;
        public TNode(int val){
            this.val = val;
        }
    }

    static ArrayList<Integer> BFS (TNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TNode> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()){
            TNode temp = q.poll();
            list.add(temp.val);

            while (temp.left!=null)
                q.add(temp.left);

            while (temp.right!=null)
                q.add(temp.right);
        }

        return list;
    }

    static ArrayList<Integer> DFS (TNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TNode> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()){
            TNode temp = s.pop();
            list.add(temp.val);

            while (temp.right!=null)
                s.push(temp.right);

            while (temp.left!=null)
                s.push(temp.left);
            }

        return list;
    }
}
