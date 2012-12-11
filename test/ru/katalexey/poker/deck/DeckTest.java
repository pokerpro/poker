package ru.katalexey.poker.deck;

import junit.framework.TestCase;

public class DeckTest extends TestCase {

	public static void testCardsInDeck() {
		Deck d = new Deck();
		assertEquals("2c", d.getCard(1).toString());
		assertEquals("2d", d.getCard(14).toString());
		assertEquals("2h", d.getCard(27).toString());
		assertEquals("2s", d.getCard(40).toString());
		assertEquals("3c", d.getCard(2).toString());
		assertEquals("4d", d.getCard(16).toString());
		assertEquals("5h", d.getCard(30).toString());
		assertEquals("6s", d.getCard(44).toString());
		assertEquals("4c", d.getCard(3).toString());
		assertEquals("6d", d.getCard(18).toString());
		assertEquals("8h", d.getCard(33).toString());
		assertEquals("Ts", d.getCard(48).toString());
		assertEquals("5c", d.getCard(4).toString());
		assertEquals("8d", d.getCard(20).toString());
		assertEquals("Jh", d.getCard(36).toString());
		assertEquals("As", d.getCard(52).toString());
	}
	
	// if failed - try again
	public static void testShuffle() {
		int sim = 520000;
		double delta = 0.05;
		Deck d = new Deck();
		Card Ah = d.getNext();
		int c1 = 0;
		int c19 = 0;
		int c37 = 0;
		int c52 = 0;
		for (int i = 0; i < sim; ++i) {
			d.shuffle();
			if (Ah.equals(d.getCard(1))) c1++;
			if (Ah.equals(d.getCard(19))) c19++;
			if (Ah.equals(d.getCard(37))) c37++;
			if (Ah.equals(d.getCard(52))) c52++;
		}
		System.out.println((c1 * 52.0) / sim + " " + (c19 * 52.0) / sim + " " 
						+ (c37 * 52.0) / sim + " " + (c52 * 52.0) / sim);
		assertEquals(1.0, c1 * 52.0 / sim, delta);
		assertEquals(1.0, c19 * 52.0 / sim, delta);
		assertEquals(1.0, c37 * 52.0 / sim, delta);
		assertEquals(1.0, c52 * 52.0 / sim, delta);
	}
}
