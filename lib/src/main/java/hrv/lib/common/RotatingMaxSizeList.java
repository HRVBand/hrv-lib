package hrv.lib.common;

public class RotatingMaxSizeList<T> {

    private final T[] elements;
    private int fillRate;

    private int currentLastElementIndex = -1;
    
    public RotatingMaxSizeList(T[] array) {
        this.elements = array;
    }

	public T get(int arg0) {
		return elements[arg0];
	}

	public int size() {
		return elements.length;
	}
	
	/**
	 * Returns how full the list is
	 * @return how full the list is
	 */
	public int fillRate() {
		return fillRate;
	}
	
	public boolean add(T first) {		
		currentLastElementIndex = (currentLastElementIndex + 1) % elements.length;
		elements[currentLastElementIndex] = first;
		
		fillRate = Math.max(fillRate(), currentLastElementIndex + 1);
		
		return true;
	}
	
	public T[] getArray() {
		return elements;
	}
}
