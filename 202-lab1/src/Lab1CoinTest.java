import static org.junit.Assert.*;

import org.junit.Test;

public class Lab1CoinTest {

	@Test 
	/** tests constructor, isHeads, isTails */
	public void testConstructor() {
		GenericCoin coin1 = new GenericCoin();
		assertEquals("default constructor should set GenericCoin to heads",
				true, coin1.isHeads());	
		assertEquals("default constructor should set GenericCoin to heads",
				false, coin1.isTails());		
	}

	@Test 
	/** tests constructor, isHeads, setTails */
	public void testSetTails() {
		GenericCoin coin1 = new GenericCoin();
		assertEquals(true, coin1.isHeads());
		GenericCoin coin2 = new GenericCoin();
		assertEquals(true, coin2.isHeads());
		coin2.setToTails();

		// coin1 should still be heads
		// coin2 should now be tails
		assertEquals(true, coin1.isHeads());
		assertEquals(false, coin2.isHeads());

		// check isTails method
		assertEquals(false, coin1.isTails());
		assertEquals(true, coin2.isTails());
	}

	@Test 
	/** test mutators and accessors */
	public void testMutators() {
		GenericCoin coin = new GenericCoin();
		coin.setToHeads();
		assertEquals(true, coin.isHeads());
		assertEquals(false, coin.isTails());

		coin.setToTails();
		assertEquals(false, coin.isHeads());
		assertEquals(true, coin.isTails());

		coin.setToHeads();
		assertEquals(true, coin.isHeads());
		assertEquals(false, coin.isTails());

		coin = new GenericCoin();
		coin.setToTails();
		assertEquals(false, coin.isHeads());
		assertEquals(true, coin.isTails());
	}

	@Test
	/** test toss - could fail when planets align, but not likely */
	public void testToss() {
		GenericCoin coin = new GenericCoin();
		int heads;
		boolean pass = false;
		// five tries to pass
		for (int i=0; i<5 && !pass; i++){
			heads = 0;
			for (int j=0; j<100; j++) {
				coin.toss();
				if (coin.isHeads())
					heads++;
			} // end for j
			if (40 <= heads && heads <= 60)
				pass = true;
		} // end for i
		assertEquals("flipping a coin 100 times, it should be head approximately half the time",
				true, pass);
	}

	@Test
	/** test toString, mutators */
	public void testToString() {
		GenericCoin coin = new GenericCoin();
		coin.setToHeads();
		assertEquals("heads", coin.toString().toLowerCase());
		coin.setToTails();
		assertEquals("tails", coin.toString().toLowerCase());
	}

}
