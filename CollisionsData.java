package project5;

/**
 * This class implements AVL tree structure to store Collision Objects
 *
 * @author Summer Li
 */
public class CollisionsData extends BST_Recursive<Collision> {

    private boolean found;
    private int balance;
    private int height;
    private int PersonsInjured = 0,PedestriansInjured= 0,CyclistInjured= 0,MotoristsInjured= 0,PersonsKilled= 0,PedestriansKilled= 0,CyclistKilled= 0,MotoristsKilled= 0;


    @Override
    public void add(Collision item) {
        if (item == null)
            return;
        root = add (root, item);
    }

    /**
     * Add the given data item to the tree. If item is null, the tree does not
     * change. If item already exists, the tree does not change. 
     * 
     * @param <Collision> iterm, new element to be added to the tree
     */

    @Override
    public Node<Collision> add(Node<Collision> node, Collision item) {
        if (node == null) {
            return (new Node(item));
        }
        if (node.data.compareTo(item)>0) {
            node.left = add(node.left, item);
        } else {
            node.right = add(node.right, item);
        }
        // Get the height of the AVL tree
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balance = getBalance(node);
        // If the updated tree becomes unbalanced, perform rotations.
        // Left Left Case
        if (balance > 1 && node.left.data.compareTo(item) < 0) {
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && node.right.data.compareTo(item) > 0) {
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && node.left.data.compareTo(item)>0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left Case
        if (balance < -1 && node.right.data.compareTo(item) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * Remove the item from the tree. If item is null the tree remains unchanged. If
     * item is not found in the tree, the tree remains unchanged.
     * 
     * @param target the item to be removed from this tree 
     */
    public boolean remove(Collision target)
    {
        root = recRemove(target, root);
        return found;
    }


    /*
     * Actual recursive implementation of remove method: find the node to remove.  
     * 
     * @param target the item to be removed from this tree 
     */
    private Node<Collision> recRemove(Collision target, Node<Collision> node)
    {
        if (node == null)
            found = false;
        else if (target.compareTo(node.data) < 0)
            node.left = recRemove(target, node.left);
        else if (target.compareTo(node.data) > 0)
            node.right = recRemove(target, node.right );
        else {
            node = removeNode(node);
            found = true;
        }
        // Get the height of the AVL tree
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        balance = getBalance(node);
        // If the updated tree becomes unbalanced, perform rotations.
        // Left Left Case
        if (balance > 1 && node.left.data.compareTo(item) < 0) {
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && node.right.data.compareTo(item) > 0) {
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && node.left.data.compareTo(item) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left Case
        if (balance < -1 && node.right.data.compareTo(item) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /*
     * Actual recursive implementation of remove method: perform the removal.  
     * 
     * @param target the item to be removed from this tree 
     * @return a reference to the node itself, or to the modified subtree 
     */
    private Node<Collision> removeNode(Node<Collision> node)
    {
        Collision data;
        if (node.left == null)
            return node.right ;
        else if (node.right  == null)
            return node.left;
        else {
            data = getPredecessor(node.left);
            node.data = data;
            node.left = recRemove(data, node.left);
            return node;
        }
    }
    
    /*
     * Returns the information held in the rightmost node of subtree  
     * 
     * @param subtree root of the subtree within which to search for the rightmost node 
     * @return returns data stored in the rightmost node of subtree  
     */
    private Collision getPredecessor(Node<Collision> subtree)
    {
        if (subtree==null) throw new NullPointerException("getPredecessor called with an empty subtree");
        Node<Collision> temp = subtree;
        while (temp.right  != null)
            temp = temp.right ;
        return temp.data;
    }

    
     /**
     * Get the Balance factor of the tree.
     * @return int 
     */
    private int getBalance( Node n ){
        if(n == null)
            return 0;
        if(n.right == null)
            balance = -n.getHeight();
            return balance;
        if(n.left == null)
            balance = n.getHeight();
            return balance;
        balance = n.right.getHeight() - node.left.getHeight();
        return balance;
    }
    /**
     * Get the height of the tree.
     * @return int 
     */
    private int getHeight(Node n) {
        if (n != null) {
            updateHeight(n);
            return n.height;
        }
        return 0;
    }

    /**
     * Perform the right rotation for the BST to be an AVL
     * @param node
     * @return node the left child of the passed node
     */
    private Node rightRotate(Node node) {
        Node node1 = node.left;
        Node tmpNode = node1.right;
        node1.right = node;
        node.left = tmpNode;
        node1.height = Math.max(getHeight(node1.left), getHeight(node1.right)) + 1;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node1;
    }

    /**
     * Perform the left rotation for the BST to be an AVL
     * @param node
     * @return node the right child of the passed node 
     */
    private Node leftRotate(Node node) {
        Node node1 = node.right;
        Node tmpNode = node1.left;
        node1.left = node;
        node.right = tmpNode;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        node1.height = Math.max(getHeight(node1.left), getHeight(node1.right)) + 1;
        return node1;
    }

    /**
     * Produces tree like string representation of this BST.
     * @return string containing tree-like representation of this BST.
     */
    public String toStringTreeFormat() {

        StringBuilder s = new StringBuilder();

        preOrderPrint(root, 0, s);
        return s.toString();
    }

    /**
    * Get the report of a Collision item stored in CollisionsData Tree.
    * @return string containing the information about the numbers of fatalities and injuries for a given zip code and range.
    */

    public String getReport (String zip, Date dateBegin, Date dateEnd ){
               
        Collision nodeFound = findData(root, zip, dateBegin, dateEnd);

        String result = "";

        result += "Motor Vehicle Collisions for zipcode "+zip+" ("+dateBegin.toString()+" - "+dateEnd.toString()+")";
        result += "====================================================================";
        result += "Total number of collisions: "+(nodeFound.PersonsInjured+nodeFound.PedestriansInjured+nodeFound.CyclistInjured+nodeFound.MotoristsInjured);
        result += "Number of fatalities:"+(nodeFound.PersonsKilled+nodeFound.PedestriansKilled+nodeFound.CyclistKilled+nodeFound.MotoristsKilled);
        result += "pedestrians: "+nodeFound.PedestriansKilled;
        result += "cyclists:"+nodeFound.CyclistKilled;
        result += "motorists:"+nodeFound.MotoristsKilled;
        return result;
    }

}


    public Node<Collision> findData(Node<Collision> node, String zip, Date dateBegin, Date dateEnd){
        if(node.zip.CompareTo(zip)==0){
            if(node.date.CompareTo(dateBegin)<0){
                return getReport(node.right, zip, dateBegin, dateEnd);
            }
            if(node.date.CompareTo(dateEnd)>0){
                return getReport(node.left, zip, dateBegin, dateEnd);
            }
            return node;
        }
        if(node.zip.CompareTo(zip)>0){
            return getReport(node.left, zip, dateBegin, dateEnd);
        }
        if(node.zip.CompareTo(zip)<0){
            return getReport(node.right, zip, dateBegin, dateEnd);
        }
    }



