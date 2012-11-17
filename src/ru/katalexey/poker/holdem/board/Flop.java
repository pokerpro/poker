package ru.katalexey.poker.holdem.board;

import ru.katalexey.poker.deck.Card;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey
 * Date: 18.11.2010
 * Time: 19:59:53
 */
public final class Flop {
    private final Card[] cards = new Card[3];

    public Flop() {
    }

    Flop(Card[] cs) {
        if (cs.length < 3) return;
        System.arraycopy(cs, 0, cards, 0, 3);
    }

    public void setFlop(Card c0, Card c1, Card c2) {
        cards[0] = c0;
        cards[1] = c1;
        cards[2] = c2;
    }

    public void clear() {
        cards[0] = null;
        cards[1] = null;
        cards[2] = null;
    }

    public Card[] getCards() {
        Card[] res = new Card[3];
        System.arraycopy(cards, 0, res, 0, 3);
        return res;
    }
}
