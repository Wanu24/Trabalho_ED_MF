package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe Node que representa um nó numa estrutura de dados de lista encadeada.
 * @param <T> O tipo de dados que o nó irá conter.
 */
public class Node<T>{
    private Node<T> next; // O próximo nó na lista encadeada.
    private T element; // O elemento armazenado neste nó.

    /**
     * Construtor padrão da classe Node.
     * Inicializa o próximo nó e o elemento como nulos.
     */
    public Node() {
        next = null;
        element = null;
    }

    /**
     * Construtor da classe Node.
     * Inicializa o próximo nó como nulo e o elemento como o elemento fornecido.
     * @param elem O elemento a ser armazenado neste nó.
     */
    public Node(T elem) {
        next = null;
        element = elem;
    }

    /**
     * Retorna o elemento armazenado neste nó.
     * @return O elemento armazenado neste nó.
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Define o elemento armazenado neste nó.
     * @param elem O elemento a ser armazenado neste nó.
     */
    public void setElement (T elem) {
        this.element = elem;
    }

    /**
     * Retorna o próximo nó na lista encadeada.
     * @return O próximo nó na lista encadeada.
     */
    public Node<T> getNext(){
        return this.next;
    }

    /**
     * Define o próximo nó na lista encadeada.
     * @param next O próximo nó na lista encadeada.
     */
    public void setNext (Node<T> next) {
        this.next = next;
    }

    /**
     * Compara este nó com outro nó.
     * @param next O nó a ser comparado.
     * @return Verdadeiro se os elementos dos nós são iguais, falso caso contrário.
     */
    public boolean compareTo(Node next) {
        return this.element.equals(next.element);
    }
}