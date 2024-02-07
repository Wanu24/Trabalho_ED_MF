/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
/**
 * Classe HeapNode que estende a classe BinaryTreeNode.
 * Cada nó HeapNode tem um objeto genérico e um nó pai.
 * O nó pai é inicializado como nulo no construtor.
 *
 * @param <T> O tipo de objeto que este nó armazena.
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
   /**
    * O nó pai deste nó HeapNode.
    */
   protected HeapNode<T> parent;

   /**
    * Construtor para a classe HeapNode.
    * Inicializa o objeto do nó com o objeto fornecido e o nó pai como nulo.
    *
    * @param obj O objeto a ser armazenado neste nó.
    */
   HeapNode(T obj) {
      super(obj);
      parent = null;
   }
}
