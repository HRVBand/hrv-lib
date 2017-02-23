package common;

public class RotatingMaxSizeList<T> {

    private final T[] a;

    private int currentlastElementIndex = -1;
    
    public RotatingMaxSizeList(T[] array) {
        this.a = array;
    }

	public T get(int arg0) {
		return a[arg0];
	}

	public int size() {
		return a.length;
	}
	
	public boolean add(T first) {		
		currentlastElementIndex = (currentlastElementIndex + 1) % a.length;
		a[currentlastElementIndex] = first;
		return true;
	}
	
	public T[] getArray() {
		return a;
	}
}