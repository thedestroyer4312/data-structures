package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestGrid{

	@Test
	public void testEquals(){
		Grid g1 = new Grid(4);
		Grid g2 = new Grid(4);
		assertEquals(g1, g2);
	}

	@Test
	public void testToString(){
		Grid g1 = new Grid(4);
		Grid g2 = new Grid();
		Grid g3 = new Grid();
		g3.setValue(3);
		assertEquals("[4]", g1.toString());
		assertEquals("()", g2.toString());
		assertEquals("(3)", g3.toString());
	}
	
	@Test
	public void testGetValue(){
		Grid g1 = new Grid(9);
		Grid g2 = new Grid();
		g2.setValue(4);
		assertEquals(9, g1.getValue());
		assertEquals(4, g2.getValue());
	}
}
