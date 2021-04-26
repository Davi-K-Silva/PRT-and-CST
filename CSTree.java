public class CSTree{
    public int[] tree;
    private int eq;

    public CSTree(int n){
        tree = new int[n];
        eq = 0;
    }

    public void build(int[] arr, int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
        } else {
            int leftIndex = 2*node + 1;
            int rightIndex = 2*node + 2;
            int mid = (start + end)/2;
            build(arr, leftIndex, start, mid);
            build(arr, rightIndex, mid + 1, end);
            if(tree[leftIndex] == tree[rightIndex]){
                eq++;
            }
            tree[node] = tree[leftIndex] + tree[rightIndex];
        }
    }

    public int getEq(){
        return eq;
    }

}

