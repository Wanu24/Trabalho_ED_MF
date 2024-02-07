package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe LinkedList que representa uma lista encadeada.
 * @param <T> O tipo de dados que a lista irá conter.
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Construtor padrão da classe LinkedList.
     * Inicializa o início e o fim da lista como nulos e o tamanho como 0.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adiciona um elemento ao fim da lista.
     * @param data O elemento a ser adicionado ao fim da lista.
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        ++this.size;
    }

    /**
     * Adiciona um elemento em um índice específico da lista.
     * @param index O índice onde o elemento será adicionado.
     * @param data O elemento a ser adicionado.
     */
    public void add(int index, T data) {
        if (index >= 0 && index <= this.size) {
            Node<T> newNode = new Node<>(data);
            if (index == 0) {
                newNode.setNext(this.head);
                this.head = newNode;
            } else if (index == this.size) {
                this.tail.setNext(newNode);
                this.tail = newNode;
            } else {
                Node<T> prev = this.head;
                for(int i = 0; i < index - 1; ++i) {
                    prev = prev.getNext();
                }
                newNode.setNext(prev.getNext());
                prev.setNext(newNode);
            }
            ++this.size;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Retorna o elemento em um índice específico da lista.
     * @param index O índice do elemento a ser retornado.
     * @return O elemento no índice especificado.
     */
    public T get(int index) {
        if (index >= 0 && index < this.size) {
            Node<T> current = this.head;
            for(int i = 0; i < index; ++i) {
                current = current.getNext();
            }
            return current.getElement();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Remove o elemento em um índice específico da lista.
     * @param index O índice do elemento a ser removido.
     */
    public void remove(int index) {
        if (index >= 0 && index < this.size) {
            if (index == 0) {
                this.head = this.head.getNext();
            } else {
                Node<T> prev = this.head;
                for(int i = 0; i < index - 1; ++i) {
                    prev = prev.getNext();
                }
                prev.setNext(prev.getNext().getNext());
            }
            --this.size;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Remove um elemento específico da lista.
     * @param data O elemento a ser removido.
     */
    public void remove(T data) {
        if (this.head != null) {
            if (this.head.getElement().equals(data)) {
                this.head = this.head.getNext();
                --this.size;
            } else {
                Node<T> prev = this.head;
                for(Node<T> current = this.head.getNext(); current != null; current = current.getNext()) {
                    if (current.getElement().equals(data)) {
                        prev.setNext(current.getNext());
                        --this.size;
                        return;
                    }
                    prev = current;
                }
            }
        }
    }

    /**
     * Retorna o número de elementos na lista.
     * @return O número de elementos na lista.
     */
    public int size() {
        return this.size;
    }

    /**
     * Verifica se a lista está vazia.
     * @return Verdadeiro se a lista estiver vazia, falso caso contrário.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Retorna o nó no início da lista.
     * @return O nó no início da lista.
     */
    public Node<T> getFront() {
        return head;
    }

    /**
     * Retorna o nó no fim da lista.
     * @return O nó no fim da lista.
     */
    public Node<T> getRear() {
        return tail;
    }

    /**
     * Limpa a lista, removendo todos os elementos.
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Retorna uma representação em string da lista.
     * @return Uma representação em string da lista.
     */
    public String toString() {
        String result = "";

        for(Node<T> current = this.head; current != null; current = current.getNext()) {
            result = result + current.getElement() + " ";
        }
        return result;
    }
}