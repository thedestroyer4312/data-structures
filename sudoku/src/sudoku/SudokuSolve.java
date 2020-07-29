package sudoku;

import java.util.List;
import java.util.ArrayList;
import util.Boards;

/**
 * Solves sudoku boards using a simple backtracking algorithm
 * @author Trevor Tsai
 * @version 6/18/2020
 */
public class SudokuSolve{

	private Square[][] board;
	private List<Node> squares; // used to hold the squares in order of modification

	private class Node{

		private final int row;
		private final int col;
		private final Square square;

		private Node(int row, int col, Square s){
			this.square = s;
			this.row = row;
			this.col = col;
		}

	}

	// Construct with an input board
	/**
	 * Constructs a new SudokuSolve instance with a copy of an input board
	 * @param inputBoard An input board representing a partially solved Sudoku board
	 */
	public SudokuSolve(Square[][] inputBoard){
		if(!Boards.isValidBoard(inputBoard))
			throw new IllegalArgumentException("Please enter a board that represents a Sudoku board.");
		this.board = Boards.makeBoard(inputBoard);
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

		// NOTE: board is impossible to solve if no value works for the first square

		int i = 0;
		boolean backtrack = false;

		long iteration = 1;

		while(i < squares.size() && i >= 0){
			// initialize our working values
			Square s = squares.get(i).square;
			int row = squares.get(i).row;
			int col = squares.get(i).col;

			// check if the board is impossible to solve (backtracked to first Square and impossible)
			if(i == 0 && backtrack && s.getValue() == Square.MAXVALUE){
				solveable = false;
				break;
			}

			// check if this Square is blank; if so, then set it to its minimum value
			if(s.isBlank()){
				s.setValue(Square.MINVALUE);
			}

			/* If we just came back to this square (backtracked), then we know the value of this Square does not work
			 * for successive Squares. Increment the value of this Square by 1 unless it is max. Then, do not modify
			 * it. It will catch the maximum value and reset it later on. */
			if(backtrack){
				if(s.getValue() == Square.MAXVALUE){
					s.resetValue();
					i--;
					continue;
				}
				s.setValue(s.getValue() + 1);
			}

			// now, increment the Square value until we find one that works and reset the backtrack
			backtrack = false;
			while(!validate(row, col)){
				// when the value is 9, break and reset the Square -- it doesn't work, so we must backtrack
				if(s.getValue() == Square.MAXVALUE){
					s.resetValue();
					// go back to the previous square
					i--;
					backtrack = true;
					break;
				}
				s.setValue(s.getValue() + 1);
			}
			// if the value works, then proceed to the next square
			if(!backtrack){
				i++;
			}

			System.out.println("index " + i);
			// PRINT WORKING CONDITION OF THE BOARD (TESTING COMMENTS)
			System.out.println(Boards.toString(this.board));
			System.out.println("Iteration " + iteration + " -----------------------");
			// ----------------------------------------------
			iteration++;
		}
		return solveable;
	}

	/**
	 * Fills the squares instance variable with squares to be modified in order from left to right,
	 * then top to bottom. The solver will iterate one-by-one through this filled list to pick
	 * squares
	 */
	private void fillSquares(){
		this.squares = new ArrayList<Node>(board.length * board.length);
		for(int i = board.length - 1; i >= 0; i--){
			for(int j = board.length - 1; j >= 0; j--){
				Square g = board[i][j];
				// if it's empty, add it to the list
				if(g.isBlank()){
					squares.add(new Node(i, j, g));
				}
			}
		}
	}

	// METHODS TO CHECK BOARD CORRECTNESS

	/**
	 * Checks for board validity at a particular position (row,col) where both are indexed from 0
	 * Checks the row, column, and the square that the position is in
	 * @param row The row index of the position
	 * @param col The column index of the position
	 * @return false if the rules are violated, true otherwise
	 */
	public boolean validate(int row, int col){
		System.out.println("coordinates: (" + row + "," + col + ")");
		System.out.println("value: " + board[row][col].getValue());
		return checkRow(row) && checkColumn(col) && checkSquare(row, col);
	}

	/**
	 * Checks if a given row is valid (only has 1 or less of the numbers 1-9)
	 * @param row Index of the row (indexed from 0)
	 * @return true if the row has 1 or less of every number 1-9, false otherwise
	 */
	public boolean checkRow(int row){
		boolean[] nums = new boolean[board.length];
		boolean valid = true;
		for(int i = 0; i < board[row].length; i++){
			Square g = board[row][i];
			// if the Square isn't blank
			if(!g.isBlank()){
				int val = g.getValue();
				// if it already exists, break -- violation of sudoku rules. Otherwise, add it
				if(nums[val - 1]){
					valid = false;
					break;
				}else{
					nums[val - 1] = true;
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
		boolean[] nums = new boolean[board.length];
		boolean valid = true;
		for(int i = 0; i < board.length; i++){
			Square g = board[i][col];
			// if the Square isn't blank
			if(!g.isBlank()){
				int val = g.getValue();
				// if it already exists, break -- violation of sudoku rules. Otherwise, add it
				if(nums[val - 1]){
					valid = false;
					break;
				}else{
					nums[val - 1] = true;
				}
			}
		}
		return valid;
	}

	/**
	 * Checks if the square that a given Square at position (row,col) is in violates the sudoku rules
	 * @param row The row index (indexed from 0)
	 * @param col The column index (indexed from 0)
	 * @return true if the square adheres to the rules of sudoku, false if it violates the rules
	 */
	public boolean checkSquare(int row, int col){
		row = (row / 3) * 3;
		col = (row / 3) * 3;
		boolean[] nums = new boolean[board.length];
		boolean valid = true;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				Square g = board[row + i][col + j];
				// if the Square isn't blank
				if(!g.isBlank()){
					int val = g.getValue();
					// if it already exists, break -- violation of sudoku rules. Otherwise, add it
					if(nums[val - 1]){
						valid = false;
						break;
					}else{
						nums[val - 1] = true;
					}
				}
			}
		}
		return valid;
	}

	/**
	 * Returns a copy of the board instance variable in its current state
	 * @return a Square[][] representing the board as it is right now
	 */
	public Square[][] getBoard(){
		int length = board.length;
		Square[][] copy = new Square[length][length];
		for(int i = 0; i < copy.length; i++){
			for(int j = 0; j < copy.length; j++){
				copy[i][j] = new Square(this.board[i][j]);
			}
		}
		return copy;
	}
}
