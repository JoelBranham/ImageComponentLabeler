package branham.joel;

public class Queue<T>{

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Queue(){
        head = tail = null;
        size = 0;
    }

    public void enqueue(T element){
        Node<T> newNode = new Node<T>(element);
        if (size == 0){
            head = tail = newNode;
        }
        else if (size == 1) {
            newNode.setNext(tail);
            head = newNode;
        }
        else{
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public T dequeue(){
        T returnData = tail.data;
        if (size > 1) {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            returnData = tail.data;
            tail = current;
        }
        size--;
        return returnData;
    }

    public int size(){
        return size;
    }

    private class Node<T>{
        public T data;
        public Node next;

        public Node(){
            this(null);
        }

        public Node(T data){
            this.data = data;
            next = null;
        }
        public void setNext(Node next){
            this.next = next;
        }
    }
}