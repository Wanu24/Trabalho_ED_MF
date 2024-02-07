package ClassImplementation;

import Exceptions.ElementNotFoundException;
import Interfaces.BinaryTreeADT;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
/**
 * Classe LinkedBinaryTree que representa uma árvore binária ligada.
 * @param <T> O tipo de dados que a árvore irá conter.
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;


    /**
     * Construtor padrão da classe LinkedBinaryTree.
     * Inicializa a raiz como nula e o contador como 0.
     */
    public LinkedBinaryTree(){
        count = 0;
        root = null;
    }

    /**
     * Retorna a contagem de nós na árvore.
     * @return A contagem de nós na árvore.
     */
    public int getCount() {
        return count;
    }

    /**
     * Define a contagem de nós na árvore.
     * @param count A nova contagem de nós.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Define a raiz da árvore.
     * @param root O novo nó raiz.
     */
    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }


    /**
     * Verifica se um elemento específico está na árvore.
     * @param targetElement O elemento a ser verificado.
     * @return Verdadeiro se o elemento estiver na árvore, falso caso contrário.
     */
    @Override
    public boolean contains(T targetElement) {
        boolean contains = false;
        T element = null;
        try {
            element = this.find(targetElement);
        } catch (ElementNotFoundException ex) {
            System.out.println(ex);
        }

        if (element != null) {
            contains = true;
        }
        return contains;
    }

    /**
     * Procura um elemento específico na árvore binária.
     * Este método chama o método findAgain para procurar o elemento a partir da raiz da árvore.
     * Se o elemento não for encontrado, lança uma exceção ElementNotFoundException.
     * Se o elemento for encontrado, retorna o elemento do nó encontrado.
     *
     * @param targetElement O elemento a ser procurado.
     * @return O elemento encontrado.
     * @throws ElementNotFoundException Se o elemento não for encontrado na árvore.
     */
    public T find(T targetElement) throws ElementNotFoundException{
        BinaryTreeNode<T> current = findAgain( targetElement, root );

        if( current == null )
            throw new ElementNotFoundException("binary tree");

        return (current.element);
    }

    /**
     * Procura recursivamente um elemento específico na árvore binária a partir de um nó fornecido.
     * Este método é recursivo e procura o elemento na subárvore esquerda e depois na subárvore direita.
     * Se o nó fornecido for nulo, retorna nulo.
     * Se o elemento do nó fornecido for igual ao elemento alvo, retorna o nó.
     * Se o elemento não for encontrado na subárvore esquerda, procura na subárvore direita.
     *
     * @param targetElement O elemento a ser procurado.
     * @param next O nó a partir do qual a busca deve começar.
     * @return O nó que contém o elemento alvo, ou nulo se o elemento não for encontrado.
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;

        if (next.element.equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null)
            temp = findAgain(targetElement, next.right);

        return temp;
    }

    /**
     * Retorna o elemento na raiz da árvore.
     * @return O elemento na raiz da árvore.
     */
    @Override
    public T getRoot() {
        return root.element;
    }

    /**
     * Verifica se a árvore está vazia.
     * @return Verdadeiro se a árvore estiver vazia, falso caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retorna o número de elementos na árvore.
     * @return O número de elementos na árvore.
     */
    @Override
    public int size() {
        return count;
    }


    /**
     * Realiza uma travessia em ordem na árvore binária.
     * Este método é recursivo e visita primeiro a subárvore esquerda, depois o nó atual e, por fim, a subárvore direita.
     * Se o nó atual não for nulo, ele chama a si mesmo para a subárvore esquerda, adiciona o elemento do nó atual à lista temporária,
     * e depois chama a si mesmo para a subárvore direita.
     *
     * @param node O nó atual na travessia.
     * @param tempList A lista temporária para armazenar os elementos da árvore em ordem.
     */
    public void inorder (BinaryTreeNode<T> node,ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder (node.left, tempList);
            tempList.addToRear(node.element);
            inorder (node.right, tempList);
        }
    }

    /**
     * Retorna um iterador para a árvore em ordem.
     * @return Um iterador para a árvore em ordem.
     */
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder (root, tempList);

        return tempList.iterator();
    }

    /**
     * Realiza uma travessia em pré-ordem na árvore binária.
     * Este método é recursivo e visita primeiro o nó atual, depois a subárvore esquerda e, por fim, a subárvore direita.
     * Se o nó atual não for nulo, ele adiciona o elemento do nó atual à lista temporária,
     * depois chama a si mesmo para a subárvore esquerda e, por fim, para a subárvore direita.
     *
     * @param node O nó atual na travessia.
     * @param tempList A lista temporária para armazenar os elementos da árvore em pré-ordem.
     */
    public void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preorder(node.left, tempList);
            preorder(node.right, tempList);
        }
    }

    /**
     * Retorna um iterador para a árvore em pré-ordem.
     * Este método cria uma lista temporária e preenche-a com os elementos da árvore em pré-ordem.
     * A ordem pré-ordem é obtida através do método preorder.
     * @return Um iterador para a lista temporária, que contém os elementos da árvore em pré-ordem.
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Realiza uma travessia em pós-ordem na árvore binária.
     * Este método é recursivo e visita primeiro a subárvore esquerda, depois a subárvore direita e, por fim, o nó atual.
     * Se o nó atual não for nulo, ele chama a si mesmo para a subárvore esquerda, depois para a subárvore direita e, por fim, adiciona o elemento do nó atual à lista temporária.
     *
     * @param node O nó atual na travessia.
     * @param tempList A lista temporária para armazenar os elementos da árvore em pós-ordem.
     */
    public void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder(node.left, tempList);
            postorder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    /**
     * Retorna um iterador para a árvore em pós-ordem.
     * Este método cria uma lista temporária e preenche-a com os elementos da árvore em pós-ordem.
     * A ordem pós-ordem é obtida através do método postorder.
     * @return Um iterador para a lista temporária, que contém os elementos da árvore em pós-ordem.
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Realiza uma travessia em ordem de nível na árvore binária.
     * Este método utiliza uma fila para realizar a travessia em ordem de nível.
     * Ele enfileira a raiz da árvore e, em seguida, entra em um loop onde desenfileira um nó,
     * adiciona o elemento desse nó à lista temporária e enfileira os filhos desse nó (se houver).
     * O loop continua até que a fila esteja vazia, o que significa que todos os elementos da árvore foram visitados.
     *
     * @param root A raiz da árvore binária.
     * @param tempList A lista temporária para armazenar os elementos da árvore em ordem de nível.
     */
    public void levelorder(BinaryTreeNode<T> root, ArrayUnorderedList<T> tempList) {
        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<BinaryTreeNode<T>>();
        if (root != null) {
            queue.enqueue(root);
            while (!queue.isEmpty()) {
                BinaryTreeNode<T> node = queue.dequeue();
                tempList.addToRear(node.element);
                if (node.left != null) {
                    queue.enqueue(node.left);
                }
                if (node.right != null) {
                    queue.enqueue(node.right);
                }
            }
        }
    }

    /**
     * Retorna um iterador para a árvore em ordem de nível.
     * Este método cria uma lista temporária e preenche-a com os elementos da árvore em ordem de nível.
     * A ordem de nível é obtida através do método levelorder.
     * Se ocorrer uma exceção durante a execução do método levelorder, ela é registrada no Logger.
     * @return Um iterador para a lista temporária, que contém os elementos da árvore em ordem de nível.
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        try {
            levelorder(root, tempList);
        } catch (Exception ex) {
            Logger.getLogger(LinkedBinaryTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempList.iterator();
    }
    /**
     * Remove todos os elementos da árvore.
     */
    public void removeAllElements() {
        count = 0;
        root = null;
    }
}
