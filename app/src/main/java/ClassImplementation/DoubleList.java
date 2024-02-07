package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.ListADT;

import java.util.Iterator;

/**
 * Classe DoubleList que implementa a interface ListADT.
 * Cada DoubleList tem um nó frontal, um nó traseiro e uma contagem de elementos.
 *
 * @param <T> O tipo de elemento que esta lista armazena.
 */
public class DoubleList<T> implements ListADT<T>{
    /**
     * O nó frontal desta lista.
     */
    protected DoubleNode<T> front;

    /**
     * O nó traseiro desta lista.
     */
    protected DoubleNode<T> rear;

    /**
     * A contagem de elementos nesta lista.
     */
    protected int count;

    /**
     * Construtor para a classe DoubleList.
     * Inicializa o nó frontal, o nó traseiro e a contagem de elementos como nulo e zero, respectivamente.
     */
    public DoubleList(){
        rear = null;
        front = null;
        count = 0;
    }

    /**
     * Remove e retorna o último elemento da lista.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     * Atualiza o nó traseiro para o nó anterior e decrementa a contagem de elementos.
     *
     * @return O último elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    public T removeLast () throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
        result = rear.getElement();
        rear = rear.getPrevious();

        if (rear == null){
            front = null;
        } else{
            rear.setNext(null);
        }
        count--;

        return result;
    }

    /**
     * Remove e retorna o primeiro elemento da lista.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     * Atualiza o nó frontal para o próximo nó e decrementa a contagem de elementos.
     *
     * @return O primeiro elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    public T removeFirst() throws EmptyCollectionException{
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
        T result = front.getElement();
        front = front.getNext();

        if (front==null) {
            rear = null;
        }else{
            front.setPrevious(null);
        }
        count--;

        return result;
    }

    /**
     * Remove e retorna um elemento específico da lista.
     * Se o elemento não for encontrado, lança uma exceção ElementNotFoundException.
     * Se o elemento for o primeiro ou o último, chama os métodos removeFirst ou removeLast, respectivamente.
     * Caso contrário, atualiza os nós adjacentes para ignorar o nó atual e decrementa a contagem de elementos.
     *
     * @param element O elemento a ser removido.
     * @return O elemento removido.
     * @throws ElementNotFoundException Se o elemento não for encontrado na lista.
     */
    public T remove (T element){
        T result;
        DoubleNode<T> nodeptr = find (element);


        if (nodeptr == null) {
            throw new ElementNotFoundException("list");
        }
        result = nodeptr.getElement();

        // check to see if front or rear
        if (nodeptr == front) {
            result = this.removeFirst();

        }else if (nodeptr == rear) {
            result = this.removeLast();

        }else{
            nodeptr.getNext().setPrevious(nodeptr.getPrevious());
            nodeptr.getPrevious().setNext(nodeptr.getNext());
            count--;
        }

        return result;
    }

    /**
     * Retorna o primeiro elemento da lista sem removê-lo.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     *
     * @return O primeiro elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    public T first() throws EmptyCollectionException {
        if (isEmpty()){
            throw new EmptyCollectionException("list");
        }
        return front.getElement();
    }

    /**
     * Retorna o último elemento da lista sem removê-lo.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     *
     * @return O último elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    public T last() throws EmptyCollectionException{
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        }
        return rear.getElement();
    }

    /**
     * Verifica se a lista contém um elemento específico.
     *
     * @param target O elemento a ser verificado.
     * @return Verdadeiro se a lista contém o elemento, falso caso contrário.
     */
    public boolean contains (T target) {
        return (find(target) != null);
    }

    /**
     * Procura um elemento específico na lista.
     * Retorna o nó contendo o elemento, ou nulo se o elemento não for encontrado.
     *
     * @param target O elemento a ser procurado.
     * @return O nó contendo o elemento, ou nulo se o elemento não for encontrado.
     */
    private DoubleNode<T> find (T target){
        boolean found = false;
        DoubleNode<T> traverse = front;
        DoubleNode<T> result = null;

        if (! isEmpty())
            while (! found && traverse != null)
                if (target.equals(traverse.getElement()))
                    found = true;
                else
                    traverse = traverse.getNext();

        if (found)
            result = traverse;

        return result;
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return Verdadeiro se a lista está vazia, falso caso contrário.
     */
    public boolean isEmpty(){
        return (count == 0);
    }

    /**
     * Retorna o tamanho da lista.
     *
     * @return O tamanho da lista.
     */
    public int size(){
        return count;
    }

    /**
     * Retorna um iterador para a lista.
     *
     * @return Um iterador para a lista.
     */
    public Iterator<T> iterator(){
        return new DoubleIterator<T>(front, count);
    }

    /**
     * Retorna uma representação em string da lista.
     * Percorre a lista e concatena cada elemento em uma string.
     *
     * @return Uma representação em string da lista.
     */
    public String toString()    {
        String result = "";
        DoubleNode<T> traverse = front;

        while (traverse != null)
        {
            result = result + (traverse.getElement()).toString() + "\n";
            traverse = traverse.getNext();
        }
        return result;
    }
}
