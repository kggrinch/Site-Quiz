package Sites;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class        BinarySearchTreeNode.java
 * Description  A self-referential class representing a binary tree node      
 * @author	<i>Kirill Grichanichenko</i>
 * date         10/06/2023
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class BinarySearchTreeNode {
    BinarySearchTreeNode left;
    Player data;
    BinarySearchTreeNode right;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor     BinarySearchTreeNode()-- constructor
     * Description     Initialize data to player and make this a leaf node.
     * @param          player Player
     * @author         <i>Kirill Grichanichenko</i>
     * date            10/06/2023
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode(Player player)
    {
        data = player;
        left = right = null; // This node has no children.
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       Insert()
     * Description  Recursive method to insert a TreeNode into a Tree that
     *              contains nodes
     * @param       player Player
     * @author      <i>Kirill Grichanichenko</i>
     * date         10/06/2023
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public synchronized void insert (Player player)
    {
        if (player.compareTo(data) < 0)
        {
            if (left == null)
                left = new BinarySearchTreeNode(player);
            else
                left.insert(player);
        }
        else
            if (player.compareTo(data) >= 0)
            {
                if (right == null)
                    right = new BinarySearchTreeNode(player);
                else
                    right.insert(player);
            }
    } 
}
