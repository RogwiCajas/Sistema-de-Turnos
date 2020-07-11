/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author Alejandra Victoria Quimi Brunner
 */
public class CircularDoublyLinkedList<E> implements List<E>, Iterable<E> {
    private Node<E> last;
    private int current;

    public Node<E> getprimero(){
        return last.getNext();
    }
        
       
    @Override
    public boolean addFirst(E e) {
        Node<E> nodo=new Node<>(e);
        if(e==null)return false;
        else if(isEmpty()){
            last=nodo;
            nodo.setNext(nodo);
            nodo.setPrevious(nodo);
                      
        }else{
        
        Node<E> tmp=last.getNext();
        last.setNext(nodo);
        nodo.setPrevious(last);
        nodo.setNext(tmp);
        tmp.setPrevious(nodo);
        
        }
        current++;
        return true;    
        
    }

    @Override
    public boolean addLast(E e) {
        Node<E> nodo=new Node<>(e);
        if(e==null)return false;
        else if(isEmpty()){
            last=nodo;
            nodo.setNext(nodo);
            nodo.setPrevious(nodo);
                
        }else{
            Node<E> ultimo=last;
            
            Node<E>previo=last.getNext();
            ultimo.setNext(nodo);
            nodo.setPrevious(ultimo);
            nodo.setNext(previo);
            previo.setPrevious(nodo);
            last=nodo;
            
           
           
        }
        current++;
        return true;
        
    }

    @Override
    public E getFirst() {
        if (isEmpty()) 
            return null;
        return last.getNext().getData();
    }

    @Override
    public E getLast() {
        if (isEmpty()) 
            return null;
        
        return last.getData();
    }

    @Override
    public int indexOf(E e) {
                if (e == null || isEmpty()) {
            return -1;
        }
        int i = 0;
        Node<E> first = last.getNext();
        Node<E> q = first;
        do {
            if (q.getData().equals(e)) {
                return i;
            }
            i++;
            q = q.getNext();

        } while (q != first);
        return -1;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        if(last.getNext()==last){
            last.setData(null);
            last=null;
            
        }
        else{
        Node<E> previo=last.getPrevious();
        Node<E>siguiente=last.getNext();
        previo.setNext(siguiente);
        siguiente.setPrevious(previo);
        last.setData(null);
        last.setNext(null);
        last.setPrevious(null);
        last=previo;

        }
        current--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
     if(last==last.getNext()){
         last.setNext(null);
         last.setPrevious(null);
         last.setData(null);
         last=null;
     }
     else{
         Node<E> siguiente=last.getNext();
         Node<E>otro=siguiente.getNext();
         otro.setPrevious(last);
         last.setNext(otro);
         siguiente.setData(null);
         siguiente.setNext(null);
         siguiente.setPrevious(null);
         
         
         
     }
      current--;
     return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(index<0||index>current||e==null)return false;
        if(index==size())
            return addLast(e);
        if(index==0) return addFirst(e);
        Node<E> tmp=new Node<>(e);
        Node<E> n=getNode(index);
        n.getPrevious().setNext(tmp);
        tmp.setPrevious(n.getPrevious());
        tmp.setNext(n);
        n.setPrevious(tmp);
        
        current++;
        return true;  
        
        
        
    }
    
   /**
     * Metodo para obtener un nodo a partir de un indice
     * @param index
     */
    private Node<E>getNode(int index){
    if(index==current-1)return last;
    int ind=0;
    Node<E> tmp=last.getNext();
    while(index!=ind){
        tmp=tmp.getNext();
        ind++;
    }
      return tmp;
    }

    @Override
    public boolean set(int index, E e) {
        if(index<0||index>=current||e==null)return false;
        Node<E> n=getNode(index);
        n.setData(e);
        
        return true;
        
        
        
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public E get(int index) {
        if(index<0||index>=current||isEmpty())return null;
        return getNode(index).getData();
    }

    @Override
    public boolean contains(E e) {
        if(e==null||isEmpty())return false;
        Node<E> p = last.getNext();
        do{
            if(p.getData().equals(e))
                return true;
            p=p.getNext();
        }while(p!=last.getNext());
        return false;
        
        
        
    }

    @Override
    public boolean remove(int index) {
        if(index<0||index>=current)return false;
        if(index==current-1)return removeLast();
        if(index==0)return removeFirst();
        Node<E> p=getNode(index);
        Node<E> tmp=p.getNext();
        p.getPrevious().setNext(tmp);
        tmp.setPrevious(p.getPrevious());
        p.setData(null);
        p.setNext(null);
        p.setPrevious(null);
       
        
        current--;
        return true;
        
        
        
    }

    @Override
    public Iterator<E> iterator() {
            Iterator<E> it= new Iterator<E>(){
            private Node<E> p=last.getNext();
            
            @Override
            public boolean hasNext() {
                return p!=null;    
            }

            @Override
            public E next() {
                E tmp=p.getData();
                if(p==last)
                    p=null;
                else
                    p=p.getNext();
                return tmp;
                   
            }
            
        };
        return it;
        
    }
    private Node<E>searchNode(int index){
        if(index==current-1)return last;
    int ind=0;
    Node<E> tmp=last.getNext();
    while(index!=ind){
        tmp=tmp.getNext();
        ind++;
    }
      return tmp;
    }
    
    public ListIterator<E> listIterator(){
        return listIterator(0);
        
    }
    
    
    
    
        public ListIterator<E> listIterator(int index){
        ListIterator<E> lit=new ListIterator<E>() {
                private Node<E>p=searchNode(index);
                private int i=index;
                
                
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                
                 E tmp=p.getData();
                if(p==last)
                    p=null;
                else
                    p=p.getNext();
                    
                //i++;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E tmp=p.getData();
                if(p==last)
                    p=null;
                else{
                    p=p.getPrevious();
                    
                }
                return tmp;            }

            @Override
            public int nextIndex() {
                return i+1;
            }

            @Override
            public int previousIndex() {
                return i-1;
            }

            @Override
            public void remove() {
                if(i==current-1)removeLast();
                if(i==0)removeFirst();
                else{
                    if(p!=null){
                    
                    p=p.getPrevious();
                     Node<E> previo=p.getPrevious();
                     Node<E> next=p.getNext();
                     previo.setNext(next);
                     next.setPrevious(previo);
                     p.setData(null);
                     p.setNext(null);
                     p.setPrevious(null);
                     p=next;
                   
                     current--;
                     
                    }}
            }

            @Override
            public void set(E e) {
                if(p!=null){
                    p.setData(e);
               
                if(p==last)
                    p.setData(e);
                   
                }        
            }

            @Override
            public void add(E e) {
                insert(i++,e);
                
                
            }
    };
        return lit;
    
                }

        @Override
    public String toString()
    {
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> nodo=last.getNext();
        do{
            if(nodo!=last){
                sb.append(nodo.getData());
                sb.append(",");
            }else{
                sb.append(nodo.getData());
                 sb.append("]");
            }
            nodo=nodo.getNext();
        }while(nodo!=last.getNext());
        return sb.toString();
}
    
    
    
   
    
    
    
}

