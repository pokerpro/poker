package ru.katalexey.poker.deck;

import junit.framework.TestCase;

public class DeckTest extends TestCase {

	public static void testCardsInDeck() {
		Deck d = new Deck();
		assertEquals("Ah", d.getCard(1).toString());
		assertEquals("Ad", d.getCard(14).toString());
		assertEquals("As", d.getCard(27).toString());
		assertEquals("Ac", d.getCard(40).toString());
		assertEquals("2h", d.getCard(2).toString());
		assertEquals("3d", d.getCard(16).toString());
		assertEquals("4s", d.getCard(30).toString());
		assertEquals("5c", d.getCard(44).toString());
		assertEquals("3h", d.getCard(3).toString());
		assertEquals("5d", d.getCard(18).toString());
		assertEquals("7s", d.getCard(33).toString());
		assertEquals("9c", d.getCard(48).toString());
		assertEquals("4h", d.getCard(4).toString());
		assertEquals("7d", d.getCard(20).toString());
		assertEquals("Ts", d.getCard(36).toString());
		assertEquals("Kc", d.getCard(52).toString());
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
