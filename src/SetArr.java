public class SetArr<E> {// 1
    private E[] set;
    private int size = 1;

    public SetArr() {
        set = (E[]) new Object[size];
        size = 0;
    }

    public void enter(E item) {
        System.out.println("entering");
        if (size == set.length) {
            ensureCapacity();
        }
        set[size] = item;
        size++;
    }

    public boolean isElement(E item) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void remove(E item) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(item)) {
                set[i] = set[size - 1];
                set[size - 1] = null;
                size--;
                return;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        E[] temp = (E[]) new Object[set.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = set[i];
        }
        set = temp;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += set[i] + " ";
        }
        return result;
    }

    // contains method
    public boolean contains(E item) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    // retreive method
    public E retreive(E item) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(item)) {
                return set[i];
            }
        }
        return null;
    }

}
