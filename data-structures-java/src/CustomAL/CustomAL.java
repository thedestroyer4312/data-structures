package CustomAL;

/**
 * An implementation of ArrayList based loosely on the Java Collections implementation
 * This is my own implementation for my own sake; there would be no point of copying the code
 * since I could just import it if I feel like it
 * @author Trevor Tsai
 * @version 4/7/2020
 */
public class CustomAL<E> {

	private E[] arr;
	private int size;

	@SuppressWarnings("unchecked")
	public CustomAL(){
		arr = (E[]) new Object[10];
	}

	@SuppressWarnings("unchecked")
	public CustomAL(int capacity){
		arr = (E[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public CustomAL(E[] arr2){
		arr = (E[]) new Object[arr2.length];
		for(int i = 0; i < arr.length; i++){
			arr[i] = arr2[i];
		}
	}

	// Mutable methods
	/**
	 * Adds the specified object at the end of the list
	 * @param e The object
	 */
	public void add(E e){
		if(e == null)
			throw new NullPointerException();

		expandCapacity();
		arr[size] = e;
		size++;
	}

	/**
	 * Adds the object at the specified index
	 * @param index Specific index
	 * @param e Object to be added
	 * @throws ArrayIndexOutOfBoundsException if accessing illegal bound
	 */
	public void add(int index, E e){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		expandCapacity();
		for(int i = size + 1; i > index; i--){
			arr[i] = arr[i - 1];
		}
		arr[index] = e;
		size++;
	}

	/**
	 * Returns the value at a specified index
	 * @param index
	 * @return the value at the index
	 * @throws IndexOutOfBoundsException for an invalid index
	 */
	public E get(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		else
			return arr[index];
	}

	public boolean contains(E obj){
		boolean contains = false;
		for(E e : arr){
			if(e.equals(obj)){
				contains = true;
				break;
			}
		}
		return contains;
	}

	/**
	 * Returns a copy of the backing array, trimmed to size
	 * @return a copy of the array and its contents, trimmed to size
	 */
	@SuppressWarnings("unchecked")
	public E[] toArray(){
		E[] output = (E[]) new Object[size];
		for(int i = 0; i < size; i++){
			output[i] = arr[i];
		}
		return output;
	}

	/**
	 * Clears the list's content
	 */
	public void clear(){
		for(int i = 0; i < size; i++){
			arr[i] = null;
		}
		size = 0;
	}

	/**
	 * Trims the backing array to size by allocating a new array and copying the elements
	 */
	@SuppressWarnings("unchecked")
	public void trimToSize(){
		E[] temp = (E[]) new Object[size];
		for(int i = 0; i < size; i++){
			temp[i] = arr[i];
		}
		arr = temp;
	}

	// Getter methods
	
	/**
	 * Returns the size of the list
	 * @return size
	 */
	public int size(){
		return size;
	}

	/**
	 * Returns if the list empty
	 * @return true if size = 0, false otherwise
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	@Override
	public boolean equals(Object obj){
		boolean equals = false;
		if(obj instanceof CustomAL<?>){
			CustomAL<?> list2 = (CustomAL<?>) obj;
			if(this.size == list2.size){
				for(int i = 0; i < size; i++){
					if(!this.arr[i].equals(list2.arr[i])){
						break;
					}
				}
				equals = true;
			}
		}
		return equals;
	}

	@Override
	public String toString(){
		String output = "";
		for(int i = 0; i < size; i++){
			output += arr[i].toString();
			output += " ";
		}
		return output;
	}
	// Private methods

	@SuppressWarnings("unchecked")
	private void expandCapacity(){
		if(size + 1 > arr.length){
			E[] temp = (E[]) new Object[arr.length * 2];
			for(int i = 0; i < arr.length; i++){
				temp[i] = arr[i];
			}
			arr = temp;
		}
	}
}
