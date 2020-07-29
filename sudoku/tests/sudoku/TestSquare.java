package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSquare{

	@Test
	public void testEquals(){
		Square g1 = new Square(4);
		Square g2 = new Square();
		g2.setValue(4);
		assertEquals(g1, g2);
	}

	@Test
	public void testToString(){
		Square g1 = new Square(4);
		Square g2 = new Square();
		Square g3 = new Square();
		g3.setValue(3);
		assertEquals("[4]", g1.toString());
		assertEquals("()", g2.toString());
		assertEquals("(3)", g3.toString());
	}

	@Test
	public void testGetValue(){
		Square g1 = new Square(9);
		Square g2 = new Square();
		g2.setValue(4);
		assertEquals(9, g1.getValue());
		assertEquals(4, g2.getValue());
	}

	@Test
	public void testIsBlank(){
		Square g1 = new Square();
		Square g2 = new Square(4);
		assertTrue(g1.isBlank());
		g1.setValue(4);
		assertFalse(g1.isBlank());
		assertFalse(g2.isBlank());
	}

	@Test
	public void testResetValue(){
		Square g1 = new Square();
		Square g2 = new Square(4);
		assertEquals(4, g2.getValue());
		g1.setValue(8);
		assertEquals(8, g1.getValue());
		g1.resetValue();
		assertEquals(0, g1.getValue());
	}
}
