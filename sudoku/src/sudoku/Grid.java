package sudoku;

/**
 * Class functioning as grid blocks for the sudoku grid
 * @author Trevor Tsai
 * @version 5/13/2020
 */
public class Grid{

	private int val;
	public final boolean isFixed;

	/**
	 * Initializes a fixed grid space that cannot be modified
	 * @param val The number that will be in this grid
	 */
	public Grid(int val){
		this.val = val;
		isFixed = true;
	}

	/**
	 * Initializes a blank, mutable grid space (val is -1)
	 */
	public Grid(){
		val = -1;
		isFixed = false;
	}

	// Getter and setter methods
	/**
	 * Returns the integer value. If it is not assigned, should be -1
	 * @return val
	 */
	public int getValue(){
		return val;
	}

	public void setValue(int num){
		val = num;
	}

	// Equals and toString

	/**
	 * Returns true if the two grids have the same number, regardless if they are fixed or not
	 * @return true if same number, false otherwise
	 */
	@Override
	public boolean equals(Object obj){
		return obj instanceof Grid && ((Grid) obj).val == this.val;
	}
	
	/**
	 * @return [val] if locked, () if val is -1, (val) if val is not -1
	 */
	@Override
	public String toString(){
		if(isFixed){
			return "[" + val + "]";
		}else{
			return "(" + (val == -1 ? "" : "" + val) + ")";
		}
	}

}
