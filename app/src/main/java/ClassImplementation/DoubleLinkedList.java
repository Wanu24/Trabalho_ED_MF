package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
import Interfaces.ListADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe DoubleLinkedList que implementa as interfaces ListADT e Iterable.
 * Cada DoubleLinkedList tem um nó cabeça, um nó cauda, um contador de elementos e um contador de modificações.
 *
 * @param <T> O tipo de elemento que esta lista armazena.
 */
public class DoubleLinkedList<T> implements ListADT<T>,Iterable<T> {
    /**
     * O nó cabeça desta lista.
     */
    protected DoubleNode<T> head = null;

    /**
     * O nó cauda desta lista.
     */
    protected DoubleNode<T> tail = null;

    /**
     * O contador de elementos nesta lista.
     */
    protected int count = 0;

    /**
     * O contador de modificações nesta lista.
     */
    protected int modCount = 0;

    /**
     * Construtor para a classe DoubleLinkedList.
     * Inicializa a lista como vazia.
     */
    public DoubleLinkedList() {
    }

    /**
     * Remove e retorna o primeiro elemento da lista.
     * Se a lista estiver vazia, retorna nulo.
     *
     * @return O primeiro elemento da lista, ou nulo se a lista estiver vazia.
     */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            T result = this.head.getElement();
            this.head = this.head.getNext();
            --this.count;
            if (this.isEmpty()) {
                this.tail = null;
            } else {
                this.head.setPrevious(null);
            }

            ++this.modCount;
            return result;
        }
    }

    /**
     * Remove e retorna o último elemento da lista.
     * Se a lista estiver vazia, retorna nulo.
     *
     * @return O último elemento da lista, ou nulo se a lista estiver vazia.
     */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            T result = this.tail.getElement();
            this.tail = this.tail.getPrevious();
            --this.count;
            if (this.isEmpty()) {
                this.head = null;
            } else {
                this.tail.setNext(null);
            }

            ++this.modCount;
            return result;
        }
    }

    /**
     * Remove e retorna um elemento específico da lista.
     * Se a lista estiver vazia ou o elemento não for encontrado, retorna nulo.
     *
     * @param element O elemento a ser removido.
     * @return O elemento removido, ou nulo se a lista estiver vazia ou o elemento não for encontrado.
     */
    public T remove(T element) {
        if (this.isEmpty()) {
            return null;
        } else {
            boolean found = false;
            DoubleNode<T> current = this.head;

            while (current != null && !found) {
                if (element.equals(current.getElement())) {
                    found = true;
                } else {
                    current = current.getNext();
                }
            }

            if (!found) {
                return null;
            } else {
                if (this.size() == 1) {
                    this.head = this.tail = null;
                } else if (current.equals(this.head)) {
                    this.head = current.getNext();
                    this.head.setPrevious(null);
                } else if (current.equals(this.tail)) {
                    this.tail = current.getPrevious();
                    this.tail.setNext(null);
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }

                --this.count;
                ++this.modCount;
                return current.getElement();
            }
        }
    }

    /**
     * Retorna o primeiro elemento da lista.
     * Se a lista estiver vazia, retorna nulo.
     *
     * @return O primeiro elemento da lista, ou nulo se a lista estiver vazia.
     */
    public T first() {
        return this.isEmpty() ? null : this.head.getElement();
    }

    /**
     * Retorna o último elemento da lista.
     * Se a lista estiver vazia, retorna nulo.
     *
     * @return O último elemento da lista, ou nulo se a lista estiver vazia.
     */
    public T last() {
        return this.isEmpty() ? null : this.tail.getElement();
    }

    /**
     * Verifica se a lista contém um elemento específico.
     *
     * @param target O elemento a ser verificado.
     * @return Verdadeiro se a lista contém o elemento, falso caso contrário.
     */
    public boolean contains(T target) {
        if (this.isEmpty()) {
            return false;
        } else {
            for (DoubleNode<T> current = this.head; current != null; current = current.getNext()) {
                if (target.equals(current.getElement())) {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return Verdadeiro se a lista está vazia, falso caso contrário.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Retorna o tamanho da lista.
     *
     * @return O tamanho da lista.
     */
    public int size() {
        return this.count;
    }

    /**
     * Retorna um iterador para a lista.
     *
     * @return Um iterador para a lista.
     */
    @Override
    public Iterator<T> iterator() {

        return new DoubleIterator<T>(this.head,this.count);

    }

    /**
     * Retorna uma representação em string da lista.
     *
     * @return Uma representação em string da lista.
     */
    public String toString() {
        String result = "DoublyLinkedList { ";

        for (DoubleNode<T> current = this.head; current != null; current = current.getNext()) {
            result = result + String.valueOf(current.getElement()) + " ";
        }

        return result + "}";
    }
    /**
     * Retorna o nó cabeça da lista.
     *
     * @return O nó cabeça da lista.
     */
    public DoubleNode<T> getHead() {
        return this.head;
    }

    /**
     * Classe MyIterator que implementa a interface Iterator.
     * Cada MyIterator tem um contador atual, um contador de modificações esperado, um indicador de remoção e um elemento atual.
     *
     * @param <T> O tipo de elemento que este iterador percorre.
     */
    /**
     * Classe MyIterator que implementa a interface Iterator.
     * Cada MyIterator tem um contador atual, um contador de modificações esperado, um indicador de remoção e um elemento atual.
     *
     * @param <T> O tipo de elemento que este iterador percorre.
     */
    private class MyIterator<T> implements Iterator<T> {
        /**
         * O contador atual deste iterador.
         */
        private int current;

        /**
         * O elemento atual deste iterador.
         */
        private DoubleNode<T> currentElement;

        /**
         * Construtor para a classe MyIterator.
         * Inicializa o contador atual e o indicador de remoção como zero e falso, respectivamente.
         * Define o elemento atual como o nó cabeça da lista e o contador de modificações esperado como o contador de modificações da lista.
         */
        public MyIterator() {
            this.current = 0;
            /**
             * O contador de modificações esperado deste iterador.
             */
            int expectedModCount = modCount;
            this.currentElement = (DoubleNode<T>) head;
        }

        /**
         * Verifica se há um próximo elemento na lista.
         *
         * @return Verdadeiro se há um próximo elemento, falso caso contrário.
         */
        public boolean hasNext() {
            return (current != size());
        }

        /**
         * Retorna o próximo elemento na lista.
         * Se não houver um próximo elemento, lança uma exceção NoSuchElementException.
         * Atualiza o elemento atual para o próximo elemento na lista e incrementa o contador atual.
         * Define o indicador de remoção como verdadeiro.
         *
         * @return O próximo elemento na lista.
         * @throws NoSuchElementException Se não houver um próximo elemento na lista.
         */
        public T next() {
            if (! hasNext()) {
                throw new NoSuchElementException();
            }
            T result = currentElement.getElement();
            currentElement = currentElement.getNext();
            current++;

            return result;
        }

        /**
         * Lança uma exceção UnsupportedOperationException.
         * Este método não é suportado nesta implementação de iterador.
         *
         * @throws UnsupportedOperationException Sempre.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
