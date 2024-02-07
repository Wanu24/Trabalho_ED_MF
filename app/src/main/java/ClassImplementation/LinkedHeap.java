/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassImplementation;

import Interfaces.HeapADT;
import Exceptions.EmptyCollectionException;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
/**
 * Classe LinkedHeap que estende LinkedBinaryTree e implementa HeapADT.
 * Cada LinkedHeap tem um lastNode, que é o último nó que foi adicionado ao heap.
 *
 * @param <T> O tipo de elementos que este heap contém.
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {
   /**
    * O último nó que foi adicionado ao heap.
    */
   public HeapNode<T> lastNode;
   /**
    * Construtor para a classe LinkedHeap.
    * Chama o construtor da superclasse.
    */
   public LinkedHeap() {
      super();
   }

   /**
    * Adiciona um elemento ao heap.
    * Se o heap estiver vazio, a raiz se torna o novo nó.
    * Caso contrário, o novo nó é adicionado como filho do próximo nó pai.
    * O lastNode é atualizado para o novo nó.
    * Se o heap tiver mais de um nó após a adição, o heap é reorganizado.
    *
    * @param obj O elemento a ser adicionado ao heap.
    */
   public void addElement (T obj) {
      HeapNode<T> node = new HeapNode<T>(obj);
      if (root == null)
         root=node;
      else {
         HeapNode<T> next_parent = getNextParentAdd();
         if (next_parent.left == null)
            next_parent.left = node;
         else
            next_parent.right = node;
         node.parent = next_parent;
      }
      lastNode = node;
      count++;
      if (count>1)
         heapifyAdd();
   }

   /**
    * Retorna o próximo nó pai onde um novo nó deve ser adicionado.
    *
    * @return O próximo nó pai.
    */
   private HeapNode<T> getNextParentAdd() {
      HeapNode<T> result = lastNode;
      while ((result != root) &&
                     (result.parent.left != result))
         result = result.parent;
      if (result != root)
         if (result.parent.right == null)
            result = result.parent;
         else {
            result = (HeapNode<T>)result.parent.right;
            while (result.left != null)
               result = (HeapNode<T>)result.left;
         }
      else
         while (result.left != null)
            result = (HeapNode<T>)result.left;
      return result;
   }

   /**
    * Reorganiza o heap após uma adição.
    * O novo nó é movido para cima até que a propriedade do heap seja restaurada.
    */
   private void heapifyAdd() {
      T temp;
      HeapNode<T> next = lastNode;
      temp = next.element;
      while ((next != root) && (((Comparable)temp).compareTo
                                 (next.parent.element) < 0))
      {
         next.element = next.parent.element;
         next = next.parent;
      }
      next.element = temp;
   }

   /**
    * Remove e retorna o elemento mínimo do heap.
    * Se o heap estiver vazio, uma EmptyCollectionException é lançada.
    * Se o heap tiver apenas um nó, a raiz e o lastNode são definidos como nulos.
    * Caso contrário, a raiz é substituída pelo último nó e o heap é reorganizado.
    *
    * @return O elemento mínimo do heap.
    * @throws EmptyCollectionException Se o heap estiver vazio.
    */
   public T removeMin() throws EmptyCollectionException {
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");
      T minElement =  root.element;
      if (count == 1){
         root = null;
         lastNode = null;
      }
      else{
           HeapNode<T> next_last = getNewLastNode();
         if (lastNode.parent.left == lastNode)
            lastNode.parent.left = null;
         else
            lastNode.parent.right = null;
         root.element = lastNode.element;
         lastNode = next_last;
         heapifyRemove();
      }
      count--;
      return minElement;
   }

   /**
    * Retorna o novo último nó após uma remoção.
    *
    * @return O novo último nó.
    */
   private HeapNode<T> getNewLastNode(){
      HeapNode<T> result = lastNode;
      while ((result != root) && (result.parent.left == result))
         result = result.parent;
      if (result != root)
         result = (HeapNode<T>)result.parent.left;
      while (result.right != null)
         result = (HeapNode<T>)result.right;
      return result;
   }

   /**
    * Reorganiza o heap após uma remoção.
    * A raiz é movida para baixo até que a propriedade do heap seja restaurada.
    */
   private void heapifyRemove(){
      T temp;
      HeapNode<T> node = (HeapNode<T>)root;
      HeapNode<T> left = (HeapNode<T>)node.left;
      HeapNode<T> right = (HeapNode<T>)node.right;
      HeapNode<T> next;
      if ((left == null) && (right == null))
         next = null;
      else if (left == null)
         next = right;
      else if (right == null)
         next = left;
      else if (((Comparable)left.element).compareTo(right.element) < 0)
         next = left;
      else
         next = right;
       temp = node.element;
      while ((next != null) && (((Comparable)next.element).compareTo(temp) < 0)){
         node.element = next.element;
         node = next;
         left = (HeapNode<T>)node.left;
         right = (HeapNode<T>)node.right;
         if ((left == null) && (right == null))
            next = null;
         else if (left == null)
            next = right;
         else if (right == null)
            next = left;
         else if (((Comparable)left.element).compareTo(right.element) < 0)
            next = left;
         else
            next = right;
      }
      node.element = temp;
   }

   /**
    * Retorna o elemento mínimo no heap sem removê-lo.
    *
    * @return O elemento mínimo no heap.
    */
    @Override
    public T findMin() {
        return super.root.element;
    }
}