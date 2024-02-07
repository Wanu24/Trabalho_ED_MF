package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe BinaryTreeNode que representa um nó em uma árvore binária.
 * Cada nó BinaryTreeNode tem um elemento genérico e dois nós filhos, esquerdo e direito.
 *
 * @param <T> O tipo de elemento que este nó armazena.
 */
public class BinaryTreeNode<T> {

    /**
     * O elemento armazenado neste nó.
     */
    protected T element;

    /**
     * O nó filho esquerdo deste nó.
     */
    protected BinaryTreeNode<T> left;

    /**
     * O nó filho direito deste nó.
     */
    protected BinaryTreeNode<T> right;

    /**
     * Construtor para a classe BinaryTreeNode.
     * Inicializa o elemento do nó com o objeto fornecido e os nós filhos como nulos.
     *
     * @param obj O objeto a ser armazenado neste nó.
     */
    public BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }

    /**
     * Retorna o elemento armazenado neste nó.
     * @return O elemento armazenado neste nó.
     */
    public T getElement() {
        return element;
    }

    /**
     * Define o elemento armazenado neste nó.
     * @param element O novo elemento a ser armazenado.
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Retorna o nó filho esquerdo deste nó.
     * @return O nó filho esquerdo deste nó.
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Define o nó filho esquerdo deste nó.
     * @param left O novo nó filho esquerdo.
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Retorna o nó filho direito deste nó.
     * @return O nó filho direito deste nó.
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Define o nó filho direito deste nó.
     * @param right O novo nó filho direito.
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Retorna o número de filhos deste nó.
     * Este método é recursivo e conta o número de filhos do nó esquerdo e do nó direito.
     * @return O número de filhos deste nó.
     */
    public int numChildren() {
        int children = 0;
        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }

}
