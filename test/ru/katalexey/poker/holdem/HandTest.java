package ru.katalexey.poker.holdem;

import ru.katalexey.poker.deck.*;
import junit.framework.TestCase;
import ru.katalexey.poker.holdem.holecards.HoleCards;
import static ru.katalexey.poker.holdem.holecards.HoleCards.*;

public class HandTest extends TestCase {

	public static void testHand() {
		Deck d = new Deck();
		HoleCards h = getHoleCards(d.getCard(1), d.getCard(14));
		assertEquals("[Ah Ad]", h.toString());
		h = getHoleCards(d.getCard(27), d.getCard(14));
		assertEquals("[Ad As]", h.toString());
		h = getHoleCards(d.getCard(2), d.getCard(15));
		assertEquals("[2h 2d]", h.toString());
		h = getHoleCards(d.getCard(13), d.getCard(51));
		assertEquals("[Kh Qc]", h.toString());
		h = getHoleCards(d.getCard(30), d.getCard(43));
		assertEquals("[4s 4c]", h.toString());
	}
}
