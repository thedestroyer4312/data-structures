package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sudoku.Square;

class TestBoards{

	@Test
	public void testEquals1(){
		Square[][] board1 = Boards.makeBoard();
		Square[][] board2 = Boards.makeBoard(board1);
		board2[2][2] = new Square(1);
		assertTrue(Boards.equals(board1, Boards.makeBoard(board1)));
		assertFalse(Boards.equals(board1, board2));
		assertFalse(Boards.equals(board1, null));
		assertTrue(Boards.equals(board1, Boards.makeBoard(board1), Boards.makeBoard()));
	}

	// Tests if a blank board can be correctly constructed
	@Test
	public void testMakeBoard1(){
		int[][] blankNums = new int[9][9];
		Square[][] blankBoard1 = Boards.makeBoard(blankNums);
		Square[][] blankBoard2 = Boards.makeBoard();
		Square[][] blankBoard3 = Boards.makeBoard(blankBoard1);

		Square[][] reference = new Square[9][9];
		// initialize all Squares in reference board to new Squares (blank)
		for(int i = 0; i < reference.length; i++){
			for(int j = 0; j < reference.length; j++){
				reference[i][j] = new Square();
			}
		}

		for(int i = 0; i < reference.length; i++){
			assertArrayEquals(reference[i], blankBoard1[i]);
			assertArrayEquals(reference[i], blankBoard2[i]);
			assertArrayEquals(reference[i], blankBoard3[i]);
		}
	}

	// Tests if a diagonal board (numbers increasing along diagonal) can be correctly constructed
	@Test
	public void testMakeBoard2(){
		int[][] diagonal = new int[9][9];
		for(int i = 0; i < diagonal.length; i++){
			diagonal[i][i] = i;
		}
		Square[][] diagonalBoard1 = Boards.makeBoard(diagonal);
		Square[][] diagonalBoard2 = Boards.makeBoard(diagonalBoard1);

		Square[][] reference = new Square[9][9];
		// initialize all Squares in reference board to new Squares except for diagonals
		for(int i = 0; i < reference.length; i++){
			for(int j = 0; j < reference.length; j++){
				if(i == j){
					reference[i][j] = new Square(i);
				}else{
					reference[i][j] = new Square();
				}
			}
		}

		for(int i = 0; i < reference.length; i++){
			assertArrayEquals(reference[i], diagonalBoard1[i]);
			assertArrayEquals(reference[i], diagonalBoard2[i]);
		}
	}

}
