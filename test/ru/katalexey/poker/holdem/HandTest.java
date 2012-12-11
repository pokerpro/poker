package ru.katalexey.poker.holdem;

import ru.katalexey.poker.deck.*;
import junit.framework.TestCase;
import ru.katalexey.poker.holdem.holecards.HoleCards;
import static ru.katalexey.poker.holdem.holecards.HoleCards.*;

public class HandTest extends TestCase {

	public static void testHand() {
		Deck d = new Deck();
		HoleCards h = getHoleCards(d.getCard(1), d.getCard(14));
		assertEquals("[2c 2d]", h.toString());
		h = getHoleCards(d.getCard(27), d.getCard(14));
		assertEquals("[2d 2h]", h.toString());
		h = getHoleCards(d.getCard(2), d.getCard(15));
		assertEquals("[3c 3d]", h.toString());
		h = getHoleCards(d.getCard(13), d.getCard(51));
		assertEquals("[Ac Ks]", h.toString());
		h = getHoleCards(d.getCard(30), d.getCard(43));
		assertEquals("[5h 5s]", h.toString());
	}
}
