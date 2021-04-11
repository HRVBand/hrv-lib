package algorithm.search;

/**
 * Searches the index of a sorted array which's 
 * value is the closest to a given value
 * 
 * @author Julian
 *
 */
public class FindIndexOfClosestValueSorted {

	/**
	 * Finds the index with the value whom's distance 
	 * to the given {@code value} is the shortest
	 * @param array Sorted array to search the index in
	 * @param value value to which the index of the value with the shortest distance is searched to
	 * @return index with the shortest distance to {@code value}
	 */
	public int findClosestIndexInSortedArray(double[] array, double value) {
		//Set closest index to last index
		int closestIndex = array.length;
		
		//Set closest distance to maximum distance
		double closestDistance = Math.abs(array[array.length - 1] - array[0]);
		
		//find index with closest distance
		for(int i = 0; i < array.length; i++) {
			double distance = Math.abs(array[i] - value);
			
			if(distance < closestDistance) {
				closestIndex = i;
				closestDistance = distance;
			}
		}
		
		return closestIndex;
	}
}
