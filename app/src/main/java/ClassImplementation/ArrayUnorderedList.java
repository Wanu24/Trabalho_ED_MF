package ClassImplementation;

import Exceptions.ElementNotFoundException;
import Interfaces.UnorderedListADT;

/**
 * @author 8210311 Daniela Moreira
 * @author 8210367 Orlando Pires
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {
    public ArrayUnorderedList(){

        super();
    }

    public ArrayUnorderedList (int initialCapacity){

        super(initialCapacity);
    }

    @Override
    public void addToFront(T element) {
        if (this.size() == this.list.length)
            expandCapacity();

        // shift elements to make room
        for (int scan = this.rear; scan > 0; --scan)
            this.list[scan] = this.list[scan-1];

        this.list[0] = element;
        ++this.rear;
        ++this.modCount;
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expandCapacity();

        list[rear] = element;
        rear++;
    }

    @Override
    public void addAfter(T element, T target) {
        if (this.size() == this.list.length) {
            expandCapacity();
        }

        int scan = 0;
        while (scan < rear && !target.equals(list[scan]))
            scan++;

        if (scan == this.rear) {
            throw new ElementNotFoundException("ArrayUnorderedList");
        }else {
            for (int scan2 = this.rear; scan2 > scan; scan2--)
                this.list[scan2] = this.list[scan2 - 1];

            this.list[scan + 1] = element;
            ++this.rear;
            ++this.modCount;
        }
    }
}
