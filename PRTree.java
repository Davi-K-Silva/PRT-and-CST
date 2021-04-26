import java.util.ArrayList;

public class PRTree {
   private Node root;
   private int balancedComps;
   private ArrayList<String> bnodes;

  private static class Node {
    Node left, right ;
    String name;
    int E;

    Node( int n ) {
      E = n;
      name = "";
      right = left = null;
    }

    Node(String nm) {
        E = 0;
        name = nm;
        right = left = null;
    }
  }

  public PRTree(){
      root = null;
      balancedComps = 0;
      bnodes = new ArrayList<>();
  }

  public void add(String nmParent, String nmLeft, String nmRight){
    if(root == null){
        root = new Node(nmParent);
        root.left = new Node(nmLeft);
        root.right = new Node(nmRight);
    } 
    else 
    {
        Node target = searchFor(nmParent, root);
        target.left = new Node(nmLeft);
        target.right = new Node(nmRight);
    }
  }

  public void add(String nmParent, int eLeft, int eRight){
        Node target = searchFor(nmParent, root);
        target.left = new Node(eLeft);
        target.right = new Node (eRight);
  }

  private Node searchFor(String nm, Node nd){
    if(nd.name.equals(nm)) return nd;
    if(nd.left == null & nd.right == null) return null;
    Node n1 = searchFor(nm, nd.left);
    Node n2 = searchFor(nm, nd.right);
    if(n1 != null) return n1;
    else return n2;
  }

  public void regression(){
    balancedComps = 0;
    root.E = checkSons(root);
  }

  private int checkSons(Node nd){
   if(nd.left.E == 0 && nd.right.E == 0) {
     int se = checkSons(nd.left);
     int sr = checkSons(nd.right);
     if(se == sr) {
       bnodes.add(nd.name);
       balancedComps++;
     }
     nd.E = se + sr;
     return nd.E;
   } else {
     if(nd.left.E == nd.right.E) {
       bnodes.add(nd.name);
       balancedComps++;
     }
     nd.E = nd.left.E + nd.right.E;
     return nd.E;
   }
  }

  public ArrayList<String> getbNodes(){
    return bnodes;
  }

  public int geteqNodes(){
    return balancedComps;
  }
  
}

