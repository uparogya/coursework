/**
 * Unit Tests for testing implementation of ThreeDimensionalPoint
 * 
 * @author Greg Gagne
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class TestThreeDimensionalPoint {
	
	@Test
	public void testGetters() {
		ThreeDimensionalPoint pt1 = new ThreeDimensionalPoint(5,10,15);
		
		assertEquals(5.0, pt1.getX(), 0.01);
		assertEquals(10.0, pt1.getY(), 0.01);
		assertEquals(15.0, pt1.getZ(), 0.01);
	}
	
	@Test
	public void testShifters() {
		ThreeDimensionalPoint pt1 = new ThreeDimensionalPoint(5,10,15);
		
		assertEquals(4.0, pt1.shiftX(-1),0.01);
		assertEquals(9.0, pt1.shiftY(-1),0.01);
		assertEquals(14.0, pt1.shiftZ(-1),0.01);
	}

	@Test
	public void testRotations() {
		ThreeDimensionalPoint pt1 = new ThreeDimensionalPoint(5,10,15);
		
		pt1.rotateX(-1);
		
		assertEquals(5.0,pt1.getX(),0.01);
		assertEquals(18.025, pt1.getY(), 0.01);
		assertEquals(-0.31, pt1.getZ(), 0.01);
		
		ThreeDimensionalPoint pt2 = new ThreeDimensionalPoint(5,10,15);
		
		pt2.rotateY(-1);
		
		assertEquals(-9.92, pt2.getX(), 0.01);
		assertEquals(10.0, pt2.getY(), 0.01);
		assertEquals(12.31, pt2.getZ(), 0.01);
		
		ThreeDimensionalPoint pt3 = new ThreeDimensionalPoint(5,10,15);
		
		pt3.rotateZ(-1);
		
		assertEquals(11.11, pt3.getX(), 0.01);
		assertEquals(1.19, pt3.getY(), 0.01);
		assertEquals(15.0, pt3.getZ(), 0.01);
	}

}
