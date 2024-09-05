package Sites;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        BinarySearchTree.java
 * Description  A definition for BinarySearchTree class with multitude of methods for 
 *              operations on trees     
 * @author	<i>Kirill Grichanichenko</i>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class BinarySearchTree 
{
    private BinarySearchTreeNode root;
    StringBuilder buffer = new StringBuilder("");
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * Constructor  BinarySearchTree - Default Constructor
   * Description  Construct a null Tree of data.     
   * @author	  <i>Kirill Grichanichenko</i>
   *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
   public BinarySearchTree()
   {
       root = null;
   }
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           InsertNode()
     * Description      Insert a new node in the binary search tree. If the root 
     *                  node is null, create the root node here. Otherwise, call
     *                  the insert method from the from the class TreeNode
     * @author          <i>Kirill Grichaichenko</i> 
     * @param           player Player
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void insertNode(Player player)
    {
        if (root == null)
            root = new BinarySearchTreeNode(player);
        else
            root.insert(player);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           remove()
     * Description      A method to remove a node with given name only. Calls
     *                  overloaded remove method with player name and the root
     *                  of the Tree
     * @author          <i>Kirill Grichanichenko</i>
     * @param           player Player
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void remove(Player player)
    {
	root = remove(player, root);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           remove()
     * Description      An overloaded recursive method to remove a node with 
     *                  given player and node. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           player Player
     * @param           node BinarySearchTreeNode
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode remove(Player player, BinarySearchTreeNode node)
    {
	if( node == null )
	    return node;                        // Item not found; do nothing
	if(player.compareTo(node.data) < 0)
	    node.left = remove(player, node.left);
	else if(player.compareTo(node.data) > 0)
	    node.right = remove(player, node.right);
	else if(node.left != null && node.right != null) // Two children
        {
	    node.data = findMin(node.right).data;
	    node.right = remove(node.data, node.right);
	}
	else    //Case 1 & case 2: remove leaf node & single child
	    node = (node.left != null) ? node.left : node.right;
	return node;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       getRoot()
     * Description  Getter method to return the root of the tree
     * @author      <i>Kirill Grichaichenko</i>
     * @return      root TreeNode
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public BinarySearchTreeNode getRoot()
    {
        return root;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           removeAll()
     * Description      A method to remove all nodes of the Tree
     * @author          <i>Kirill Grichanichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void removeAll()
    {
        root = null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findMin()
     * Description      A method to find and return the player with the minimum
     *                  value as specified by the Player compareTo method. 
     *                  Calls an overloaded method to start the search from the 
     *                  root node that is passed to it.
     * @author          <i>Kirill Grichaichenko</i>
     * @return          player Player--with minimum name 
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player findMin()
    {
        return findMin(root).data;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findMin()
     * Description      A recursive overloaded method to find the node with the 
     *                  player containing the minimum value as specified by 
     *                  the Player compareTo method. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           node BinarySearchTreeNode--recursive node to search for minimum
     * @return          node BinarySearchTreeNode--node containing the smallest item 
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode findMin(BinarySearchTreeNode node)
    {
	if(node == null)
	    return null;
	else if(node.left == null)
	    return node;
	return findMin(node.left);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findMax()
     * Description      A method to find and return the player with the maximum
     *                  value as specified by the Player compareTo method. 
     *                  Calls an overloaded method to start the search from the 
     *                  root node that is passed to it.
     * @author          <i>Kirill Grichaichenko</i>
     * @return          player Player--with minimum name
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player findMax()
    {
        return findMax(root).data;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           contains()
     * Description      A Boolean method to determine if a player is in the BinarySearchTree
     * @author          <i>Kirill Grichaichenko</i>
     * @return          player Player--with minimum name
     * @param           player Player
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean contains(Player player){
        if(nodeWith(player, root) == null){
            return false;
        }
        else{
            BinarySearchTreeNode foundPlayer = nodeWith(player, root);
            return (foundPlayer.data).equals(player);
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findMax()
     * Description      A recursive overloaded method to find the node with the 
     *                  player containing the maximum value as specified by 
     *                  the Player compareTo method. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           node BinarySearchTreeNode--recursive node to search for minimum
     * @return          node BinarySearchTreeNode--node containing the largest item. 
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode findMax(BinarySearchTreeNode node)
    {
	if( node != null )
        {
	    while( node.right != null )
		node = node.right;
        }	
	return node;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           elementAt()
     * Description      A method to return the player (data) given the node. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           node BinarySearchTreeNode
     * @return          player Player--the element field or null.
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Player elementAt(BinarySearchTreeNode node)
    {
	return (node == null ? null : node.data);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           saveTree()
     * Description      outputs the tree contents in pre-order. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           file String
     * @param           node BinarySearchTreeNode -- the node that roots the tree
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void saveTree(BinarySearchTreeNode node, String file){
        
        try{
            FileWriter filePointer = new FileWriter(file, false);
            PrintWriter writeFile = new PrintWriter(filePointer);
            if(node != null){
                String buf = printTree(node);
                writeFile.write(buf.substring(0, buf.length()-1));
            }
            writeFile.close();
        }
        catch(IOException exp){
            exp.printStackTrace();
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       setBuffer()
     * Description  Set buffer
     * @author      <i>Kirill Grichaichenko</i>
     * @param       buffer stringBuilder
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public void setBuffer(StringBuilder buffer){
        this.buffer = buffer;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       getBuffer()
     * Description  Returns buffer
     * @author      <i>Kirill Grichaichenko</i>
     * @return      buffer String
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public String getBuffer(){
        return buffer.toString();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       buildBuffer()
     * Description  Build a StringBuilder that contains all data in pre-order
     *              traversal.
     * @param       node BinarySearchTreeNodeTreeNode
     * @author      <i>Kirill Grichaichenko</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public void buildBuffer (BinarySearchTreeNode node){
        if (node != null){
            buffer.append(node.data.toString()+'\n');
            buildBuffer(node.left);
            buildBuffer(node.right);
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           nodeWith()
     * Description      A method to return the player (data) given the node. 
     * @author          <i>Kirill Grichaichenko</i>   
     * @param           data Player
     * @param           node BinarySearchTreeNode
     * @return          node BinarySearchTreeNode--the node with specified player or null 
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode nodeWith(Player data, BinarySearchTreeNode node)
    {
        if (node == null)
            return null;
        else
        {
            if(data.compareTo(node.data) == 0)
                return node;
            else
                if (data.compareTo(node.data) < 0)
                    return nodeWith(data, node.left);
                else
                    return nodeWith(data, node.right);                            
        }            
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           nodeWith()
     * Description      An overloaded method to return the node given the 
     *                  player name as a String
     * @author          <i>Kirill Grichaichenko</i>   
     * @param           playerName String
     * @param           node BinarySearchTreeNode
     * @return          node BinarySearchTreeNode--the node with specified player's name or null
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode nodeWith(String playerName, BinarySearchTreeNode node)
    {
        if (node == null)
            return null;
        else
        {
            if(playerName.compareTo(node.data.getName()) == 0)
                return node;
            else
                if (playerName.compareTo(node.data.getName()) < 0)
                    return nodeWith(playerName, node.left);
                else
                    return nodeWith(playerName, node.right);                            
        }            
    }
        
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           next()
     * Description      A method to return the next player according
     *                  to the sorting of the BinarySearchTree given a player. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           player Player
     * @return          player Player--the next player.
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player next(Player player)
    {
        BinarySearchTreeNode nextNode = nodeWith(player, root);
        if( nextNode.left != null && nextNode.right != null ) // Two children
            return findMin(nextNode.right).data;
        else
            return null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           previous()
     * Description      An unfinished method to return the next player according
     *                  to the sorting of the BinarySearchTree given a player. 
     * @author          <i>Kirill Grichaichenko</i>
     * @param           player Player
     * @return          player Player--the next player.
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player previous(Player player)
    {
        BinarySearchTreeNode nextNode = nodeWith(player, root);
        if( nextNode.left != null && nextNode.right != null ) // Two children
            return findMax(nextNode.left).data;
        else
            return null; //not so--needs work!! UNFINISHED
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           preorderTraversal()
     * Description      Display data of nodes in preorder: Node, Left, Right, Calls
     *                  recursive preorderHelper method to do the real display.
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void preorderTraversal()
    {
        preorderHelper( root );
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           preorderHelper()
     * Description      Display data of nodes id preorder: Node, Left, Right
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void preorderHelper( BinarySearchTreeNode node )
    {
        if ( node == null )
            return;
        System.out.print( node.data + " " );
        preorderHelper( node.left );
        preorderHelper( node.right );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           inorderTraversal()
     * Description      Display data of nodes in preorder: Left, Node, Right. Calls
     *                  recursive inorderHelper method to do the real display
     * @author          <i>Kirill Grichaichenko</i>  
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void inorderTraversal()
    {
        inorderHelper( root );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           inorderHelper()
     * Description      Display data of nodes in preoredr: left, Node, Right.
     * @author          <i>Kirill Grichaichenko</i> 
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void inorderHelper(BinarySearchTreeNode node )
    {
        if ( node == null )
            return;
        inorderHelper( node.left );
        System.out.print( node.data + " " );
        inorderHelper( node.right );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           postorderTraversal()
     * Description      Display data of nodes in preorder: Left, Right, Node. Calls
     *                  recursive postorderHelper method to do the real display
     * @author          <i>Kirill Grichaichenko</i>  
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void postorderTraversal()
    {
        postorderHelper( root );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           postorderHelper()
     * Description      Display data of nodes in preorder: left, Right, Node.
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void postorderHelper( BinarySearchTreeNode node )
    {
        if ( node == null )
            return;
        postorderHelper( node.left );
        postorderHelper( node.right );
        System.out.print( node.data + " " );
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           toString()
     * Description      Outputs the tree contents in sorted order as a string.
     *                  It calls the printTree to print the tree in-order.
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString( )
    {
	if( isEmpty() )
	    return("");
	else
	    return(printTree( root ));
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           printTree()
     * Description      Outputs the tree contents in sorted order via inorder.
     * @param           node BinarySearchTreeNode
     * @return          tree String
     * @author          <i>Kirill Grichaichenko</i>  
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String printTree(BinarySearchTreeNode node)
    {
	
	if(node != null)
        {
	    printTree( node.left);
	    buffer.append(node.data + ",");
	    printTree( node.right);
	}
	return buffer.toString();
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           isEmpty()
     * Description      Test if the tree is logically empty.
     * @return          true if empty, false otherwise
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isEmpty()
    {
	return root == null;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           size()
     * Description      Find number of nodes in a tree. Calls sizeHelper with 
     *                  the root of the tree passed to it
     * @return          number of nodes in a tree
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int size()
    {	
        return sizeHelper(root);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           sizeHelper()
     * Description      Recursive method to find number of nodes in a tree.
     * @param           node BinarySearchTreeNode
     * @return          number of nodes in the tree
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int sizeHelper (BinarySearchTreeNode node)
    {
	if (node == null)
	    return 0;
	else
	    return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }  

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           countLeaves()
     * Description      Find number of leaves in a tree. Calls countLeavesHelper  
     *                  with the root of the tree passed to it
     * @return          number of leaves in a tree to which node points
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int countLeaves() 
    {
        return countLeavesHelper(root);
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           countLeavesHelper()
     * Description      Recursive method to find number of leaves in a tree.
     * @param           node BinarySearchTreeNode
     * @return          number of leaves in the tree
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int countLeavesHelper(BinarySearchTreeNode node)
    {
        if (node == null)
           return 0;
        else if (node.left == null && node.right == null)
            return 1;  // Node is a leaf.
        else
            return countLeavesHelper(node.left) + countLeavesHelper(node.right);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           height()
     * Description      Find height of a tree. Calls heighthHelper with the root 
     *                  of the tree passed to it.The height of a node is the 
     *                  number of edges on the longest path from the  node to a 
     *                  leaf. A leaf node will have a height of 0
     * @return          height of a node int
     * @author          <i>Kirill Grichaichenko</i>  
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int height() 
    {
        return heighthHelper(root);        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           heighthHelper()
     * Description      Recursive method to find the height of a node.
     * @param           node BinarySearchTreeNode
     * @return          height of a node int
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int heighthHelper(BinarySearchTreeNode node)
    {
        if(node == null)
            return 0;
        else
        {
            return 1 + Math.max(heighthHelper(node.left),
                    heighthHelper(node.right));
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findParent()
     * Description      Find height of a tree. Calls overloaded findParent with
     *                  player, root and parent (null)
     * @param           player Player
     * @return          parent node of player BinarySearchTreeNode
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findParent(Player player) 
    {
        return findParent(player, root, null);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findParent()
     * Description      Recursive method to find the parent of an player.
     * @param           player Player
     * @param           node BinarySearchTreeNode
     * @param           parent BinarySearchTreeNode
     * @return          parent of a node or null--BinarySearchTreeNode
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findParent(Player player, BinarySearchTreeNode node, BinarySearchTreeNode parent) 
    {
        if (node == null) 
        {
            return null;
        } 
        else if (node.data != player) 
        {
            parent = findParent(player, node.left, node);
            if (parent == null) 
            {
                parent = findParent(player, node.right, node);
            }
        }
        return parent;
    }
        
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findSuccessor()
     * Description      Recursive method to find the successor of a player 
     *                  given the data=Player
     * @param           data Player
     * @return          successor of an player or null--TreeNode
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findSuccessor(Player data) 
    {
        return findSuccessor(nodeWith(data,root));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findSuccessor()
     * Description      An overloaded recursive method to find the successor 
     *                  of a player given a node
     * @param           node BinarySearchTreeNode
     * @return          successor of an player or null--BinarySearchTreeNode
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findSuccessor(BinarySearchTreeNode node)
    {
	if (node == null)
		return null;
	
	if (node.right != null)
		return findMin(node.right);
	
	BinarySearchTreeNode y = findParent(node.data);
	BinarySearchTreeNode x = node;
	while (y != null && x == y.right)
	{
		x = y;
		y = findParent(y.data);
	}
	// Intuition: as we traverse left up the tree we traverse smaller values
	// The first node on the right is the next larger value
	return y;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findPredecessor()
     * Description      Recursive method to find the predecessor of a player 
     *                  given the data=Player
     * @param           data Player
     * @return          successor of an player or null--BinarySearchTreeNode
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findPredecessor(Player data) 
    {
        return findPredecessor(nodeWith(data,root));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method           findPredecessor()
     * Description      An overloaded recursive method to find the predecessor 
     *                  of an player given a node
     * @param           node BinarySearchTreeNode
     * @return          predecessor of an player or null--BinarySearchTreeNode
     * @author          <i>Kirill Grichaichenko</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findPredecessor(BinarySearchTreeNode node)
    {
	if (node == null)
            return null;
	
	if (node.left != null)
            return findMax(node.left);
			
	BinarySearchTreeNode parent = findParent(node.data);

	BinarySearchTreeNode y = parent;
	BinarySearchTreeNode x = node;
	while (y != null && x == y.left)
	{
            x = y;
            y = findParent(y.data);
	}	
	return y;
    }  
}
