package ru.katalexey.poker.holdem.holecards;

import ru.katalexey.poker.deck.*;

import java.util.*;

public final class HoleCards {

    static final HoleCards[][] all = new HoleCards[52][52];

    static {
    	Card[] allCards = Card.values();
        for (Card c1 : allCards) {
            for (Card c2: allCards) {
                if (c1 != c2) {
                    HoleCards hc = new HoleCards(c1, c2);
                    all[c1.index][c2.index] = hc;
                    all[c2.index][c1.index] = hc;
                } else break;
            }
        }
    }

    static List<HoleCards> getAll() {
        List<HoleCards> res = new LinkedList<HoleCards>();
        for (HoleCards[] m: all) {
            Collections.addAll(res, m);
        }
        return res;
    }

    public static HoleCards getHoleCards(Card c1, Card c2) {
        return all[c1.index][c2.index];
    }

	public final Card first;
	public final Card second;
	
	public final boolean isPair;
	public final boolean isSuited;
	
	private HoleCards(Card c1, Card c2) {
        if (c1.rank.compareTo(c2.rank) > 0) {
            first = c1;
            second = c2;
        } else {
		    first = c2;
		    second = c1;
        }
        isSuited = (first.suit == second.suit);
        isPair = (first.rank == second.rank);
	}

	public boolean isPair() {
		return isPair;
	}
	
	public boolean isSuited() {
		return isSuited;
	}

    public HoleCardsType getType() {
        if (isPair()) {
            return HoleCardsType.getType("" + first.rank + second.rank);
        }
        if (isSuited()) {
            return HoleCardsType.getType("" + first.rank + second.rank + "s");
        } else {
            return HoleCardsType.getType("" + first.rank + second.rank + "o");
        }
    }

	public String toString() {
		return "[" + first + " " + second + "]";
	}
}
