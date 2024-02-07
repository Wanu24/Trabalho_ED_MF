package ClassImplementation;

import Exceptions.ElementNotFoundException;
import Interfaces.UnorderedListADT;

/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
/**
 * Classe ArrayUnorderedList que estende a classe ArrayList e implementa a interface UnorderedListADT.
 * Cada ArrayUnorderedList tem uma lista de elementos genéricos.
 *
 * @param <T> O tipo de elemento que esta lista armazena.
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Construtor para a classe ArrayUnorderedList.
     * Inicializa a lista chamando o construtor da classe pai.
     */
    public ArrayUnorderedList(){
        super();
    }

    /**
     * Construtor para a classe ArrayUnorderedList.
     * Inicializa a lista com a capacidade inicial fornecida, chamando o construtor da classe pai.
     *
     * @param initialCapacity A capacidade inicial da lista.
     */
    public ArrayUnorderedList (int initialCapacity){
        super(initialCapacity);
    }

    /**
     * Adiciona um elemento ao início da lista.
     * Se a lista estiver cheia, expande a capacidade da lista.
     * Desloca os elementos para criar espaço e adiciona o elemento no início.
     * Incrementa o contador de elementos e o contador de modificações.
     *
     * @param element O elemento a ser adicionado ao início da lista.
     */
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

    /**
     * Adiciona um elemento ao final da lista.
     * Se a lista estiver cheia, expande a capacidade da lista.
     * Adiciona o elemento no final da lista e incrementa o contador de elementos.
     *
     * @param element O elemento a ser adicionado ao final da lista.
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expandCapacity();

        list[rear] = element;
        rear++;
    }

    /**
     * Adiciona um elemento após um elemento alvo na lista.
     * Se a lista estiver cheia, expande a capacidade da lista.
     * Procura o elemento alvo na lista e adiciona o novo elemento após ele.
     * Se o elemento alvo não for encontrado, lança uma exceção ElementNotFoundException.
     * Incrementa o contador de elementos e o contador de modificações.
     *
     * @param element O elemento a ser adicionado.
     * @param target O elemento alvo após o qual o novo elemento será adicionado.
     * @throws ElementNotFoundException Se o elemento alvo não for encontrado na lista.
     */
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
