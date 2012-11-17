package ru.katalexey.poker.holdem;

import ru.katalexey.poker.deck.Card;
import junit.framework.TestCase;
import ru.katalexey.poker.holdem.combination.Combination;

import static ru.katalexey.poker.deck.Rank.*;
import static ru.katalexey.poker.deck.Card.*;
import static ru.katalexey.poker.holdem.combination.Combination.*;
import static ru.katalexey.poker.holdem.combination.CombinationType.*;

public class CombinationTest extends TestCase {

	Combination strFlush = evaluateCombination(new Card[] {hA, hJ, hQ, hT, hK});
	Combination flush = evaluateCombination(new Card[] {h3, hK, h9, hJ, h6});
	Combination straight = evaluateCombination(new Card[] {d4, s8, h6, c5, h7});
	Combination quad = evaluateCombination(new Card[] {h7, c7, sJ, d7, s7});
	Combination fullHouseQueenHigh = evaluateCombination(new Card[] {hK, cQ, hQ, cK, dQ});
	Combination fullHouseJackHigh = evaluateCombination(new Card[] {hA, cJ, hJ, cA, dJ});
	Combination trips = evaluateCombination(new Card[] {hT, c3, sT, hJ, cT});
	Combination twoPair = evaluateCombination(new Card[] {c7, cT, sQ, h7, hT});
	Combination twoPair1 = evaluateCombination(new Card[] {c6, cT, sK, h6, hT});
	Combination onePair = evaluateCombination(new Card[] {cJ, hK, dQ, hJ, sT});
	Combination onePair1 = evaluateCombination(new Card[] {cJ, hK, dQ, hJ, s9});
	Combination highCard = evaluateCombination(new Card[] {hA, sK, c9, s4, d6});
	Combination wheel = evaluateCombination(new Card[] {s2, c4, d5, hA, s3});
    Combination flush7 = evaluateCombination(new Card[]{d2, d3, d8, h8, h2, dK, dT});
    Combination fullHouse7 = evaluateCombination(new Card[]{d2, c2, dA, h8, h2, sA, d9});
    Combination straight6 = evaluateCombination(new Card[]{c8, s5, h5, s6, h4, d7});

	public void testCompare() {
		assertTrue(strFlush.compareTo(flush) > 0);
		assertTrue(straight.compareTo(wheel) > 0);
		assertTrue(twoPair.compareTo(highCard) > 0);
		assertTrue(onePair.compareTo(onePair1) > 0);
		assertTrue(twoPair.compareTo(twoPair1) > 0);
		assertTrue(fullHouseQueenHigh.compareTo(fullHouseJackHigh) > 0);
	}
	
	public void testResolveCombinations() {
		assertEquals(StraightFlush, strFlush.getType());
		assertEquals(Ace, strFlush.getHighCardRank());

		assertEquals(Flush, flush.getType());
		assertEquals(King, flush.getHighCardRank());
		assertEquals(Nine, flush.getKickers()[1]);
		
		assertEquals(Straight, straight.getType());
		assertEquals(Eight, straight.getHighCardRank());
		
		assertEquals(Straight, wheel.getType());
		assertEquals(Five, wheel.getHighCardRank());
		
		assertEquals(Quad, quad.getType());
		assertEquals(Seven, quad.getHighCardRank());
		assertEquals(Jack, quad.getKickers()[0]);
		
		assertEquals(FullHouse, fullHouseQueenHigh.getType());
		assertEquals(Queen, fullHouseQueenHigh.getHighCardRank());
		assertEquals(King, fullHouseQueenHigh.getKickers()[0]);
		
		assertEquals(Trips, trips.getType());
		assertEquals(Ten, trips.getHighCardRank());
		assertEquals(Jack, trips.getKickers()[0]);
		
		assertEquals(TwoPair, twoPair.getType());
		assertEquals(Ten, twoPair.getHighCardRank());
		assertEquals(Seven, twoPair.getKickers()[0]);
		
		assertEquals(OnePair, onePair.getType());
		assertEquals(Jack, onePair.getHighCardRank());
		assertEquals(Queen, onePair.getKickers()[1]);
	
		assertEquals(HighCard, highCard.getType());
		assertEquals(Ace, highCard.getHighCardRank());
		assertEquals(Six, highCard.getKickers()[2]);
	}

    public void testResolveCombinations6() {
        assertEquals(Straight, straight6.getType());
        assertEquals(Eight, straight6.getHighCardRank());
    }
    public void testResolveCombinations7() {
        assertEquals(Flush, flush7.getType());
        assertEquals(King, flush7.getHighCardRank());
        assertEquals(Ten, flush7.getKickers()[0]);

        assertEquals(FullHouse, fullHouse7.getType());
        assertEquals(Deuce, fullHouse7.getHighCardRank());
        assertEquals(Ace, fullHouse7.getKickers()[0]);
    }
}
