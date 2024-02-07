package ClassImplementation;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;

/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe ArrayIterator que implementa a interface Iterator.
 * Cada ArrayIterator tem uma contagem de elementos, um índice atual e um array de elementos.
 *
 * @param <T> O tipo de elemento que este iterador percorre.
 */
public class ArrayIterator<T> implements Iterator {
    /**
     * A contagem de elementos neste iterador.
     */
    private final int count;

    /**
     * O índice atual deste iterador.
     */
    private int current;

    /**
     * O array de elementos deste iterador.
     */
    private final T[] items;

    /**
     * Construtor para a classe ArrayIterator.
     * Inicializa o array de elementos com a coleção fornecida, a contagem de elementos com o tamanho fornecido e o índice atual como 0.
     *
     * @param collection A coleção de elementos a ser percorrida por este iterador.
     * @param size O número de elementos na coleção.
     */
    public ArrayIterator (T[] collection, int size) {
        items = collection;
        count = size;
        current = 0;
    }

    /**
     * Verifica se há um próximo elemento na coleção.
     *
     * @return Verdadeiro se há um próximo elemento, falso caso contrário.
     */
    public boolean hasNext() {
        return (current < count);
    }

    /**
     * Retorna o próximo elemento na coleção.
     * Se não houver um próximo elemento, lança uma exceção ElementNotFoundException.
     * Incrementa o índice atual e retorna o elemento no índice atualizado.
     *
     * @return O próximo elemento na coleção.
     * @throws ElementNotFoundException Se não houver um próximo elemento na coleção.
     */
    public T next() {
        if (! hasNext()) {
            throw new ElementNotFoundException("No next element");
        }
        current++;
        return items[current - 1];
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

