package ru.katalexey.poker.deck;

import java.util.*;

public class Card {
//	hA(0),  h2(1),  h3(2),  h4(3),  h5(4),  h6(5),  h7(6),  h8(7),  h9(8),  hT(9),  hJ(10), hQ(11), hK(12),
//	dA(13), d2(14), d3(15), d4(16), d5(17), d6(18), d7(19), d8(20), d9(21), dT(22), dJ(23), dQ(24), dK(25),
//    cA(26), c2(27), c3(28), c4(29), c5(30), c6(31), c7(32), c8(33), c9(34), cT(35), cJ(36), cQ(37), cK(38),
//    sA(39), s2(40), s3(41), s4(42), s5(43), s6(44), s7(45), s8(46), s9(47), sT(48), sJ(49), sQ(50), sK(51);

    private static final List<Card> all = new ArrayList<Card>(52);
    private static final Map<Rank, Map<Suit, Card>> map = new HashMap<Rank, Map<Suit, Card>>(13);

    static {
        for (Rank rank : Rank.values()) {
            Map<Suit, Card> map = new HashMap<Suit, Card>(4);
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                all.add(card);
                map.put(suit, card);
            }
            Card.map.put(rank, map);
        }
    }

    public static List<Card> getAll() {
        return Collections.unmodifiableList(all);
    }

    private Card(Rank r, Suit s) {
        rank = r;
        suit = s;
    }
    public final Rank rank;

	public final Suit suit;

    public static Card of(Rank r, Suit s) {
        return map.get(r).get(s);
    }
	public String toString() {
		return "" + rank.name + suit.name;
	}
} 
