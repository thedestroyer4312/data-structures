package util;

import sudoku.Square;

/**
 * A utility class to create, modify, and test sudoku boards
 * @author Trevor Tsai
 * @version 6/18/20
 */
public class Boards{

	/**
	 * Constructs a sudoku board given a 2d array of integers if it is valid
	 * If the resulting board is not valid, returns null
	 * @param nums A 2d array of integers
	 * @return a corresponding sudoku board if the 2d number array is valid, null otherwise
	 */
	public static Square[][] makeBoard(int[][] nums){
		Square[][] board = new Square[nums.length][nums[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(nums[i][j] > Square.DEFAULTVALUE){
					board[i][j] = new Square(nums[i][j]);
				}else{
					board[i][j] = new Square();
				}
			}
		}
		return isValidBoard(board) ? board : null;
	}

	/**
	 * Constructs a sudoku board from a preexisting sudoku board if it is valid
	 * If the input board is not valid, returns null
	 * @param other A preexisting sudoku board
	 * @return a copy of the input sudoku board if it is valid, null otherwise
	 */
	public static Square[][] makeBoard(Square[][] other){
		if(!isValidBoard(other)){
			return null;
		}else{
			Square[][] newBoard = new Square[other.length][other.length];
			for(int i = 0; i < newBoard.length; i++){
				for(int j = 0; j < newBoard.length; j++){
					newBoard[i][j] = new Square(other[i][j]);
				}
			}
			return newBoard;
		}
	}

	/**
	 * Constructs a blank sudoku board
	 * @return a blank sudoku board with each Square blank
	 */
	public static Square[][] makeBoard(){
		Square[][] board = new Square[9][9];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				board[i][j] = new Square();
			}
		}
		return board;
	}

	/**
	 * Returns a String representation of the board
	 * @param board An input Square[][] representing a sudoku board
	 * @return A string separated by spaces to appear like a Square, denoting locked and fixed numbers
	 * as well as empty numbers
	 */
	public static String toString(Square[][] board){
		String output = "";
		for(Square[] row : board){
			for(Square g : row){
				output += g + " ";
			}
			output = output.trim() + "\n";
		}
		return output.trim();
	}

	/**
	 * Checks if an arbitrary number of boards are equal - that is, if they have the same Squares at the same position
	 * If one of the boards is invalid, return false regardless
	 * @param boards A number of Square[][] that represent sudoku boards
	 * @return true if they are the same, false if not the same or if invalid
	 */
	public static boolean equals(Square[][]... boards){
		for(Square[][] board : boards){
			if(!isValidBoard(board))
				return false;
		}

		// our reference board will be the first board. if the boards are all equal to 1, then they are all equal
		Square[][] board1 = boards[0];
		for(int i = 1; i < boards.length; i++){
			Square[][] currBoard = boards[i];
			// now, we have to compare the squares one by one
			for(int row = 0; row < board1.length; row++){
				for(int col = 0; col < board1.length; col++){
					if(!board1[row][col].equals(currBoard[row][col])){
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Checks if a Square[][] representing a sudoku board is legitimately constructed (not if it is solveable)
	 * Verifies if the board is of the right dimensions and if every Square is initialized (i.e. not null)
	 * @param board An input Square[][] representing a sudoku board
	 * @return true if the board is legitimately constructed, false otherwise
	 */
	public static boolean isValidBoard(Square[][] board){
		if(board == null)
			return false;
		// first, check that the board dimensions are correct
		for(Square[] row : board){
			if(row == null || row.length != Square.MAXVALUE){
				return false;
			}
		}
		// then, check that each Square is initialized (i.e. not null) and of the correct values
		for(Square[] row : board){
			for(Square s : row){
				int val = s.getValue();
				boolean validVal = s.isBlank() || (val <= Square.MAXVALUE && val >= Square.MINVALUE);
				if(s == null || !validVal){
					return false;
				}
			}
		}
		return true;
	}

}
