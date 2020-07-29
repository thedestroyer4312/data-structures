package sudoku;

/**
 * Class functioning as Square blocks for the sudoku Square
 * @author Trevor Tsai
 * @version 6/18/2020
 */
public class Square{

	public static final int DEFAULTVALUE = 0;
	public static final int MINVALUE = 1;
	public static final int MAXVALUE = 9;

	private int val;
	public final boolean isFixed;

	// Constructors

	/**
	 * Initializes a blank, mutable Square space with maxValue 9
	 */
	public Square(){
		isFixed = false;
		val = DEFAULTVALUE;
	}

	/**
	 * Initializes a fixed Square space that cannot be modified
	 * @param val The number that will be in this Square
	 */
	public Square(int val){
		this.val = val;
		isFixed = true;
	}

	/**
	 * Constructs a new Square that is a copy of the input
	 * @param other Another Square that is to be copied
	 */
	public Square(Square other){
		this.val = other.val;
		this.isFixed = other.isFixed;
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
	 * Sets the integer value of the Square. Will not do anything if the Square is fixed
	 * @param num an input number assumed to be 1-9
	 */
	public void setValue(int num){
		if(!isFixed){
			this.val = num;
		}
	}

	/**
	 * Checks if the Square is blank, not taking into account whether it is mutable or fixed
	 * @return true if the Square value is not the default, false otherwise
	 */
	public boolean isBlank(){
		return val == DEFAULTVALUE;
	}

	/**
	 * Resets the Square's value to the default value if it is mutable. If it is fixed, does nothing
	 */
	public void resetValue(){
		if(!isFixed){
			val = DEFAULTVALUE;
		}
	}

	// Equals and toString

	/**
	 * Returns true if the two Squares have the same number, regardless if they are fixed or not
	 * @return true if same number, false otherwise
	 */
	@Override
	public boolean equals(Object obj){
		return obj instanceof Square && ((Square) obj).val == this.val;
	}

	/**
	 * @return [val] if fixed, () if blank, (val) if not blank
	 */
	@Override
	public String toString(){
		if(isFixed){
			return "[" + val + "]";
		}else{
			if(isBlank()){
				return " * ";
			}else{
				return " " + val + " ";
			}
		}
	}

}
