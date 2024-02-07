package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe DoubleNode que representa um nó em uma lista duplamente encadeada.
 * Cada DoubleNode tem um elemento genérico, um nó próximo e um nó anterior.
 *
 * @param <T> O tipo de elemento que este nó armazena.
 */
public class DoubleNode<T> {
    /**
     * O elemento armazenado neste nó.
     */
    private T element;

    /**
     * O próximo nó após este nó na lista.
     */
    private DoubleNode<T> next;

    /**
     * O nó anterior a este nó na lista.
     */
    private DoubleNode<T> previous;

    /**
     * Construtor para a classe DoubleNode.
     * Inicializa o elemento do nó, o próximo nó e o nó anterior como nulos.
     */
    public DoubleNode() {
        this.next = null;
        this.element = null;
        this.previous = null;
    }

    /**
     * Construtor para a classe DoubleNode.
     * Inicializa o elemento do nó com o objeto fornecido e o próximo nó e o nó anterior como nulos.
     *
     * @param element O objeto a ser armazenado neste nó.
     */
    public DoubleNode(T element) {
        this.next = null;
        this.element = element;
        this.previous = null;
    }

    /**
     * Construtor para a classe DoubleNode.
     * Inicializa o elemento do nó com o objeto fornecido, o próximo nó e o nó anterior com os nós fornecidos.
     *
     * @param data O objeto a ser armazenado neste nó.
     * @param previous O nó anterior a este nó.
     * @param next O próximo nó após este nó.
     */
    public DoubleNode(T data, DoubleNode<T> previous, DoubleNode<T> next) {
        this.element = data;
        this.next = next;
        this.previous = previous;
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
     * @param elem O novo elemento a ser armazenado.
     */
    public void setElement(T elem) {
        this.element = elem;
    }

    /**
     * Retorna o próximo nó após este nó.
     * @return O próximo nó após este nó.
     */
    public DoubleNode<T> getNext() {
        return this.next;
    }

    /**
     * Define o próximo nó após este nó.
     * @param dnode O novo próximo nó.
     */
    public void setNext(DoubleNode<T> dnode) {
        this.next = dnode;
    }

    /**
     * Retorna o nó anterior a este nó.
     * @return O nó anterior a este nó.
     */
    public DoubleNode<T> getPrevious() {
        return this.previous;
    }

    /**
     * Define o nó anterior a este nó.
     * @param dnode O novo nó anterior.
     */
    public void setPrevious(DoubleNode<T> dnode) {
        this.previous = dnode;
    }
}


