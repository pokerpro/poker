package ru.katalexey.poker.holdem;

import junit.framework.TestCase;
import ru.katalexey.poker.deck.Card;
import ru.katalexey.poker.holdem.combination.Combination;

import static ru.katalexey.poker.deck.Rank.*;
import static ru.katalexey.poker.deck.Suit.*;
import static ru.katalexey.poker.holdem.combination.Combination.evaluateCombination;
import static ru.katalexey.poker.holdem.combination.CombinationType.*;

public class CombinationTest extends TestCase {

	Combination strFlush = evaluateCombination(new Card[] {ACE.of(HEARTS), JACK.of(HEARTS), QUEEN.of(HEARTS), TEN.of(HEARTS), KING.of(HEARTS)});
	Combination flush = evaluateCombination(new Card[] {THREE.of(HEARTS), KING.of(HEARTS), NINE.of(HEARTS), JACK.of(HEARTS), SIX.of(HEARTS)});
	Combination straight = evaluateCombination(new Card[] {FOUR.of(DIAMONDS), EIGHT.of(SPADES), SIX.of(HEARTS), FIVE.of(CLUBS), SEVEN.of(HEARTS)});
	Combination quad = evaluateCombination(new Card[] {SEVEN.of(HEARTS), SEVEN.of(CLUBS), JACK.of(SPADES), SEVEN.of(DIAMONDS), SEVEN.of(SPADES)});
	Combination fullHouseQueenHigh = evaluateCombination(new Card[] {KING.of(HEARTS), QUEEN.of(CLUBS), QUEEN.of(HEARTS), KING.of(DIAMONDS), QUEEN.of(DIAMONDS)});
	Combination fullHouseJackHigh = evaluateCombination(new Card[] {ACE.of(HEARTS), JACK.of(SPADES), JACK.of(HEARTS), ACE.of(CLUBS), JACK.of(DIAMONDS)});
	Combination trips = evaluateCombination(new Card[] {TEN.of(HEARTS), THREE.of(CLUBS), TEN.of(SPADES), JACK.of(HEARTS), TEN.of(CLUBS)});
	Combination twoPair = evaluateCombination(new Card[] {SEVEN.of(CLUBS), TEN.of(CLUBS), QUEEN.of(SPADES), SEVEN.of(HEARTS), TEN.of(HEARTS)});
	Combination twoPair1 = evaluateCombination(new Card[] {SIX.of(CLUBS), TEN.of(CLUBS), KING.of(SPADES), SIX.of(HEARTS), TEN.of(HEARTS)});
	Combination onePair = evaluateCombination(new Card[] {JACK.of(CLUBS), KING.of(HEARTS), QUEEN.of(DIAMONDS), JACK.of(HEARTS), TEN.of(SPADES)});
	Combination onePair1 = evaluateCombination(new Card[] {JACK.of(CLUBS), KING.of(HEARTS), QUEEN.of(DIAMONDS), JACK.of(HEARTS), NINE.of(SPADES)});
	Combination highCard = evaluateCombination(new Card[] {ACE.of(HEARTS), KING.of(SPADES), NINE.of(CLUBS), FOUR.of(SPADES), SIX.of(DIAMONDS)});
	Combination wheel = evaluateCombination(new Card[] {DEUCE.of(SPADES), FOUR.of(CLUBS), FIVE.of(DIAMONDS), ACE.of(HEARTS), THREE.of(CLUBS)});
    Combination flush7 = evaluateCombination(new Card[]{DEUCE.of(DIAMONDS), THREE.of(DIAMONDS), EIGHT.of(DIAMONDS), EIGHT.of(HEARTS), DEUCE.of(HEARTS), KING.of(DIAMONDS), TEN.of(DIAMONDS)});
    Combination fullHouse7 = evaluateCombination(new Card[]{DEUCE.of(DIAMONDS), DEUCE.of(CLUBS), ACE.of(DIAMONDS), EIGHT.of(CLUBS), DEUCE.of(HEARTS), ACE.of(SPADES), NINE.of(CLUBS)});
    Combination straight6 = evaluateCombination(new Card[]{EIGHT.of(CLUBS), FIVE.of(SPADES), FIVE.of(HEARTS), SIX.of(SPADES), FOUR.of(CLUBS), SEVEN.of(DIAMONDS)});

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
		assertEquals(ACE, strFlush.getHighCardRank());

		assertEquals(Flush, flush.getType());
		assertEquals(KING, flush.getHighCardRank());
		assertEquals(NINE, flush.getKickers()[1]);
		
		assertEquals(Straight, straight.getType());
		assertEquals(EIGHT, straight.getHighCardRank());
		
		assertEquals(Straight, wheel.getType());
		assertEquals(FIVE, wheel.getHighCardRank());
		
		assertEquals(Quad, quad.getType());
		assertEquals(SEVEN, quad.getHighCardRank());
		assertEquals(JACK, quad.getKickers()[0]);
		
		assertEquals(FullHouse, fullHouseQueenHigh.getType());
		assertEquals(QUEEN, fullHouseQueenHigh.getHighCardRank());
		assertEquals(KING, fullHouseQueenHigh.getKickers()[0]);
		
		assertEquals(Trips, trips.getType());
		assertEquals(TEN, trips.getHighCardRank());
		assertEquals(JACK, trips.getKickers()[0]);
		
		assertEquals(TwoPair, twoPair.getType());
		assertEquals(TEN, twoPair.getHighCardRank());
		assertEquals(SEVEN, twoPair.getKickers()[0]);
		
		assertEquals(OnePair, onePair.getType());
		assertEquals(JACK, onePair.getHighCardRank());
		assertEquals(QUEEN, onePair.getKickers()[1]);
	
		assertEquals(HighCard, highCard.getType());
		assertEquals(ACE, highCard.getHighCardRank());
		assertEquals(SIX, highCard.getKickers()[2]);
	}

    public void testResolveCombinations6() {
        assertEquals(Straight, straight6.getType());
        assertEquals(EIGHT, straight6.getHighCardRank());
    }
    public void testResolveCombinations7() {
        assertEquals(Flush, flush7.getType());
        assertEquals(KING, flush7.getHighCardRank());
        assertEquals(TEN, flush7.getKickers()[0]);

        assertEquals(FullHouse, fullHouse7.getType());
        assertEquals(DEUCE, fullHouse7.getHighCardRank());
        assertEquals(ACE, fullHouse7.getKickers()[0]);
    }
}
