package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe DoubleIterator que implementa a interface Iterator.
 * Cada DoubleIterator tem um nó atual, um nó anterior, um contador e um contador de modificações.
 *
 * @param <T> O tipo de elemento que este iterador percorre.
 */
public class DoubleIterator<T> implements Iterator{
    /**
     * O nó atual deste iterador.
     */
    private DoubleNode<T> current;

    /**
     * O nó anterior a este nó na lista.
     */
    private DoubleNode<T> previous;

    /**
     * O contador de elementos neste iterador.
     */
    private int count;

    /**
     * O contador de modificações neste iterador.
     */
    private int modCount;

    /**
     * Construtor para a classe DoubleIterator.
     * Inicializa o nó atual com o nó cabeça fornecido, o contador com o valor fornecido e o contador de modificações como zero.
     *
     * @param head O nó cabeça da lista.
     * @param count O número de elementos na lista.
     */
    public DoubleIterator(DoubleNode<T> head, int count) {
        this.current = head;
        this.count = count;
        this.modCount = 0;
    }

    /**
     * Verifica se há um próximo elemento na lista.
     *
     * @return Verdadeiro se há um próximo elemento, falso caso contrário.
     */
    public boolean hasNext() {
        return this.count != 0;
    }

    /**
     * Retorna o próximo elemento na lista.
     * Se não houver um próximo elemento, lança uma exceção NoSuchElementException.
     * Atualiza o nó atual para o próximo nó na lista e decrementa o contador.
     *
     * @return O próximo elemento na lista.
     * @throws NoSuchElementException Se não houver um próximo elemento na lista.
     */
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            T result = this.current.getElement();
            this.previous = this.current;
            this.current = this.current.getNext();
            --this.count;
            return result;
        }
    }

    /**
     * Lança uma exceção UnsupportedOperationException.
     * Este método não é suportado nesta implementação de iterador.
     *
     * @throws UnsupportedOperationException Sempre.
     */
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
