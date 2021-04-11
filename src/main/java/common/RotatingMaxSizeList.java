package common;

public class RotatingMaxSizeList<T> {

    private final T[] a;
    private int fillRate;

    private int currentLastElementIndex = -1;
    
    public RotatingMaxSizeList(T[] array) {
        this.a = array;
    }

	public T get(int arg0) {
		return a[arg0];
	}

	public int size() {
		return a.length;
	}
	
	/**
	 * Returns how full the list is
	 * @return how full the list is
	 */
	public int fillRate() {
		return fillRate;
	}
	
	public boolean add(T first) {		
		currentLastElementIndex = (currentLastElementIndex + 1) % a.length;
		a[currentLastElementIndex] = first;
		
		fillRate = Math.max(fillRate(), currentLastElementIndex + 1);
		
		return true;
	}
	
	public T[] getArray() {
		return a;
	}
}
