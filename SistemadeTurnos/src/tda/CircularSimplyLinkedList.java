/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.util.Iterator;
import java.util.ListIterator;
import sistemadeturnos.List;

/**
 *
 * @author cajas
 * @param <E>
 */
public class CircularSimplyLinkedList<E> implements List <E>,Iterable<E>{
    private Node<E> last;
    private int current;
    public CircularSimplyLinkedList()
    {
        last = null;
        current = 0;
    }
    private class Node<E>
    {
        private E data;
        private Node<E> next;
        
        public Node(E data)
        {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
        
    }
    @Override
    public String toString(){
        if(isEmpty()) return "[]\n";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Node<E> p = last.next; p!=last; p=p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
           
    }
    
    
    
    @Override
    public boolean addFirst(E e) {
        Node<E> n=new Node<>(e);
        if(isEmpty()){ 
            last= n;
            last.next=last;
        }else{
            n.next=last.next;
            last.next=n;
        }
        current++;
        return true;
            
    }

    @Override
    public boolean addLast(E e) {
        Node<E> n = new Node<>(e);
        if(isEmpty()){
            last = n;
            last.next = last;
        }
        else
        {
            n.next = last.next;
            last.next = n;
            last = n;
        }
        current++;
        return true;
    }

    @Override
    public E getFirst() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        else
        {
            return last.next.getData();
        }
    }

    @Override
    public E getLast() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        else
        {
            return last.getData();
        }
        
    }

    @Override
    public int indexOf(E e) throws NullPointerException{
        int index=0;
        if(e==null){
            throw new NullPointerException();
        }else{
            for(Node <E> n=last.next;index<current;n=n.next){
                if(n.getData()==e){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() throws NullPointerException{
        if(isEmpty()) throw  new NullPointerException();
        else if(current==1){
            last.setData(null);
            last.setNext(null);
            
        }else{
            Node<E> n=getPrevious(last);//capturo referencia al nuevo last;
            n.next=last.next;
            last.setData(null);
            last.setNext(null);
            last=n;
        }
        current--;
        return true;
    }
    private Node<E> getPrevious(Node<E> p) throws IllegalArgumentException{//lanzar exepcion illegal argument
        if(p==null) throw  new IllegalArgumentException();
        int index=0;
        for(Node<E> q=this.last;index<current;q=q.getNext()){
            if(q.getNext()==p){
                return q;
            }
        }
        return null;
    }
    @Override
    public boolean removeFirst() throws NullPointerException{
        if(isEmpty()) throw  new NullPointerException();
        else if(current==1){
            last.setData(null);
            last.setNext(null);
        }else{
            Node<E> nuevoFirst=last.next.next;
            last.next.setData(null);
            last.next.setNext(null);
            last.next=nuevoFirst;
            
        }
        current--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) throws IndexOutOfBoundsException, NullPointerException{
        if(index<0 || index>this.size()){
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
        else if(e==null){
            throw  new NullPointerException();
        }
        else if(index==0){
            
            return addFirst(e);
        }
        else if(index==(current)){
            
            return addLast(e);
        }
        else{
            //creo un nodo
            Node<E> q= getNode(index-1);//implementar Node <E > nodeIndex(index)
            Node<E> p=q.getNext();
            
            Node<E> node=new Node<>(e);//nodo a insertar
            q.setNext(node);
            node.setNext(p);
            
            current++;
            return true;
        }
        
    }
    private Node<E> getNode(int index) throws IndexOutOfBoundsException{
        if(isEmpty()|| (index<0 || index>=current)){//si esta vacio o el indice no es correcto
            throw new IndexOutOfBoundsException("indice no valido o lista vacia");
            
        }else{
            int contador=0;
            for(Node<E> q=last.next; contador<current;q=q.getNext()){
                if(contador==index){
                    return q;
                }
                contador++;
            }
            return null;//si no lo encuentra
        }
        
        
    }

    @Override
    public boolean set(int index, E e) throws IndexOutOfBoundsException{
        if(index<0 || index>=this.size()){
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
        getNode(index).setData(e);
        return true;
    }
    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public E get(int index) {
        return getNode(index).getData();
    }

    @Override
    public boolean contains(E e) throws IllegalArgumentException{
        if(e==null) throw new IllegalArgumentException("Argumento nullo");
        int index=0;
        for(Node<E> n=last;index<current;n=n.getNext()){
            if(n.getData()==e) return true;           
        }
        return false;
    }

    @Override
    public boolean remove(int index) throws IndexOutOfBoundsException {
        if(isEmpty()) return false;
        if(index<0 || index>=this.size()){
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
        else if(index==0){
            removeFirst();
            return true;
        }
        else if(index==size()-1){
            removeLast();
            return true;
        }else{
            Node<E> n=getNode(index);
            getPrevious(n).setNext(n.getNext());//enlazo el anterior al posterior
            n.setData(null);
            n.setNext(null);
            current--;
            return true;
        }
        
    }
    
    @Override
    public Iterator<E> iterator(){
         Iterator<E>it= new Iterator<E>(){
             public Node<E> p = last;
             @Override
             public boolean hasNext() {
                return p!=null;
             }

             @Override
             public E next() {
                E tmp = p.getData();
                p=p.getNext();
                return tmp;
             }
             
         };
     return it;    
     }
     
}


