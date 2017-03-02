import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class HeapSort {
	 private static int N;
	    public static void sort(ArrayList<Node> q)
	    {       
	    	System.out.println("First round of heapify ");
	    	Node root,parent;
	    	int height,n=0;
	        heapify(q); 
	        System.out.println("Second round of heapify ");
	    	
	        for (int i = N; i > 0; i--)
	        {
	            swap(q,0, i);
	            root=q.get(0);
				height = root.getHeight();
				root.printTree(height);
	           if (i>=1)
	           {
		            parent=q.get((i-1)/2);
		            if (parent.right!=null && parent.right.data==q.get(i).data)
		            {
		            	parent.right=null;
		            }
		            else
		            {
		            	parent.left=null;
		            }
	            }
	          for (int k=N;k<=q.size()-1;k++)
		        {
		        	System.out.print("|"+q.get(k).data);
		        }
		        System.out.println();
	            N = N-1;
	            maxheap(q, 0);
	            root=q.get(0);
				height = root.getHeight();
				root.printTree(height);
			       
	        }
	        System.out.println("Final Sorted array : ");
	        for (int k=0;k<=q.size()-1;k++)
	        {
	        	System.out.print("|"+q.get(k).data);
	        }
	    }     
	    /* Function to build a heap */   
	    public static void heapify(ArrayList<Node> q)
	    {
	        N = q.size()-1;
	        for (int i = N/2; i >= 0; i--)
	        maxheap(q, i);   
	    }
	    /* Function to swap largest element in heap */        
	    public static void maxheap(ArrayList<Node> q, int i)
	    { 
	    	Node root;
	    	int height;
	        int left = 2*i+1 ;
	        int right = 2*i +2;
	        int max = i;
	        if (left <= N && q.get(left).data > q.get(i).data)
	            max = left;
	        if (right <= N && q.get(right).data > q.get(max).data)        
	            max = right;
	        root=q.get(0);
			height = root.getHeight();
			root.printTree(height);
	        if (max != i)
	        {
	            swap(q, i, max);
	            maxheap(q, max);
	        }
	      
	    }    
	    /* Function to swap two numbers in an array */
	    public static void swap(ArrayList<Node> q, int i, int j)
	    {
	        int tmp = q.get(i).data;
	        q.get(i).data = q.get(j).data;
	        q.get(j).data = tmp; 
	    }    

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  
		ArrayList<Node> q = new ArrayList<Node>();
		Node root = null;//new Node(0,null,null);
		Node current=null;
		
		int node=0,nbr;
			System.out.println("Please enter size of array : ");
			int size= reader.nextInt(); 
			for (int i=0;i<size;)
			{
				if (q.size()==0)
				{
					System.out.println("Please enter "+(i+1)+" number : ");
					nbr= reader.nextInt();
					root=new Node(nbr,null,null);
					q.add(root);
					current=root;
					i++;
				}
				else
				{
					System.out.println("Please enter "+(i+1)+" number : ");
					nbr= reader.nextInt();
					current=q.get(node);
					current.left=new Node(nbr,null,null);
					q.add(current.left);
					i++;
					if(i<size){
						System.out.println("Please enter "+(i+1)+" number : ");
						nbr= reader.nextInt();
						current.right=new Node(nbr,null,null);
						q.add(current.right);
						current=current.left;
						node++;
						i++;
					}
					
				}
			}
			
			System.out.println("Heap of the given array: ");
			
			root=q.get(0);
			int	height = root.getHeight();
			root.printTree(height);
			sort(q);
			
		
	}	
}