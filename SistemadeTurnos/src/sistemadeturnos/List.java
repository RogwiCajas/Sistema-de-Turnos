/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeturnos;

/**
 *
 * @author cajas
 */
public interface List <E>{
    boolean addFirst(E e);
    boolean addLast(E e);
    E getFirst();
    E getLast();
    int indexOf(E e);
    int size();
    boolean removeLast();
    boolean removeFirst();
    boolean insert(int index, E e);
    boolean set(int index, E e);
    boolean isEmpty();
    E get(int index);
    boolean contains(E e);
    boolean remove(int index);
    
    
}

