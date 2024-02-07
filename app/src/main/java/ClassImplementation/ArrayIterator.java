package ClassImplementation;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;

/**
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
public class ArrayIterator<T> implements Iterator {
    private final int count;
    private int current;
    private final T[] items;

    public ArrayIterator (T[] collection, int size) {
        items = collection;
        count = size;
        current = 0;
    }


    public boolean hasNext()
    {
        return (current < count);
    }


    public T next() {
        if (! hasNext()) {
            throw new ElementNotFoundException("No next element");
        }
        current++;
        return items[current - 1];

    }


    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

