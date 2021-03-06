package ru.katalexey.poker.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Card {
    DEUCE_CLUBS(Rank.DEUCE, Suit.CLUBS),
    THREE_CLUBS(Rank.THREE, Suit.CLUBS),
    FOUR_CLUBS(Rank.FOUR, Suit.CLUBS),
    FIVE_CLUBS(Rank.FIVE, Suit.CLUBS),
    SIX_CLUBS(Rank.SIX, Suit.CLUBS),
    SEVEN_CLUBS(Rank.SEVEN, Suit.CLUBS),
    EIGHT_CLUBS(Rank.EIGHT, Suit.CLUBS),
    NINE_CLUBS(Rank.NINE, Suit.CLUBS),
    TEN_CLUBS(Rank.TEN, Suit.CLUBS),
    JACK_CLUBS(Rank.JACK, Suit.CLUBS),
    QUEEN_CLUBS(Rank.QUEEN, Suit.CLUBS),
    KING_CLUBS(Rank.KING, Suit.CLUBS),
    ACE_CLUBS(Rank.ACE, Suit.CLUBS),

    DEUCE_DIAMONDS(Rank.DEUCE, Suit.DIAMONDS),
    THREE_DIAMONDS(Rank.THREE, Suit.DIAMONDS),
    FOUR_DIAMONDS(Rank.FOUR, Suit.DIAMONDS),
    FIVE_DIAMONDS(Rank.FIVE, Suit.DIAMONDS),
    SIX_DIAMONDS(Rank.SIX, Suit.DIAMONDS),
    SEVEN_DIAMONDS(Rank.SEVEN, Suit.DIAMONDS),
    EIGHT_DIAMONDS(Rank.EIGHT, Suit.DIAMONDS),
    NINE_DIAMONDS(Rank.NINE, Suit.DIAMONDS),
    TEN_DIAMONDS(Rank.TEN, Suit.DIAMONDS),
    JACK_DIAMONDS(Rank.JACK, Suit.DIAMONDS),
    QUEEN_DIAMONDS(Rank.QUEEN, Suit.DIAMONDS),
    KING_DIAMONDS(Rank.KING, Suit.DIAMONDS),
    ACE_DIAMONDS(Rank.ACE, Suit.DIAMONDS),

    DEUCE_HEARTS(Rank.DEUCE, Suit.HEARTS),
    THREE_HEARTS(Rank.THREE, Suit.HEARTS),
    FOUR_HEARTS(Rank.FOUR, Suit.HEARTS),
    FIVE_HEARTS(Rank.FIVE, Suit.HEARTS),
    SIX_HEARTS(Rank.SIX, Suit.HEARTS),
    SEVEN_HEARTS(Rank.SEVEN, Suit.HEARTS),
    EIGHT_HEARTS(Rank.EIGHT, Suit.HEARTS),
    NINE_HEARTS(Rank.NINE, Suit.HEARTS),
    TEN_HEARTS(Rank.TEN, Suit.HEARTS),
    JACK_HEARTS(Rank.JACK, Suit.HEARTS),
    QUEEN_HEARTS(Rank.QUEEN, Suit.HEARTS),
    KING_HEARTS(Rank.KING, Suit.HEARTS),
    ACE_HEARTS(Rank.ACE, Suit.HEARTS),

    DEUCE_SPADES(Rank.DEUCE, Suit.SPADES),
    THREE_SPADES(Rank.THREE, Suit.SPADES),
    FOUR_SPADES(Rank.FOUR, Suit.SPADES),
    FIVE_SPADES(Rank.FIVE, Suit.SPADES),
    SIX_SPADES(Rank.SIX, Suit.SPADES),
    SEVEN_SPADES(Rank.SEVEN, Suit.SPADES),
    EIGHT_SPADES(Rank.EIGHT, Suit.SPADES),
    NINE_SPADES(Rank.NINE, Suit.SPADES),
    TEN_SPADES(Rank.TEN, Suit.SPADES),
    JACK_SPADES(Rank.JACK, Suit.SPADES),
    QUEEN_SPADES(Rank.QUEEN, Suit.SPADES),
    KING_SPADES(Rank.KING, Suit.SPADES),
    ACE_SPADES(Rank.ACE, Suit.SPADES);

    private static final Card[][] cards = new Card[13][4];

    static {
        for (Card card : Card.values()) {
            cards[card.rank.index][card.suit.index] = card;
        }
    }

    public final Rank rank;
    public final Suit suit;
    public final int index;

    private Card(Rank r, Suit s) {
        rank = r;
        suit = s;
        index = 13 * s.index + r.index;
    }

    public static Card of(Rank r, Suit s) {
        return cards[r.index][s.index];
    }

	public String toString() {
		return "" + rank.name + suit.name;
	}
} 
