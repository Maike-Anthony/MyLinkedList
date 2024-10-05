import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> extends AbstractList<T> {
    /** The Node that is currently the beginning of the linked list. */
    private DoublyLinkedNode firstNode;
    /** The Node that is currently the end of the linked list. */
    private DoublyLinkedNode lastNode;
    /** The current size of the list. */
    private int size;

    public MyLinkedList() {
        this.firstNode = null;
        this.lastNode = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size -1) + ".");
        }
        return getNthNode(index).item;
   }

    private DoublyLinkedNode getNthNode(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size -1) + ".");
        }
        DoublyLinkedNode tempNode = this.firstNode;
        for (int count = 0; count < index; count++) {
            tempNode = tempNode.next;
        }
        return tempNode;
   }

   public void add(int index, T item) {
    if (item == null) {
        throw new NullPointerException("Null is not a valid item to be inserted into the list.");
    } else if (index < 0 || index > this.size) {
        throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size) + ".");
    } else if (this.size == 0) {
        DoublyLinkedNode newNode = new DoublyLinkedNode(item, null, null);
        this.firstNode = newNode;
        this.lastNode = newNode;
    } else if (index == 0) {
        DoublyLinkedNode newNode = new DoublyLinkedNode(item, null, firstNode);
        this.firstNode.previous = newNode;
        this.firstNode = newNode;
    } else if (index == this.size) {
        DoublyLinkedNode newNode = new DoublyLinkedNode(item, this.lastNode, null);
        this.lastNode.next = newNode;
        this.lastNode = newNode;
    } else {
        DoublyLinkedNode prevNode = getNthNode(index - 1);
        DoublyLinkedNode sucNode = getNthNode(index);
        DoublyLinkedNode newNode = new DoublyLinkedNode(item, this.lastNode, null);
        prevNode.next = newNode;
        sucNode.previous = newNode;
        newNode.previous = prevNode;
        newNode.next = sucNode;
    }
    this.size++;
   }

   public boolean add(T item) {
    add(this.size(), item);
    return true;
   }

   public T set(int index, T item) {
    if (item == null) {
        throw new NullPointerException("Null is not a valid item to be inserted into the list.");
    } else if (index < 0 || index > this.size - 1) {
        throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size - 1) + ".");
    } else {
        T returnee = get(index);
        getNthNode(index).item = item;
        return returnee;
    }
   }

   public T remove(int index) {
    T returnee = null;
    if (index < 0 || index > this.size - 1) {
        throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size - 1) + ".");
    } else if (this.size == 1) {
        returnee = this.firstNode.item;
        this.firstNode = null;
        this.lastNode = null;
    } else if (index == 0) {
        returnee = this.firstNode.item;
        getNthNode(1).previous = null;
        this.firstNode = firstNode.next;
    } else if (index == this.size - 1) {
        returnee = this.lastNode.item;
        getNthNode(this.size - 2).next = null;
        this.lastNode = lastNode.previous;
    }
    this.size--;
    return returnee;
   }

   public boolean isEmpty() {
    if (this.size == 0) {
        return true;
    } else {
        return false;
    }
   }

   public void clear() {
    this.firstNode = null;
    this.lastNode = null;
    this.size = 0;
   }

   public ListIterator<T> listIterator() {
    return new MyLinkedListIterator();
   }

   public Iterator<T> iterator() {
    return listIterator();
   }
	
	private class DoublyLinkedNode {
        /** The item stored in this Node. */
        private T item;

        /** The Node that preceeds this Node in the doubly linked list. Note: previous is null if this Node is the beginning of the doubly linked list. */
        private DoublyLinkedNode previous;
        /** The Node that follows this Node in the doubly linked list. Note: next is null if this Node is the end of the doubly linked list. */
        private DoublyLinkedNode next;

        DoublyLinkedNode (T item, DoublyLinkedNode previous, DoublyLinkedNode next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
	}

    public class MyLinkedListIterator implements ListIterator<T> {
        DoublyLinkedNode prevNode;
        DoublyLinkedNode nextNode;

        public MyLinkedListIterator() {
            prevNode = null;
            nextNode = firstNode;
        }

        public boolean hasNext() {
            if (this.nextNode != null) {
                return true;
            } else {
                return false;
            }
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element.");
            } else {
                T returnee = nextNode.item;
                this.prevNode = this.nextNode;
                this.nextNode = this.nextNode.next;
                return returnee;
            }
        }

        public boolean hasPrevious() {
            if (prevNode != null) {
                return true;
            } else {
                return false;
            }
        }

        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("There is no previous element.");
            } else {
                T returnee = prevNode.item;
                this.nextNode = this.prevNode;
                this.prevNode = this.prevNode.previous;
                return returnee;
            }
        }

        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        public void add(T item) {
            throw new UnsupportedOperationException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(T item) {
            throw new UnsupportedOperationException();
        }

    }
}
