package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
import Interfaces.StackADT;
import Exceptions.EmptyCollectionException;

/**
 * Classe ArrayStack que implementa a interface StackADT.
 * Cada ArrayStack tem uma capacidade padrão, um índice para o topo da pilha e um array para armazenar os elementos.
 *
 * @param <T> O tipo de elemento que esta pilha armazena.
 */
public class ArrayStack<T> implements StackADT<T> {

    /**
     * O índice para o topo desta pilha.
     */
    private int top=0;

    /**
     * O array para armazenar os elementos desta pilha.
     */
    private T[] stack;

    /**
     * Construtor para a classe ArrayStack.
     * Inicializa o topo da pilha como 0 e o array com a capacidade padrão.
     */
    public ArrayStack() {
        top = 0;
        /**
         * A capacidade padrão desta pilha.
         */
        int DEFAULT_CAPACITY = 100;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Construtor para a classe ArrayStack.
     * Inicializa o array com a capacidade inicial fornecida.
     *
     * @param initialCapacity A capacidade inicial da pilha.
     */
    public ArrayStack(int initialCapacity) {
        stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adiciona um elemento ao topo da pilha.
     * Se a pilha estiver cheia, expande a capacidade da pilha.
     * Adiciona o elemento no topo da pilha e incrementa o índice do topo.
     *
     * @param element O elemento a ser adicionado ao topo da pilha.
     */
    public void push(T element) {
        if (this.size() == stack.length) {
            this.expandCapacity();
        }

        this.stack[this.top++] = element;
    }

    /**
     * Expande a capacidade da pilha.
     * Cria um novo array com o dobro da capacidade do array atual e copia os elementos do array atual para o novo array.
     * Atualiza o array desta pilha para o novo array.
     */
    private void expandCapacity() {
        T[] larger = (T[]) new Object[this.stack.length * 2];

        for (int i = 0; i < stack.length; i++) {
            larger[i] = stack[i];
        }
        this.stack = larger;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     * Se a pilha estiver vazia, lança uma exceção EmptyCollectionException.
     * Remove o elemento do topo da pilha, decrementa o índice do topo e retorna o elemento removido.
     *
     * @return O elemento removido do topo da pilha.
     * @throws EmptyCollectionException Se a pilha estiver vazia.
     */
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }

        T result = this.stack[--this.top];
        this.stack[this.top] = null;
        return result;
    }

    /**
     * Retorna o elemento do topo da pilha sem removê-lo.
     * Se a pilha estiver vazia, lança uma exceção EmptyCollectionException.
     *
     * @return O elemento do topo da pilha.
     * @throws EmptyCollectionException Se a pilha estiver vazia.
     */
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return this.stack[this.top - 1];
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return Verdadeiro se a pilha estiver vazia, falso caso contrário.
     */
    @Override
    public boolean isEmpty() {

        return (this.top == 0);
    }

    /**
     * Retorna o tamanho da pilha.
     *
     * @return O tamanho da pilha.
     */
    @Override
    public int size() {

        return this.top;
    }

    /**
     * Retorna uma representação em string da pilha.
     *
     * @return Uma representação em string da pilha.
     */
    @Override
    public String toString() {
        String result = "ArrayStack {";

        for (int i = 0; i < this.top; i++) {
            result = result + " " + this.stack[i];
        }
        result = result + " }";
        return result;
    }
}

