package ru.katalexey.poker.holdem.combination;

import java.util.*;

import ru.katalexey.poker.deck.Card;
import ru.katalexey.poker.deck.Rank;
import ru.katalexey.poker.deck.Suit;
import ru.katalexey.poker.holdem.holecards.HoleCards;

import static ru.katalexey.poker.deck.Rank.*;
import static ru.katalexey.poker.holdem.combination.CombinationType.*;

public class Combination implements Comparable<Combination> {

	private Combination(CombinationType t, Rank high, Rank[] kicks) {
		type = t;
		highCardRank = high;
		kickers = (kicks != null) ? kicks : new Rank[0];
	}

	private final CombinationType type;
	private final Rank highCardRank;
	private final Rank[] kickers;

	public CombinationType getType() {
		return type;
	}

	public Rank getHighCardRank() {
		return highCardRank;
	}

	public Rank[] getKickers() {
		return kickers;
	}

    public static Combination evaluate(HoleCards hc, Card[] cards) {
        Card[] cs = new Card[cards.length + 2];
        cs[0] = hc.first;
        cs[1] = hc.second;
        System.arraycopy(cards, 0, cs, 2, cards.length);
        return evaluateCombination(cs);
    }

	public static Combination evaluateCombination(final Card[] cards) {
		if (cards.length == 5) {
			return evaluateCombination5(cards);
		}
		if (cards.length == 6) {
			final List<Card> cs = new LinkedList<Card>();
            cs.addAll(Arrays.asList(cards));
            Combination res = null;
            ListIterator<Card> it = cs.listIterator();
            while (it.hasNext()) {
                Card removed = it.next();
                it.remove();
                Combination com = evaluateCombination5(cs.toArray(new Card[5]));
                if (com.compareTo(res) > 0) res = com;
                it.add(removed);
            }
            return res;
		}
        if (cards.length == 7) {
            final List<Card> cs = new LinkedList<Card>();
            cs.addAll(Arrays.asList(cards));
            Combination res = null;
            ListIterator<Card> it = cs.listIterator();
            while (it.hasNext()) {
                Card r1 = it.next();
                it.remove();
                int j = 0;
                while (it.hasNext()) {
                    j++;
                    Card r2 = it.next();
                    it.remove();
                    Combination com = evaluateCombination5(cs.toArray(new Card[5]));
                    if (com.compareTo(res) > 0) res = com;
                    it.add(r2);
                }
                for (; j > 0; j--) {
                    it.previous();
                }
                it.add(r1);
            }
            return res;
        }
		return null;
	}

	private static Combination evaluateCombination5(Card[] cards) {
		CombinationType type = HIGH_CARD;
		Rank high = DEUCE;
		Rank[] kickers = null;
		boolean suited = isSuited(cards);
		List<Rank> list = Arrays.asList(cards[0].rank, cards[1].rank,
				cards[2].rank, cards[3].rank, cards[4].rank);
		Collections.sort(list);
		Rank[] r = new Rank[] { list.get(0), list.get(1), list.get(2),
				list.get(3), list.get(4) };
		boolean succesive = isSuccesive(r);
		if (suited) {
			type = (succesive) ? STRAIGHT_FLUSH : FLUSH;
			high = r[4];
			kickers = (succesive) ? null : new Rank[] { r[3], r[2], r[1], r[0] };
			if (succesive && r[4] == ACE && r[0] == DEUCE) {
				high = FIVE;
			}
			return new Combination(type, high, kickers);
		}
		if (succesive) return new Combination(STRAIGHT, (r[4] == ACE && r[0] == DEUCE) ? r[3] : r[4], kickers);
		int[] t = new int[] { 1, 0, 0, 0, 0 };
		int j = 0;
		for (int i = 1; i < 5; i++, t[j]++)
			if (r[i] != r[i - 1]) j++;
		switch (j) {
		case 4:
			kickers = new Rank[] {r[3],r[2],r[1],r[0]};
			return new Combination(HIGH_CARD, r[4], kickers);
		case 1:
			type = (t[0] == 1 || t[0] == 4) ? QUAD : FULL_HOUSE;
			high = (t[0] < 3) ? r[4] : r[0];
			kickers = (t[0] < 3) ? new Rank[] {r[0]} : new Rank[] {r[4]};
			return new Combination(type, high, kickers);
		case 2:
			switch (t[0]) {
			case 3: return new Combination(TRIPS, r[0], new Rank[] {r[4],r[3]});
			case 2: return new Combination(TWO_PAIR, r[3], new Rank[] {r[0],(t[1] == 2) ? r[4] : r[2]});
			case 1: return (t[1] == 2) ? new Combination(TWO_PAIR, r[3], new Rank[] {r[1],r[0]})
					: new Combination(TRIPS, r[3], new Rank[] {r[4],r[0]});
			}
		case 3:
			if (t[0] == 2) return new Combination(ONE_PAIR, r[0], new Rank[] {r[4],r[3],r[2]});
			if (t[1] == 2) return new Combination(ONE_PAIR, r[1], new Rank[] {r[4],r[3],r[0]});
			if (t[2] == 2) return new Combination(ONE_PAIR, r[2], new Rank[] {r[4],r[1],r[0]});
			if (t[3] == 2) return new Combination(ONE_PAIR, r[3], new Rank[] {r[2],r[1],r[0]});
		}
		return new Combination(type, high, kickers);
	}

	private static boolean isSuccesive(Rank[] ranks) {
		int len = ranks.length;
		if (len < 2) {
			return true;
		}
		for (int i = 1; i < len; ++i) {
			if (ranks[i - 1].index + 1 != ranks[i].index) {
                return i + 1 == len && ranks[i] == ACE && ranks[0] == DEUCE;
            }
		}
		return true;
	}

	private static boolean isSuited(Card[] cards) {
		if (cards.length < 2) {
			return true;
		}
		Suit s = cards[0].suit;
		for (Card c : cards) {
			if (c.suit != s) {
				return false;
			}
		}
		return true;
	}

	public int compareTo(Combination c) {
        if (c == null) return 1;
		int compare = type.compareTo(c.type);
		if (compare != 0) {
			return compare;
		}
		compare = highCardRank.compareTo(c.highCardRank);
		if (compare != 0) {
			return compare;
		}
		for (int i = 0; i < kickers.length; ++i) {
			compare = kickers[i].compareTo(c.kickers[i]);
			if (compare != 0) {
				return compare;
			}
		}
		return 0;
	}

}
