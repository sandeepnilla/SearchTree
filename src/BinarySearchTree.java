//package tree_printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Node {
	public int data;
	public Node left;
	public Node right;
	public Node(int data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right=right;
	}
	public void printTree(int height) {
		System.out.println(printTree(this, 1, height));
	}
	private StringBuilder printTree(Node root, int currentHeight, int totalHeight) {
		StringBuilder sb = new StringBuilder();
		int spaces = getSpaceCount(totalHeight-currentHeight + 1);
		if(root == null) {
			//create a 'spatial' block and return it
			String row = String.format("%"+(2*spaces+1)+"s%n", "");
			//now repeat this row space+1 times
			String block = new String(new char[spaces+1]).replace("\0", row);
			return new StringBuilder(block);
		}
		if(currentHeight==totalHeight) return new StringBuilder(root.data+"");
		int slashes = getSlashCount(totalHeight-currentHeight +1);
		sb.append(String.format("%"+(spaces+1)+"s%"+spaces+"s", root.data+"", ""));
		sb.append("\n");
		//now print / and \
		// but make sure that left and right exists
		char leftSlash = root.left == null? ' ':'/';
		char rightSlash = root.right==null? ' ':'\\';
		int spaceInBetween = 1;
		for(int i=0, space = spaces-1; i<slashes; i++, space --, spaceInBetween+=2) {
			for(int j=0; j<space; j++) sb.append(" ");
			sb.append(leftSlash);
			for(int j=0; j<spaceInBetween; j++) sb.append(" ");
			sb.append(rightSlash+"");
			for(int j=0; j<space; j++) sb.append(" ");
			sb.append("\n");
		}
		//sb.append("\n");

		//now get string representations of left and right subtrees
		StringBuilder leftTree = printTree(root.left, currentHeight+1, totalHeight);
		StringBuilder rightTree = printTree(root.right, currentHeight+1, totalHeight);
		// now line by line print the trees side by side
		Scanner leftScanner = new Scanner(leftTree.toString());
		Scanner rightScanner = new Scanner(rightTree.toString());
//		spaceInBetween+=1;
		while(leftScanner.hasNextLine()) {
			if(currentHeight==totalHeight-1) {
				sb.append(String.format("%-2s %2s", leftScanner.nextLine(), rightScanner.nextLine()));
				sb.append("\n");
				spaceInBetween-=2;				
			}
			else {
				sb.append(leftScanner.nextLine());
				sb.append(" ");
				sb.append(rightScanner.nextLine()+"\n");
			}
		}
		return sb;

	}
	private int getSlashCount(int height) {
		if(height <= 3) return height -1;
		return (int) (3*Math.pow(2, height-3)-1);
	}
	private int getSpaceCount(int height) {
		return (int) (3*Math.pow(2, height-2)-1);
	}
	public void print() {
		inorder(this);
		System.out.println();
	}
	private void inorder(Node root) {
		if (root==null) return;
		inorder(root.left);
		System.out.print(root.data+" ");
		inorder(root.right);
	}
	public int getHeight() {
		return getHeight(this);
	}
	private int getHeight(Node root) {
		if (root == null) return 0;
		return Math.max(getHeight(root.left), getHeight(root.right))+1;
	}
	@Override
	public String  toString() {
		return this.data+"";
	}
}
public class BinarySearchTree {
	
	public static void createBST(ArrayList<Node> bstArray) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, height;
		Node root=null;
		
		try {
			System.out.println("Please enter a number: ");
			N = Integer.parseInt(br.readLine());
			if (bstArray.size()==0)
			{
				root =  new Node(N,null,null);
				bstArray.add(root);
				return;
			}
			else
			{
				int rootValue=bstArray.get(0).data;
				Node current =bstArray.get(0);
				root=bstArray.get(0);
				if (rootValue>N )
				{
					current=setLeftNode(bstArray,current,N);
				}
				else
				{
					current=setRightNode(bstArray,current,N);
				}
			}
			height = root.getHeight();
			root.printTree(height);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//To set the value as a left node to parent node
	public static Node setLeftNode(ArrayList<Node> bstArray,Node current,int N)
	{
		while(current.left!= null )
		{
			current =current.left;
			if (current.data<= N)
			{
				current=setRightNode(bstArray,current,N);
				return current;
			}
		}
			current.left = new Node(N,null,null);
			bstArray.add(current.left);
		return current;
	}
	//To set the value as a right node to parent node
	public static Node setRightNode(ArrayList<Node> bstArray,Node current,int N)
	{
		while(current.right!= null )
		{
			current =current.right;
			if (current.data>= N)
			{
				current=setLeftNode(bstArray,current,N);
				return current;
			}
		}
			current.right = new Node(N,null,null);
			bstArray.add(current.right);
		return current;
	}
	//To search the value in BST
	public static boolean searchBST(ArrayList<Node> bstArray,int nbr)
	{
		Node n=bstArray.get(0);
		 while(n.data!=nbr){
		   if(nbr < n.data) n=n.left;
		   else n=n.right;
		   if(n==null) return false;
		 }
		 return true;
	}
	//To delete a node in BST
	public static void deleteNode (ArrayList<Node> bstArray,int nbr)
	{
		Node current=bstArray.get(0);
		Node root =bstArray.get(0);
		Node parent=current;
		
		while(current.data!=nbr)
		 {
			 parent=current;
			 if(nbr < current.data) 
			 {
				 current=current.left;
			 }
			 else
			 {
				 current=current.right;
			 }
		 }
		int i;
		for (i=0;i<bstArray.size()-1;i++)
		{
			if(	bstArray.get(i).data==nbr)
			{
				break;
			}
		}
		
		if (current.left==null && current.right==null)
		{
			  if ( current.data == bstArray.get(0).data )
	          {
	             bstArray.remove(0);
	             return;
	          }
	          if ( parent.left == current )
	             parent.left = null;
	          else
	             parent.right = null;
	          bstArray.remove(i);
	          return;
		}
		else if (current.left==null || current.right==null){
		
			 if ( current.left == null )    
			 {
				 if ( current == root )
			      {
		             bstArray.remove(0);
		             return;
		          }     
		          if ( parent.left == current )
		          {
		             parent.left  = current.right;   
		          }
		          else
		          { 
		        	  parent.right = current.right;   
		          }
		          bstArray.remove(i);
		          return;
			 }
		       if ( current.right == null )         
		       {
		          if ( current == root )
		          {
		             bstArray.remove(0);
		             return;
		          }
		          if ( parent.left == current )
		             parent.left = current.left;
		          else
		             parent.right = current.left;
		          bstArray.remove(i);
		          return;
		       }
		}
		else
		{
			Node toberemoved=current;
			Node currentNode=null;
			current=current.left;
			if (current.right==null)
			{
				toberemoved.data=current.data;
				toberemoved.left=current.left;
				return;
			}
			while (current.right!=null)
			{
				currentNode=current;
				current=current.right;
			}
				toberemoved.data=current.data;
				currentNode.right=null;
				if (i!=0)
				{
					bstArray.remove(i);
				}
		}
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  
		int searchNbr;
		ArrayList<Node> bstArray = new ArrayList<Node>();
		Node root = null;//new Node(0,null,null);
		while(true)
		{
			System.out.println("Please select an option: ");
			System.out.println(" 1. Insert \n 2. Delete \n 3. Search \n 4. Exit");
			int option = reader.nextInt(); 
			switch (option)
			{
			case 1: 
				createBST(bstArray);
				break;
			case 2:
				System.out.println("Please enter a number: ");
				searchNbr=reader.nextInt(); 
				if(searchBST(bstArray,searchNbr))
				{
					deleteNode(bstArray,searchNbr);
					
					if (bstArray.size()==0)
					{
						System.out.println("No elements in BST");
					}
					else
					{
						root=bstArray.get(0);
						int height = root.getHeight();
						root.printTree(height);
					}
				}
				else
				{
					System.out.println("Number not found");
				}
				
				break;
			case 3:
				System.out.println("Please enter a number: ");
				searchNbr=reader.nextInt(); 
				if(searchBST(bstArray,searchNbr))
				{
					System.out.println("Number found");
				}
				else
				{
					System.out.println("Number not found");
				}
				
				break;
			case 4:
				System.out.println("Exit Successful ");
				return;
				//break;
            default: break;
			}
		}
		
	}
	
}
