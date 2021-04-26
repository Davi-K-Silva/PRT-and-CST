import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Test {
    public static void main(String[] args){

        if(args[0].toLowerCase().equals("prt")){
            if(!args[1].toLowerCase().equals("all")){
                final long startTime = System.nanoTime();
        
                PRTree prTree = new PRTree();
                try {
                File myObj = new File("testCases/" + args[1]);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) { 
                    String data = myReader.nextLine();
                    String[] nodes = data.split(" ");
                    if(!nodes[1].contains("X")){
                    prTree.add(nodes[0], Integer.parseInt(nodes[1]), Integer.parseInt(nodes[2]));
                    } else {
                    prTree.add(nodes[0], nodes[1], nodes[2]);
                    }
                }
                myReader.close();
                } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }
        
                prTree.regression();
        
                final long elapsedTimeMillis = (System.nanoTime() - startTime)/ 1000000;
                System.out.println("Nodos em equilibrio: " + prTree.geteqNodes());
                String bnodes = "";
                for(String n : prTree.getbNodes()){
                bnodes += n + " ";
                }
                System.out.println("São equilibrados:" + bnodes);
                System.out.println("Tempo de processamento: " + elapsedTimeMillis + " ms");
        
            } else {
                
                File files = new File("./testCases");
                String[] pathnames = files.list();
                
                for(String file : pathnames){
                    String path = "testCases/" + file;
                    System.out.println("------------------------------------------------");
                    System.out.println("Arquivo: " + file);
        
                    PRTree prTree = new PRTree();
                    try {
                    File myObj = new File(path);
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) { 
                        String data = myReader.nextLine();
                        String[] nodes = data.split(" ");
                        if(!nodes[1].contains("X")){
                        prTree.add(nodes[0], Integer.parseInt(nodes[1]), Integer.parseInt(nodes[2]));
                        } else {
                        prTree.add(nodes[0], nodes[1], nodes[2]);
                        }
                    }
                    myReader.close();
                    } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    }
        
                    prTree.regression();
    
                    System.out.println("Nodos em equilibrio: " + prTree.geteqNodes());
                    String bnodes = "";
                    for(String n : prTree.getbNodes()){
                    bnodes += n + " ";
                    }
                    System.out.println("São equilibrados:" + bnodes);

                    System.out.println("------------------------------------------------");
                }
            }
        } else if(args[0].toLowerCase().equals("cst")){
            if(!args[1].toLowerCase().equals("all")){
                final long startTime = System.nanoTime();
                ArrayList<Integer> finalNodes = new ArrayList<Integer>();
                try {
                
                File myObj = new File("testCases/" + args[1]);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) { 
                    String data = myReader.nextLine();
                    String[] nodes = data.split(" ");
                    if(!nodes[1].contains("X")){
                        finalNodes.add(Integer.parseInt(nodes[1]));
                        finalNodes.add(Integer.parseInt(nodes[2]));
                    } 
                }
                myReader.close();
                } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }

                int[] input = new int[finalNodes.size()];
                int pos = 0;
                for(Integer i : finalNodes){
                    input[pos] = i.intValue();
                    pos++;
                }
        
                int n = input.length;
                int height = (int)(Math.log(n)/Math.log(2)) + 1;
                int tree_nodes = (int) Math.pow(2, height + 1);
                CSTree csTree = new CSTree(tree_nodes);
                csTree.build(input, 0, 0, n - 1);

                int eqnodes = csTree.getEq();
                final long elapsedTimeMillis = (System.nanoTime() - startTime)/ 1000000;
                System.out.println("Nodos em equilibrio: " + eqnodes);
                System.out.println("Tempo de processamento: " + elapsedTimeMillis + " ms");

            } else {

                File files = new File("./testCases");
                String[] pathnames = files.list();

                for(String file : pathnames){
                    String path = "testCases/" + file;
                    System.out.println("------------------------------------------------");
                    System.out.println("Arquivo: " + file);

                    ArrayList<Integer> finalNodes = new ArrayList<Integer>();
                    try {
                    
                    File myObj = new File(path);
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) { 
                        String data = myReader.nextLine();
                        String[] nodes = data.split(" ");
                        if(!nodes[1].contains("X")){
                            finalNodes.add(Integer.parseInt(nodes[1]));
                            finalNodes.add(Integer.parseInt(nodes[2]));
                        } 
                    }
                    myReader.close();
                    } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    }

                    int[] input = new int[finalNodes.size()];
                    int pos = 0;
                    for(Integer i : finalNodes){
                        input[pos] = i.intValue();
                        pos++;
                    }
            
                    int n = input.length;
                    int height = (int)(Math.log(n)/Math.log(2)) + 1;
                    int tree_nodes = (int) Math.pow(2, height + 1);
                    CSTree csTree = new CSTree(tree_nodes);
                    csTree.build(input, 0, 0, n - 1);

                    int eqnodes = csTree.getEq();
                
                    System.out.println("Nodos em equilibrio: " + eqnodes);
                    System.out.println("------------------------------------------------");
                }
            }

        }
    
    }
}
