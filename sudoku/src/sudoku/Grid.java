package sudoku;

/**
 * Class functioning as grid blocks for the sudoku grid
 * @author Trevor Tsai
 * @version 5/13/2020
 */
public class Grid{

	public static final int DEFAULTVALUE = 0;

	private int val;
	public final boolean isFixed;

	// Constructors

	/**
	 * Initializes a blank, mutable grid space
	 */
	public Grid(){
		isFixed = false;
		val = DEFAULTVALUE;
	}

	/**
	 * Initializes a fixed grid space that cannot be modified
	 * @param val The number that will be in this grid
	 */
	public Grid(int val){
		this.val = val;
		isFixed = true;
	}

	/**
	 * Constructs a new Grid that is a copy of the input
	 * @param other Another Grid that is to be copied
	 */
	public Grid(Grid other){
		this.val = other.val;
		this.isFixed = other.isFixed;
	}

	// Sudoku board constructor
	// TODO: implement the method
	public static Grid[][] makeBoard(String input){
		return null;
	}

	// Getter and setter methods

	/**
	 * Returns the integer value. If it is not assigned, should be the default value
	 * @return val
	 */
	public int getValue(){
		return val;
	}

	/**
	 * Sets the integer value of the Grid. Will not do anything if the grid is fixed
	 * @param num an input number assumed to be 1-9
	 * @return true if it was changed, false if not (fixed Grid)
	 */
	public boolean setValue(int num){
		if(!isFixed){
			this.val = num;
		}
		return !isFixed;
	}

	/**
	 * Checks if the Grid is blank, not taking into account whether it is mutable or fixed
	 * @return true if the Grid value is not the default, false otherwise
	 */
	public boolean isBlank(){
		return val == DEFAULTVALUE;
	}

	/**
	 * Resets the Grid's value to the default value if it is mutable. If it is fixed, does nothing
	 * @return true if the value was changed, false otherwise
	 */
	public boolean resetValue(){
		if(!isFixed){
			val = DEFAULTVALUE;
		}
		return !isFixed;
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
	 * @return [val] if locked, () if val is default, (val) if val is not default
	 */
	@Override
	public String toString(){
		if(isFixed){
			return "[" + val + "]";
		}else{
			return "(" + (val == DEFAULTVALUE ? "" : "" + val) + ")";
		}
	}

}
