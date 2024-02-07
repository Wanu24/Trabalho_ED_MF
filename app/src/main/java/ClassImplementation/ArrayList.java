package ClassImplementation;

import Exceptions.EmptyCollectionException;
import Interfaces.ListADT;

import java.util.Iterator;
import Exceptions.ElementNotFoundException;

/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe ArrayList que implementa ListADT e Iterable.
 * Cada ArrayList tem uma capacidade padrão, um valor para expansão, um valor para indicar que um elemento não foi encontrado, um índice para o final da lista, um array para armazenar os elementos e um contador de modificações.
 *
 * @param <T> O tipo de elementos que esta lista armazena.
 */
public class ArrayList<T> implements ListADT<T>, Iterable<T> {

    /**
     * O valor que indica que um elemento não foi encontrado na lista.
     */
    private final int NotFound = -1;

    /**
     * O índice para o final desta lista.
     */
    protected int rear;

    /**
     * O array para armazenar os elementos desta lista.
     */
    protected T[] list;

    /**
     * O contador de modificações nesta lista.
     */
    protected int modCount;

    /**
     * Construtor para a classe ArrayList.
     * Inicializa o final da lista como 0 e o array com a capacidade padrão.
     */
    public ArrayList() {
        this.rear = 0;
        /**
         * A capacidade padrão desta lista.
         */
        int DEFAULT_CAPACITY = 100;
        this.list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Construtor para a classe ArrayList.
     * Inicializa o array com a capacidade inicial fornecida.
     *
     * @param initialCapacity A capacidade inicial da lista.
     */
    public ArrayList(int initialCapacity) {
        this.modCount = 0;
        this.rear = 0;
        this.list = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Expande a capacidade da lista.
     * Cria um novo array com o dobro da capacidade do array atual e copia os elementos do array atual para o novo array.
     * Atualiza o array desta lista para o novo array.
     */
    protected void expandCapacity() {
        /**
         * O valor pelo qual a lista é expandida quando a capacidade é atingida.
         */
        int expandBy = 2;
        T[] newList = (T[]) (new Object[list.length * expandBy]);

        System.arraycopy(this.list, 0, newList, 0, this.list.length);

        this.list = newList;
    }

    /**
     * Remove e retorna o primeiro elemento da lista.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     * Remove o elemento do início da lista, move todos os outros elementos para a esquerda e decrementa o índice do final da lista.
     *
     * @return O primeiro elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        } else {

            T result = this.list[0];

            for (int i = 0; i < this.rear; i++) {
                this.list[i] = this.list[i + 1];
            }

            this.list[rear] = null;
            --this.rear;
            this.modCount++;
            return result;
        }
    }

    /**
     * Remove e retorna o último elemento da lista.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     * Remove o elemento do final da lista e decrementa o índice do final da lista.
     *
     * @return O último elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new EmptyCollectionException("list");
        } else {
            T result = list[rear];
            list[rear] = null;
            --this.rear;
            ++this.modCount;
            return result;
        }
    }

    /**
     * Remove e retorna um elemento específico da lista.
     * Se o elemento não for encontrado, lança uma exceção EmptyCollectionException.
     * Remove o elemento da lista, move todos os elementos à direita do elemento removido para a esquerda e decrementa o índice do final da lista.
     *
     * @param element O elemento a ser removido.
     * @return O elemento removido.
     * @throws EmptyCollectionException Se o elemento não for encontrado na lista.
     */
    @Override
    public T remove(T element) {
        int index = this.find(element);

        if (index == NotFound) {
            throw new EmptyCollectionException("list");
        }

        T result = this.list[index];
        this.rear--;

        for (int scan = index; scan < this.rear; scan++) {
            this.list[scan] = this.list[scan + 1];
        }

        this.list[this.rear] = null;
        this.modCount++;
        return result;
    }

    /**
     * Retorna o primeiro elemento da lista sem removê-lo.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     *
     * @return O primeiro elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("list");

        return this.list[0];
    }

    /**
     * Retorna o último elemento da lista sem removê-lo.
     * Se a lista estiver vazia, lança uma exceção EmptyCollectionException.
     *
     * @return O último elemento da lista.
     * @throws EmptyCollectionException Se a lista estiver vazia.
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("list");

        return this.list[this.rear - 1];
    }

    /**
     * Verifica se a lista contém um elemento específico.
     *
     * @param target O elemento a ser verificado.
     * @return Verdadeiro se a lista contém o elemento, falso caso contrário.
     */
    @Override
    public boolean contains(T target) {
        return (find(target) != NotFound);
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return Verdadeiro se a lista está vazia, falso caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return (rear == 0);
    }

    /**
     * Retorna o tamanho da lista.
     *
     * @return O tamanho da lista.
     */
    @Override
    public int size() {
        return rear;
    }


    /**
     * Retorna um iterador para a lista.
     *
     * @return Um iterador para a lista.
     */
    @Override
    public Iterator<T> iterator() {

        return new ArrayIterator<T>(this.list, this.rear);

    }

    /**
     * Procura um elemento específico na lista.
     * Retorna o índice do elemento, ou -1 se o elemento não for encontrado.
     *
     * @param target O elemento a ser procurado.
     * @return O índice do elemento, ou -1 se o elemento não for encontrado.
     */
    public int find(T target) {
        int position = 0, result = -1;
        boolean found = false;

        if (isEmpty()) {
            while (!found && position < rear) {
                if (target.equals(list[position])) {
                    found = true;
                } else {
                    position++;
                }
            }
        }

        if (found) {
            result = position;
        }

        return result;
    }

    /**
     * Retorna uma representação em string da lista.
     * Percorre a lista e concatena cada elemento em uma string.
     *
     * @return Uma representação em string da lista.
     */
    public String toString() {
        String result = "";

        for (int scan = 0; scan < rear; scan++)
            result = result + list[scan].toString() + "\n";

        return result;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * Se a lista estiver cheia, expande a capacidade da lista.
     * Adiciona o elemento no final da lista e incrementa o índice do final da lista.
     *
     * @param element O elemento a ser adicionado ao final da lista.
     */
    public void add(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        list[rear] = element;
        rear++;
    }

    /**
     * Adiciona um elemento em um índice específico da lista.
     * Se o índice for inválido, lança uma exceção IndexOutOfBoundsException.
     * Se a lista estiver cheia, expande a capacidade da lista.
     * Move todos os elementos à direita do índice para a direita, adiciona o elemento no índice e incrementa o índice do final da lista.
     *
     * @param index O índice onde o elemento deve ser adicionado.
     * @param element O elemento a ser adicionado.
     */
    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("ArrayList index out of range.");
        }
        if (size() == list.length) {
            expandCapacity();
        }
        for (int i = rear; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = element;
        rear++;
    }

    /**
     * Define um elemento em um índice específico da lista.
     * Se o índice for inválido, lança uma exceção IndexOutOfBoundsException.
     * Define o elemento no índice especificado.
     *
     * @param index O índice onde o elemento deve ser definido.
     * @param element O elemento a ser definido.
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("ArrayList index out of range.");
        }
        list[index] = element;
    }
    /**
     * Retorna um elemento de um índice específico da lista.
     * Se o índice for inválido, lança uma exceção IndexOutOfBoundsException.
     *
     * @param index O índice do elemento a ser retornado.
     * @return O elemento no índice especificado.
     */
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("ArrayList index out of range.");
        }
        return list[index];
    }

    /**
     * Classe MyIterator que implementa Iterator.
     * Cada MyIterator tem um índice atual, um contador de modificações esperado e um booleano para indicar se é seguro remover um elemento.
     */
    private class MyIterator<T> implements Iterator<T> {
        /**
         * O índice atual deste iterador.
         */
        private int current;
        /**
         * O contador de modificações esperado deste iterador.
         */
        private int expectedModCount;
        /**
         * Indica se é seguro remover um elemento.
         */
        private boolean okToRemove = false;
        /**
         * Construtor para a classe MyIterator.
         * Inicializa o índice atual como 0, o contador de modificações esperado como o contador de modificações da lista e okToRemove como falso.
         */
        public MyIterator() {
            this.current = 0;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }
        /**
         * Verifica se há um próximo elemento na lista.
         *
         * @return Verdadeiro se houver um próximo elemento, falso caso contrário.
         */
        @Override
        public boolean hasNext() {
            return (current != size());
        }
        /**
         * Retorna o próximo elemento na lista.
         * Se não houver um próximo elemento, lança uma exceção ElementNotFoundException.
         *
         * @return O próximo elemento na lista.
         * @throws ElementNotFoundException Se não houver um próximo elemento.
         */
        @Override
        public T next() {
            if (! hasNext())
                throw new ElementNotFoundException("do not have next element");

            T result = (T) ArrayList.this.list[this.current];
            this.current++;
            this.okToRemove = true;

            return result;
        }
        /**
         * Remove o elemento atual da lista.
         * Se não for seguro remover um elemento, lança uma exceção IllegalStateException.
         *
         * @throws UnsupportedOperationException Se não for seguro remover um elemento.
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            if(!this.okToRemove) {
                throw new IllegalStateException();
            }

            this.okToRemove = false;


            ++this.expectedModCount;

            ArrayList.this.remove(list[this.current]);
        }
    }
}