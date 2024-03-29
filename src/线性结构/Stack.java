package 线性结构;

import javafx.beans.binding.ObjectBinding;

/**
 * 栈的数组实现
 *
 * @param <T>
 */
public class Stack<T> {
    private static final int DEFAULTSIZE = 10000;
    T[] array;
    int length = 0;
    int size;

    public Stack() {
        this(DEFAULTSIZE);
    }

    public Stack(int size) {
        this.size = size;
        array = (T[]) new Object[size];
    }

    public boolean isFull() {
        return length == size;
    }

    public boolean isEmpty(){
        return length==0;
    }

    public void push(T element) {
        if (isFull())
            throw new OutOfMemoryError();
        array[length++] = element;
    }

    public T top(){
        if(isEmpty())
            throw new NullPointerException();
        return array[length-1];
    }

    public T pop(){
        if(isEmpty())
            throw new NullPointerException();
        return array[--length];
    }
    public static void main(String[] args){
        Stack<Integer>  test = new Stack<>();
        test.push(1);
        System.out.print(test.top()+" " + test.pop()+" "+ test.length);

    }
}
