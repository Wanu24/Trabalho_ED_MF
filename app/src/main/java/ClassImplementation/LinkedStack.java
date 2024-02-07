package ClassImplementation;
import Interfaces.StackADT;
import Exceptions.*;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
/**
 * Classe LinkedStack que representa uma pilha usando uma lista encadeada.
 * @param <T> O tipo de dados que a pilha irá conter.
 */
public class LinkedStack<T> implements StackADT<T>{
    private int count; // O número de elementos na pilha.
    private Node<T> top; // O topo da pilha.

    /**
     * Construtor padrão da classe LinkedStack.
     * Inicializa o contador como 0 e o topo como nulo.
     */
    public LinkedStack()    {
        count = 0;
        top = null;
    }

    /**
     * Adiciona um elemento ao topo da pilha.
     * @param element O elemento a ser adicionado ao topo da pilha.
     */
    public void push (T element)    {
        Node<T> temp = new Node<>(element);

        temp.setNext(top);
        top = temp;
        count++;
    }

    /**
     * Remove e retorna o elemento no topo da pilha.
     * @return O elemento no topo da pilha.
     * @throws EmptyStackException Se a pilha estiver vazia.
     */
    public T pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();

        T result = top.getElement();
        top = top.getNext();
        count--;

        return result;
    }

    /**
     * Retorna o elemento no topo da pilha sem removê-lo.
     * @return O elemento no topo da pilha.
     * @throws EmptyStackException Se a pilha estiver vazia.
     */
    public T peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();

        return top.getElement();
    }

    /**
     * Verifica se a pilha está vazia.
     * @return Verdadeiro se a pilha estiver vazia, falso caso contrário.
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o número de elementos na pilha.
     * @return O número de elementos na pilha.
     */
    public int size(){
        return count;
    }

    /**
     * Retorna uma representação em string da pilha.
     * @return Uma representação em string da pilha.
     */
    public String toString() {
        String result = "";
        Node<T> current = top;

        while (current != null)
        {
            result = result + (current.getElement()).toString() + "\n";
            current = current.getNext();
        }

        return result;
    }
}