package ClassImplementation;
/**
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
public class Node<T>{
    private Node<T> next;
    private T element;

     public Node() {
        next = null;
        element = null;
    }

    public Node(T elem) {
        next = null;
        element = elem;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement (T elem) {
        this.element = elem;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public void setNext (Node<T> next) {
        this.next = next;
    }


    public boolean compareTo(Node next) {
        return this.element.equals(next.element);
    }
}
