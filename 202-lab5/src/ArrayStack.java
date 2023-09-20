public class ArrayStack<T> implements Stack<T>{

    private T[] stack;
    private int nextIndex = 0;

    public ArrayStack() {
        stack = (T[]) new Object[nextIndex];
    }
    @Override
    public void push(T item) {
        ensureCapacity();
        stack[nextIndex] = item;
        nextIndex++;
    }

    @Override
    public T pop() {
        T latestElement = peek();
        if (nextIndex > 0) {
            nextIndex--;
        }
        return latestElement;
    }

    @Override
    public T peek() {
        if (nextIndex == 0) return null;
        return stack[nextIndex - 1];
    }

    @Override
    public boolean isEmpty() {
        if(nextIndex > 0){
            return false;
        }else{
            return true;
        }
    }

    private void ensureCapacity() {
        if (nextIndex == stack.length) {
            T[] newArray = (T[])new Object[(nextIndex+1) * 2];
            System.arraycopy(stack,0,newArray,0,nextIndex);
            stack = newArray;
        }
    }
}
