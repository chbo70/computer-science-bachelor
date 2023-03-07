public interface Stack {

    int pop();
    void push(int element);
    int size();
    default boolean isEmpty(){
        return size() <= 0;
    }
    default int peek(){
        if (isEmpty()){
            System.err.println("Stack is already empty");
            return -1;
        }
        int element = pop();
        push(element);
        return element;
    }
}
