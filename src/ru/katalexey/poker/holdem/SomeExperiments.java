package ru.katalexey.poker.holdem;

import ru.katalexey.poker.deck.*;
import ru.katalexey.poker.holdem.combination.Combination;
import ru.katalexey.poker.holdem.holecards.HoleCards;
import ru.katalexey.poker.holdem.holecards.HoleCardsType;

import static ru.katalexey.poker.holdem.combination.Combination.evaluate;

public class SomeExperiments {

	public static void main(String[] args) {
//		exp1();
//      exp2();
	}

    private static double compareHoleCards(HoleCards hc1, HoleCards hc2, int n) {
        Deck deck = new Deck();
        deck.remove(hc1);
        deck.remove(hc2);
        int win = 0;
        int split = 0;
        for (int i = 0; i < n; i++) {
            deck.shuffle(5);
            Card[] board = new Card[5];
            for (int j = 0; j < 5; j++) {
                board[j] = deck.getNext();
            }
            Combination c1 = evaluate(hc1, board);
            Combination c2 = evaluate(hc2, board);
            if (c1.compareTo(c2) > 0) win++;
            if (c1.compareTo(c2) == 0) split++;
        }
        return (split * 0.5 + win) / n;
    }

    public static void exp1() {
		Deck d = new Deck();
		int N = 1000000;
		int pair = 0;
		int setFlop = 0;
		int setTurn = 0;
		for (int i = 0; i < N; i++) {
			d.shuffle();
			HoleCards h = HoleCards.getHoleCards(d.getNext(), d.getNext());
			if (h.isPair()) {
				pair++;
				Rank r = h.first.rank;
				if (d.getNext().rank == r || d.getNext().rank == r || d.getNext().rank == r) {
					setFlop++;
				} else if (d.getNext().rank == r) setTurn++;
			}
		}
		System.out.println("Poket pair freq = " + pair*100.0/N + "%");
		System.out.println("Set on flop freq = " + setFlop*100.0/pair + "%");
		System.out.println("Set only on turn freq = " + setTurn*100.0/(pair - setFlop) + "%");
	}

    public static void exp2() {
		Deck d = new Deck();
		int N = 1000000;
		int suited = 0;
		int flushFlop = 0;
		int flushDraw = 0;
		int flushDrawClosed = 0;
		for (int i = 0; i < N; i++) {
			d.shuffle();
			HoleCards h = HoleCards.getHoleCards(d.getNext(), d.getNext());
			if (h.isSuited()) {
				suited++;
				Suit s = h.second.suit;
                Card[] flop = new Card[] {d.getNext(), d.getNext(), d.getNext()};
				int t = (flop[0].suit == s ? 1 : 0) + (flop[1].suit == s ? 1 : 0) + (flop[2].suit == s ? 1 : 0);
                if (t == 3) {
                    flushFlop++;
                }
                if (t == 2) {
                    flushDraw++;
                    if (d.getNext().suit == s || d.getNext().suit == s) {
                        flushDrawClosed++;    
                    }
                }
			}
		}
		System.out.println("Suited cards freq = " + suited*100.0/N + "%");
		System.out.println("Flush on flop freq = " + flushFlop*100.0/suited + "%");
		System.out.println("Flushdraw on flop freq = " + flushDraw*100.0/suited + "%");
		System.out.println("Flushdraw closed on turn-river freq = " + flushDrawClosed*100.0/flushDraw + "%");
	}
}
