package sudoku;

import java.util.List;
import java.util.ArrayList;
// TODO: flesh out solving algorithm and write unit test cases, develop way to easily make sudoku boards
public class SudokuSolve{

	private Grid[][] board;
	private List<Node> squares; // used to hold the squares in order of modification

	private class Node{

		final int row;
		final int col;
		final Grid grid;

		private Node(int row, int col, Grid g){
			this.grid = g;
			this.row = row;
			this.col = col;
		}

	}

	// Default null value constructor
	public SudokuSolve(){
		this.board = null;
		this.squares = null;
	}

	// Construct with an input board
	/**
	 * Constructs a new SudokuSolve instance with an input board
	 * @param inputBoard An input board representing a partially solved Sudoku board
	 */
	public SudokuSolve(Grid[][] inputBoard){
		if(!isValidBoard(inputBoard))
			throw new IllegalArgumentException("Please enter a board that represents a Sudoku board.");
		this.board = inputBoard;
		squares = null;
	}

	// Methods related to solving the board

	/**
	 * Solves the sudoku board by backtracking
	 * @return false if the board is impossible to solve, true otherwise
	 */
	public boolean solve(){
		boolean solveable = true;
		fillSquares();

		// board is impossible to solve if no value works for the first square
		for(int i = 0; i < squares.size();){
			Grid g = squares.get(i).grid;
			int row = squares.get(i).row;
			int col = squares.get(i).col;
			// check if this is impossible
			if(i == 0 && g.getValue() == 9 && !g.isFixed){
				solveable = false;
				break;
			}

			// check if this grid is blank (value = -1), then set it to 1
			if(g.getValue() == -1){
				g.setValue(1);
			}

			// now, we modify the square and test if it works
			boolean reset = false;
			while(!validate(row, col)){
				// when the value is 9, break and reset the grid
				if(g.getValue() == 9){
					g.setValue(-1);
					// go back to the previous square
					i--;
					reset = true;
					break;
				}
				g.setValue(g.getValue() + 1);
				if(!reset){
					i++;
				}
			}
		}
		return solveable;
	}

	/**
	 * Fills the squares instance variable with squares to be modified in order from left to right,
	 * then top to bottom. The solver will iterate one-by-one through this filled list to pick
	 * squares
	 */
	private void fillSquares(){
		this.squares = new ArrayList<Node>(81);
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				Grid g = board[i][j];
				// if it's not empty, add it to the list
				if(g.getValue() != -1){
					squares.add(new Node(i, j, g));
				}
			}
		}
	}

	// METHODS TO CHECK BOARD CORRECTNESS

	/**
	 * Checks for board validiy at a particular position (row,col) where both are indexed from 0
	 * Checks the row, column, and the square that the position is in
	 * @param row The row index of the position
	 * @param col The column index of the position
	 * @return false if the rules are violated, true otherwise
	 */
	public boolean validate(int row, int col){
		int gridValue = board[row][col].getValue();
		boolean validValue = (gridValue >= 1 && gridValue <= 9) || gridValue == -1;
		return validValue && checkRow(row) && checkColumn(col) && checkSquare(row, col);
	}

	/**
	 * Checks if a given row is valid (only has 1 or less of the numbers 1-9)
	 * @param row Index of the row (indexed from 0)
	 * @return true if the row has 1 or less of every number 1-9, false otherwise
	 */
	public boolean checkRow(int row){
		boolean[] nums = new boolean[10];
		boolean valid = true;
		for(int i = 0; i < board[row].length; i++){
			Grid g = board[row][i];
			int val = g.getValue();
			// if the grid isn't blank
			if(val != -1){
				// if it already exists, break -- violation of sudoku rules. Otherwise, add it
				if(nums[val]){
					valid = false;
					break;
				}else{
					nums[val] = true;
				}
			}
		}
		return valid;
	}

	/**
	 * Checks if a given column is valid (only has 1 or less of the numbers 1-9)
	 * @param col Index of the column (indexed from 0)
	 * @return true if the column has 1 or less of every number 1-9, false otherwise
	 */
	public boolean checkColumn(int col){
		boolean[] nums = new boolean[10];
		boolean valid = true;
		for(int i = 0; i < board.length; i++){
			Grid g = board[i][col];
			int val = g.getValue();
			// if the grid isn't blank
			if(val != -1){
				// if it already exists, break -- violation of sudoku rules. Otherwise, add it
				if(nums[val]){
					valid = false;
					break;
				}else{
					nums[val] = true;
				}
			}
		}
		return valid;
	}

	/**
	 * Checks if the square that a given Grid at position (row,col) is in violates the sudoku rules
	 * @param row The row index (indexed from 0)
	 * @param col The column index (indexed from 0)
	 * @return true if the square adheres to the rules of sudoku, false if it violates the rules
	 */
	public boolean checkSquare(int row, int col){
		row /= 3;
		col /= 3;
		boolean[] nums = new boolean[10];
		boolean valid = true;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				Grid g = board[row + i][col + j];
				int val = g.getValue();
				// check if it exists
				if(val != -1){
					// if it already exists, break -- violation of sudoku rules. Otherwise, add it
					if(nums[val]){
						valid = false;
						break;
					}else{
						nums[val] = true;
					}
				}
			}
		}
		return valid;
	}

	// UTILITY METHODS

	/**
	 * Returns a String representation of the board
	 * @param board An input Grid[][] representing a sudoku board
	 * @return A string separated by spaces to appear like a grid, denoting locked and fixed numbers
	 * as well as empty numbers
	 */
	public static String toString(Grid[][] board){
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
	 * Checks if a Grid[][] representing a sudoku board is legitimately constructed (not if it is solveable)
	 * Verifies if the board is of the right dimensions and if every Grid is initialized (i.e. not null)
	 * @param board An input Grid[][] representing a sudoku board
	 * @return true if the board is legitimately constructed, false otherwise
	 */
	public static boolean isValidBoard(Grid[][] board){
		if(board == null)
			return false;
		// first, check that the board dimensions are correct
		boolean validDimensions = board.length == 9;
		for(Grid[] row : board){
			if(row == null || row.length != 9){
				validDimensions = false;
				break;
			}
		}
		boolean noNullGrids = !validDimensions ? false : true;
		if(noNullGrids){
			for(Grid[] row : board){
				for(Grid g : row){
					if(g == null){
						noNullGrids = false;
						break;
					}
				}
			}
		}
		return validDimensions && noNullGrids;
	}

	/**
	 * Returns a copy of the board instance variable in its current state
	 * @return a Grid[][] representing the board as it is right now
	 */
	public Grid[][] getBoard(){
		Grid[][] copy = new Grid[9][9];
		for(int i = 0; i < copy.length; i++){
			copy[i] = new Grid[9];
		}
		for(int i = 0; i < copy.length; i++){
			for(int j = 0; j < copy.length; j++){
				copy[i][j] = new Grid(this.board[i][j]);
			}
		}
		return copy;
	}

	// TESTING METHODS

	/**
	 * Returns the squares instance variable in its current state
	 * @return the squares instance variable that represents the squares visited
	 */
	public List<Node> getSquares(){
		return squares;
	}
}
