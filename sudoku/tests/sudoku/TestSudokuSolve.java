package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import util.Boards;
import sudoku.SudokuSolve;

// NOTE: https://www.puzzles.ca/sudoku/ is a great place to find boards and solutions
class TestSudokuSolve{

	public static final Square[][] easyBoard = Boards.makeBoard(new int[][]{{1, 3, 0, 2, 0, 0, 7, 4, 0},
			{0, 2, 5, 0, 1, 0, 0, 0, 0},
			{4, 8, 0, 0, 6, 0, 0, 5, 0},
			{0, 0, 0, 7, 8, 0, 2, 1, 0},
			{5, 0, 0, 0, 9, 0, 3, 7, 0},
			{9, 0, 0, 0, 3, 0, 0, 0, 5},
			{0, 4, 0, 0, 0, 6, 8, 9, 0},
			{0, 5, 3, 0, 0, 1, 4, 0, 0},
			{6, 0, 0, 0, 0, 0, 0, 0, 0}});
	public static final Square[][] easyBoardSolution = Boards.makeBoard(new int[][]{{1, 3, 6, 2, 5, 9, 7, 4, 8},
			{7, 2, 5, 4, 1, 8, 9, 3, 6},
			{4, 8, 9, 3, 6, 7, 1, 5, 2},
			{3, 6, 4, 7, 8, 5, 2, 1, 9},
			{5, 1, 8, 6, 9, 2, 3, 7, 4},
			{9, 7, 2, 1, 3, 4, 6, 8, 5},
			{2, 4, 1, 5, 7, 6, 8, 9, 3},
			{8, 5, 3, 9, 2, 1, 4, 6, 7},
			{6, 9, 7, 8, 4, 3, 5, 2, 1}});
	public static final Square[][] invalidBoard = Boards.makeBoard();
	{
		invalidBoard[0][0] = new Square(1);
		invalidBoard[1][1] = new Square(1);
		invalidBoard[2][0] = new Square(1);
		invalidBoard[0][2] = new Square(1);
	}

	@Test
	public void testCheckRow(){
		SudokuSolve instance1 = new SudokuSolve(easyBoard);
		for(int i = 0; i < easyBoard.length; i++){
			assertTrue(instance1.checkRow(i));
		}
		SudokuSolve instance2 = new SudokuSolve(invalidBoard);
		assertFalse(instance2.checkRow(0));
	}

	@Test
	public void testCheckCol(){
		SudokuSolve instance1 = new SudokuSolve(easyBoard);
		for(int i = 0; i < easyBoard.length; i++){
			assertTrue(instance1.checkColumn(i));
		}
		SudokuSolve instance2 = new SudokuSolve(invalidBoard);
		assertFalse(instance2.checkColumn(0));
	}

	@Test
	public void testCheckSquare(){
		SudokuSolve instance1 = new SudokuSolve(easyBoard);
		for(int i = 0; i < easyBoard.length; i++){
			for(int j = 0; j < easyBoard.length; j++){
				assertTrue(instance1.checkSquare(i, j));
			}
		}
		SudokuSolve instance2 = new SudokuSolve(invalidBoard);
		assertFalse(instance2.checkSquare(0, 0)); 
	}

	@Test
	public void testSolve(){
		SudokuSolve instance1 = new SudokuSolve(easyBoard);
		instance1.solve();
		assertTrue(Boards.equals(easyBoardSolution, instance1.getBoard()));
	}
}
