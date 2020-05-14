package sudoku;

public class Solve{

	/**
	 * Takes in a partially completed sudoku board and solves it using backtracking
	 * @param board A Grid[][] representing the sudoku board
	 */
	public static void solve(Grid[][] board){

	}

	/**
	 * Returns a string representation of a board
	 * @param board A Grid[][] representing the sudoku board
	 * @return A string separated by spaces to appear like a grid, denoting locked and fixed numbers
	 * as well as empty numbers
	 */
	public static String GridToString(Grid[][] board){
		String output = "";
		for(Grid[] row : board){
			for(Grid g : row){
				output += g + " ";
			}
			output = output.trim() + "\n";
		}
		return output.trim();
	}

	/**
	 * Checks if all the squares on the board are filled in (does not check correctness)
	 * @param board A Grid[][] representing the sudoku board
	 * @return true if all squares have been filled, false otherwise
	 */
	public static boolean isFull(Grid[][] board){
		for(Grid[] gridArr : board){
			for(Grid g : gridArr){
				if(!g.isFixed && g.getValue() == -1)
					return false;
			}
		}
		return true;
	}
	// *** METHODS TO CHECK BOARD CORRECTNESS ***

	/**
	 * Checks if a sudoku board follows the rule for number uniqueness. Empty squares are ignored
	 * @param board A Grid[][] representing the sudoku board
	 * @return true if the rows, columns, and square follow number uniqueness
	 */
	public static boolean checkBoard(Grid[][] board){
		return checkRows(board) && checkCols(board) && checkSquares(board);
	}

	/**
	 * Checks if every row in the board has 1 or less of the numbers 1-9
	 * @param board A Grid[][] representing the sudoku board
	 * @return true if all rows have 1 or less of the numbers 1-9
	 */
	private static boolean checkRows(Grid[][] board){
		boolean[] nums = new boolean[10];
		for(Grid[] row : board){
			for(Grid g : row){
				if(g.getValue() != -1){
					// check if the corresponding index is true (already counted)
					if(nums[g.getValue()]){
						return false;
					}else{
						nums[g.getValue()] = true;
					}
				}
			}
			// zero out the nums array for each row
			for(int i = 0; i < nums.length; i++){
				nums[i] = false;
			}
		}
		return true;
	}

	/**
	 * Checks if every column in the board has 1 or less of the numbers 1-9
	 * @param board A Grid[][] representing the sudoku board
	 * @return true if all columns have 1 or less of the numbers 1-9
	 */
	private static boolean checkCols(Grid[][] board){
		boolean[] nums = new boolean[10];
		for(int col = 0; col < board.length; col++){
			for(int row = 0; row < board.length; row++){
				Grid g = board[row][col];
				if(g.getValue() != -1){
					// check if the corresponding index is true (already counted)
					if(nums[g.getValue()]){
						return false;
					}else{
						nums[g.getValue()] = true;
					}
				}
			}
			// zero out the nums array for each column
			for(int i = 0; i < nums.length; i++){
				nums[i] = false;
			}
		}
		return true;
	}

	/**
	 * Checks if every 3x3 square in the board has 1 or less of the numbers 1-9
	 * @param board A Grid[][] representing the sudoku board
	 * @return true if all 3x3 squares have 1 or less of the numbers 1-9
	 */
	private static boolean checkSquares(Grid[][] board){
		boolean[] nums = new boolean[10];
		// since we have to iterate in squares of 3x3, there are 3 tiers vertically and 3
		// horizontally

		// this is the height increment
		for(int height = 0; height < board.length; height += 3){
			// this is the width increment
			for(int width = 0; width < board.length; width += 3){
				// this is where each square is analyzed
				for(int row = 0; row < 3; row++){
					for(int col = 0; col < 3; col++){
						Grid g = board[height + row][width + col];
						if(g.getValue() != -1){
							// check if corresponding index is already true
							if(nums[g.getValue()]){
								return false;
							}else{
								nums[g.getValue()] = true;
							}
						}
					}
				}
				// nums is zeroed after each square
				for(int i = 0; i < nums.length; i++){
					nums[i] = false;
				}
			}
		}
		return true;
	}
}
