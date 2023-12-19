
public class SortedLinkedList implements SortedList {

    Node first = new Node(null, "", null);
    Node last = new Node(null, "", null);
    OrderStatus order = OrderStatus.ASCENDING;

    enum OrderStatus {
        ASCENDING,
        DESCENDING
    }

    @Override
    public int size() {
        int i = 0;
        Node currentNode = first;
        try {
            while (!currentNode.getString().isEmpty()) {
                i++;
                currentNode = currentNode.getNext();
            }
        } catch (NullPointerException e) {
            System.out.println("End of list reached | " + i);
        }
        return i;
    }

    @Override
    public void add(String string) {
        this.add(new Node(string));
    }

    @Override
    public void add(Node node) {
        Node currentNode;

        //Check for duplicates
        if (this.isPresent(node.getString())) {
            System.out.println("A node with string '" + node.getString() + "' already exists - nothing added");
            return;
        }

        if (this.size() == 0) {
            //There is no first item so new node becomes the first (for both ascending and descending)
            System.out.println("No first node so new node (" + node.getString() + ") = first and last");
            first = node;
            last = node;
        } else {
            //Not the first node
            switch (order) {
                case OrderStatus.ASCENDING:
                    currentNode = first;

                    if (node.getString().compareToIgnoreCase(currentNode.getString()) < 1) {
                        currentNode.setPrev(node);
                        node.setNext(currentNode);
                        first = node;
                        return;
                    }

                    for (int i = 0; i < this.size(); i++) {
                        try {
                            if (node.getString().compareToIgnoreCase(currentNode.getNext().getString()) >= 1) {
                                //CompareTo returns a positive integer if node string alphabetically succeeds next-node string
                                currentNode = currentNode.getNext();
                            } else if (node.getString().compareToIgnoreCase(currentNode.getNext().getString()) < 1) {
                                //Must be lesser alphabetically so insert here
                                node.setNext(currentNode.getNext());
                                node.setPrev(currentNode);
                                currentNode.setNext(node);
                                node.getNext().setPrev(node);
                                return;
                            }

                        } catch (NullPointerException e) {
                            //Must be at the end of the list
                            currentNode.setNext(node);
                            node.setPrev(currentNode);
                            last = node;
                            return;
                        }
                    }

                    break;
                case OrderStatus.DESCENDING:
                    currentNode = last;

                    if (node.getString().compareToIgnoreCase(currentNode.getString()) < 1) {
                        System.out.println(node.getString() + " is after " + currentNode.getString() + " (last) so added to end");
                        currentNode.setNext(node);
                        node.setPrev(currentNode);
                        last = node;
                        return;
                    }

                    for (int i = 0; i < this.size(); i++) {
                        try {
                            if (node.getString().compareToIgnoreCase(currentNode.getPrev().getString()) >= 1) {
                                System.out.println(node.getString() + " is after " + currentNode.getPrev().getString() + " so carry on");
                                currentNode = currentNode.getPrev();
                            } else if (node.getString().compareToIgnoreCase(currentNode.getPrev().getString()) < 1) {
                                System.out.println(node.getString() + " is before " + currentNode.getPrev().getString() + " so inserted here");
                                currentNode.getPrev().setNext(node);
                                node.setPrev(currentNode.getPrev());
                                node.setNext(currentNode);
                                currentNode.setPrev(node);
                                return;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("reached end of list, " + node.getString() + " added to start");
                            currentNode.setPrev(node);
                            node.setNext(currentNode);
                            first = node;
                            return;
                        }

                    }
            }

        }
    }

    @Override
    public Node getFirst() {
        return first;
    }

    @Override
    public Node getLast() {
        return last;
    }

    @Override
    public Node get(int index) {
        //Check for index out of range exception
        if (index >= this.size()) {
            return null;
        } else {
            if (this.size() == 0) {
                System.out.println("List is empty!");
                return null;
            } else {
                Node currentNode = first;
                for (int i = 0; i < this.size(); i++) {
                    try {
                        if (i == index) {
                            return currentNode;
                        } else {
                            currentNode = currentNode.getNext();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("End of list");
                        return null;
                    }
                }
            }
            return null;
        }
    }

    @Override
    public boolean isPresent(String string) {
        Node currentNode = first;
        for (int i = 0; i < this.size(); i++) {
            if (currentNode.getString().equals(string)) {
                return true;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }

    @Override
    public boolean removeFirst() {
        try {
            first = first.getNext();
            first.setPrev(null);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean removeLast() {
        try {
            last = last.getPrev();
            last.setNext(null);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean remove(int index) {
        if (index > this.size()) {
            System.out.println("Index supplied is larger than list!");
            return false;
        } else if (index < 0) {
            System.out.println("Invalid index");
            return false;
        } else if (index == this.size() - 1) {
            this.removeLast();
        } else if (index == 0) {
            this.removeFirst();
        } else {
            Node currentNode = first;
            if (this.size() == 0) {
                System.out.println("List is empty!");
                return false;
            } else {
                for (int i = 0; i < this.size(); i++) {
                    try {
                        if (i == index) {
                            //Remove
                            currentNode.getNext().setPrev(currentNode.getPrev());
                            currentNode.getPrev().setNext(currentNode.getNext());
                            return true;
                        } else {
                            currentNode = currentNode.getNext();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("End of list");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(String string) {
        Node currentNode = first;

        for (int i = 0; i < this.size(); i++) {
            try {
                if (currentNode.getString().equalsIgnoreCase(string)) {
                    //Remove
                    if (i == 0) {
                        //Must be the first element
                        this.removeFirst();
                        return true;
                    } else if (i == this.size() - 1) {
                        //Must be the last element
                        this.removeLast();
                        return true;
                    }
                    currentNode.getNext().setPrev(currentNode.getPrev());
                    currentNode.getPrev().setNext(currentNode.getNext());
                    return true;
                } else {
                    currentNode = currentNode.getNext();
                }
            } catch (NullPointerException e) {
                System.out.println("End of list");
                return false;
            }
        }
        return false;
    }

    public void removeAll(int size) {
        for (int i = 0; i < size; i++) {
            if (this.size() == 1) {
                this.removeFirst();
            } else {
                this.removeLast();
            }
        }
    }

    public boolean bubble(Node node1, Node node2) {
        //If they are already in order, leave them and move on to the next pair
        if (this.order == OrderStatus.ASCENDING) {
            if (node1.getString().compareToIgnoreCase(node2.getString()) > 0) {
                //Must be in the wrong order - swap!
                if (node1 == first) {
                    //At the start of the list
                    node2.getNext().setPrev(node1);
                    node1.setNext(node2.getNext());
                    node1.setPrev(node2);
                    node2.setNext(node1);
                    node2.setPrev(null);
                    first = node2;
                    return true;
                } else if (node2 == last) {
                    //At the end of the list
                    node1.getPrev().setNext(node2);
                    node2.setPrev(node1.getPrev());
                    node2.setNext(node1);
                    node1.setPrev(node2);
                    node1.setNext(null);
                    last = node1;
                    return true;
                } else {
                    //In the middle somewhere
                    node1.getPrev().setNext(node2);
                    node2.getNext().setPrev(node1);
                    node1.setNext(node2.getNext());
                    node2.setNext(node1);
                    node2.setPrev(node1.getPrev());
                    node1.setPrev(node2);
                    return true;
                }
            }
        }
        else if (this.order == OrderStatus.DESCENDING) {
            if (node2.getString().compareToIgnoreCase(node1.getString()) > 0) {
                //Must be in the wrong order - swap!
                if (node1 == first) {
                    //At the start of the list
                    node2.getNext().setPrev(node1);
                    node1.setNext(node2.getNext());
                    node1.setPrev(node2);
                    node2.setNext(node1);
                    node2.setPrev(null);
                    first = node2;
                    return true;
                } else if (node2 == last) {
                    //At the end of the list
                    node1.getPrev().setNext(node2);
                    node2.setPrev(node1.getPrev());
                    node2.setNext(node1);
                    node1.setPrev(node2);
                    node1.setNext(null);
                    last = node1;
                    return true;
                } else {
                    //In the middle somewhere
                    node1.getPrev().setNext(node2);
                    node2.getNext().setPrev(node1);
                    node1.setNext(node2.getNext());
                    node2.setNext(node1);
                    node2.setPrev(node1.getPrev());
                    node1.setPrev(node2);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void orderAscending() {
        this.order = OrderStatus.ASCENDING;
        this.orderList();
    }

    @Override
    public void orderDescending() {
        this.order = OrderStatus.DESCENDING;
        this.orderList();
    }

    public void orderList() {
        //Bubble sort the list
        int size = this.size();
        boolean noSwaps = true;
        Node node1;
        Node node2;
        do {
            noSwaps = true;
            for (int i = 0; i < size - 1; i++) {
                node1 = this.get(i);
                node2 = this.get(i + 1);
                if (bubble(node1, node2)) {
                    //A swap has been done
                    noSwaps = false;
                }
            }
        } while (!noSwaps);
    }

    @Override
    public void print() {
        if (this.size() == 0) {
            System.out.println("List is empty!");
        }

        Node currentNode = first;

        for (int i = 0; i < this.size(); i++) {
            try {
                System.out.println("String: " + currentNode.getString());
                currentNode = currentNode.getNext();
            } catch (NullPointerException e) {
                System.out.println("End of list so stopped printing");
            }
        }
    }
}


/**
 * SortedList interface for use with the CM10228: Principles of Programming 2 coursework.
 * <p>
 * This should not be modified by the student.
 *
 * @author Christopher Clarke
 * @version 1.0
 */
interface SortedList {

    /**
     * Returns the number of Nodes in the linked list.
     *
     * @return the number of Nodes in the linked list
     */
    public int size();

    /**
     * Adds a Node with the specified string to the linked list in
     * the appropriate position given the specified alphabetical order
     * (i.e., ascending/descending).
     *
     * @param string a String to be added to the linked list
     */
    public void add(String string);

    /**
     * Adds a Node to the linked list in the appropriate position
     * given the specified alphabetical order (i.e., ascending/descending).
     *
     * @param node a Node to be added to the linked list
     */
    public void add(Node node);

    /**
     * Returns the first Node of the linked list given the specified
     * alphabetical order (i.e., ascending/descending).
     *
     * @return the first Node in the linked list
     */
    public Node getFirst();

    /**
     * Returns the last Node of the linked list given the specified
     * alphabetical order (i.e., ascending/descending).
     *
     * @return the last Node in the linked list
     */
    public Node getLast();

    /**
     * Returns the Node at the specified index assuming indices start
     * at 0 and end with size-1 given the specified alphabetical order
     * (i.e., ascending/descending).
     *
     * @param index the index of the Node in the linked list to be retrieved
     * @return the Node in the linked list at the specified index
     */
    public Node get(int index);

    /**
     * Checks to see if the list contains a Node with the specified
     * string.
     *
     * @param string the String to be searched for in the linked list
     * @return True if the string is present or false if not
     */
    public boolean isPresent(String string);

    /**
     * Removes the first Node from the list given the specified
     * alphabetical order (i.e., ascending/descending).
     *
     * @return Returns true if successful or false if unsuccessful
     */
    public boolean removeFirst();

    /**
     * Removes the last Node from the list given the specified
     * alphabetical order (i.e., ascending/descending).
     *
     * @return Returns true if successful or false if unsuccessful
     */
    public boolean removeLast();

    /**
     * Removes the Node at the specified index from the list assuming indices
     * start at 0 and end with size-1 given the specified alphabetical order
     * (i.e., ascending/descending)
     *
     * @param index the index of the Node in the linked list to be removed
     * @return Returns true if successful or false if unsuccessful
     */
    public boolean remove(int index);

    /**
     * Removes the Node from the list that contains the specified string.
     *
     * @param string the string to be removed from the linked list
     * @return Returns true if successful or false if unsuccessful
     */
    public boolean remove(String string);

    /**
     * Orders the linked list in ascending alphabetical order.
     */
    public void orderAscending();

    /**
     * Orders the linked list in descending alphabetical order.
     */
    public void orderDescending();

    /**
     * Prints the contents of the linked list in the specified alphabetical order
     * (i.e., ascending/descending) to System.out with each node's string on
     * a new line.
     */
    public void print();
}


/**
 * Node class for use with the CM10228: Principles of Programming 2 coursework.
 * <p>
 * This should not be modified by the student.
 *
 * @author Christopher Clarke
 * @version 1.0
 */
class Node {
    private String name;
    private Node prev;
    private Node next;

    public Node(String name) {
        this.prev = null;
        this.name = name;
        this.next = null;
    }

    public Node(String name, Node next) {
        this.prev = null;
        this.name = name;
        this.next = next;
    }

    public Node(Node prev, String name) {
        this.prev = prev;
        this.name = name;
        this.next = null;
    }

    public Node(Node prev, String name, Node next) {
        this.prev = prev;
        this.name = name;
        this.next = next;
    }

    public void setString(String name) {
        this.name = name;
    }

    public String getString() {
        return this.name;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return this.next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getPrev() {
        return this.prev;
    }
}
