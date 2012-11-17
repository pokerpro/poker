package ru.katalexey.poker.holdem.holecards;

import ru.katalexey.poker.deck.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class HoleCards {

    static final Map<Card, Map<Card, HoleCards>> all = new HashMap<Card, Map<Card, HoleCards>>();

    static {
    	List<Card> allCards = Card.getAll();
        for (Card c : allCards) {
            all.put(c, new HashMap<Card, HoleCards>());
        }
        for (Card c1 : allCards) {
            for (Card c2: allCards) {
                if (c1 != c2) {
                    HoleCards hc = new HoleCards(c1, c2);
                    all.get(c1).put(c2, hc);
                    all.get(c2).put(c1, hc);
                } else break;
            }
        }
    }

    static List<HoleCards> getAll() {
        List<HoleCards> res = new LinkedList<HoleCards>();
        for (Map<Card, HoleCards> m: all.values()) {
            for (HoleCards hc : m.values()) {
                res.add(hc);
            }
        }
        return res;
    }

    public static HoleCards getHoleCards(Card c1, Card c2) {
        return all.get(c1).get(c2);
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
