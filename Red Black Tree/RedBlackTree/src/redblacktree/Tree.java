/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

import java.util.Scanner;

;

public class Tree  {

	private Node nill;
	Node root;
	Node q;

	
	
	
	
	public Scanner input = new Scanner(System.in);

	Tree() {
		nill = new Node();
		nill.color = Node.BLACK;
		nill.left = null;
		nill.right = null;
		q = null;
		root = nill;
	}

	public void insert(int key) {
		Node node = new Node();
		node.parent = null;
		node.key = key;
		node.left = nill;
		node.right = nill;
		node.color = 1;

		Node y = null;
		Node x = this.root;

		while (x != nill) {
			y = x;
			if (node.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		node.parent = y;
		if (y == null) {
			root = node;
		} else if (node.key < y.key) {
			y.left = node;
		} else {
			y.right = node;
		}

		if (node.parent == null) {
			node.color = 0;
			return;
		}

		if (node.parent.parent == null) {
			return;
		}

		fixInsert(node);
	}

	public void delete(int key) {
		UtilitydeleteNode(this.root, key);
	}


	
	private void fixInsert(Node node) {
		Node u;
		while (node.parent.color == 1) {
			if (node.parent == node.parent.parent.right) {
				u = node.parent.parent.left;
				if (u.color == 1) {
					u.color = 0;
					node.parent.color = 0;
					node.parent.parent.color = 1;
					node = node.parent.parent;
				} else {
					if (node == node.parent.left) {
						node = node.parent;
						rightRotate(node);
					}
					node.parent.color = 0;
					node.parent.parent.color = 1;
					leftRotate(node.parent.parent);
				}
			} else {
				u = node.parent.parent.right;

				if (u.color == 1) {
					u.color = 0;
					node.parent.color = 0;
					node.parent.parent.color = 1;
					node = node.parent.parent;
				} else {
					if (node == node.parent.right) {
						node = node.parent;
						leftRotate(node);
					}
					node.parent.color = 0;
					node.parent.parent.color = 1;
					rightRotate(node.parent.parent);
				}
			}
			if (node == root) {
				break;
			}
		}
		root.color = 0;
	}
	
	private void leftRotate(Node node) {
		Node y = node.right;
		node.right = y.left;
		if (y.left != nill) {
			y.left.parent = node;
		}
		y.parent = node.parent;
		if (node.parent == null) {
			this.root = y;
		} else if (node == node.parent.left) {
			node.parent.left = y;
		} else {
			node.parent.right = y;
		}
		y.left = node;
		node.parent = y;
	}

	private void rightRotate(Node node) {
		Node y = node.left;
		node.left = y.right;
		if (y.right != nill) {
			y.right.parent = node;
		}
		y.parent = node.parent;
		if (node.parent == null) {
			this.root = y;
		} else if (node == node.parent.right) {
			node.parent.right = y;
		} else {
			node.parent.left = y;
		}
		y.right = node;
		node.parent = y;
	}

	private void transplant(Node node1, Node node2) {
		if (node1.parent == null) {
			root = node2;
		} else if (node1 == node1.parent.left) {
			node1.parent.left = node2;
		} else {
			node1.parent.right = node2;
		}
		node2.parent = node1.parent;
	}

	private Node minimum(Node node) {
		while (node.left != nill) {
			node = node.left;
		}
		return node;
	}

	private void UtilitydeleteNode(Node node, int key) {
		Node z = nill;
		Node x, y;
		while (node != nill) {
			if (node.key == key) {
				z = node;
			}

			if (node.key <= key) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		if (z == nill) {
			//System.out.println("Couldn't find key in the tree");
			return;
		}

		y = z;
		int yOriginalColor = y.color;
		if (z.left == nill) {
			x = z.right;
			transplant(z, z.right);
		} else if (z.right == nill) {
			x = z.left;
			transplant(z, z.left);
		} else {
			y = minimum(z.right);
			yOriginalColor = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			} else {
				transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (yOriginalColor == Node.BLACK) {
			fixDelete(x);
		}
	}

	private void fixDelete(Node x) {
		Node s;
		while (x != root && x.color == Node.BLACK) {
			if (x == x.parent.left) {
				s = x.parent.right;
				if (s.color == Node.RED) {
					s.color = Node.BLACK;
					x.parent.color = Node.RED;
					leftRotate(x.parent);
					s = x.parent.right;
				}

				if (s.left.color == Node.BLACK && s.right.color == Node.BLACK) {
					s.color = Node.RED;
					x = x.parent;
				} else {
					if (s.right.color == Node.BLACK) {
						s.left.color = Node.BLACK;
						s.color = Node.RED;
						rightRotate(s);
						s = x.parent.right;
					}

					s.color = x.parent.color;
					x.parent.color = Node.BLACK;
					s.right.color = Node.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			} else {
				s = x.parent.left;
				if (s.color == Node.RED) {
					s.color = Node.BLACK;
					x.parent.color = Node.RED;
					rightRotate(x.parent);
					s = x.parent.left;
				}

				if (s.right.color == Node.BLACK && s.right.color == Node.BLACK) {
					s.color = Node.RED;
					x = x.parent;
				} else {
					if (s.left.color == Node.BLACK) {
						s.right.color = Node.BLACK;
						s.color = Node.RED;
						leftRotate(s);
						s = x.parent.left;
					}

					s.color = x.parent.color;
					x.parent.color = Node.BLACK;
					s.left.color = Node.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}
		x.color = Node.BLACK;
	}
      
	private void printHelper(Node root, String space, String Direction) {

		if (root != null) {
			System.out.print(space);
			if (Direction.equals("right"))
				System.out.print("(R)>>>>>>> ");
			else
				System.out.print("(L)>>>>>  ");
			space += "             ";

			String ColorOfNode;
			if (root.color == Node.RED)
				ColorOfNode = "red";
			else
				ColorOfNode = "black";
			System.out.println(root.key + "(" + ColorOfNode + ")");
			printHelper(root.left, space, "left");
			printHelper(root.right, space, "right");
		}

	}
	void Print() {

		
		printHelper(root, "         ", "right");
		System.out.println("-------------------------------------------------------------");
	}
	
	
	void clear ()
    {  
		
		clearHelper(root);
	   
		
    }
	void clearHelper (Node root)
    {
		delete(root.key);
         if(root==null)
       {
           return;
       }  
       if (root.left!=null)
       {
    	      clearHelper(root.left);
              root.left=null;
             
       }
       if(root.right!=null){
    	   clearHelper(root.right);
       root.right=null;
      
       }
      root=null;
   
    }






}
