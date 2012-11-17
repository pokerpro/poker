package ru.katalexey.poker.holdem.board;

import ru.katalexey.poker.deck.Card;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey
 * Date: 18.11.2010
 * Time: 19:55:54
 */
public final class Board {
    private final Flop flop= new Flop();
    private Card turn;
    private Card river;

    public void setTurn(Card t) {
        turn = t;
    }

    public void setRiver(Card r) {
        river = r;
    }

    public void clear() {
        flop.clear();
        turn = null;
        river = null;
    }

    public Flop getFlop() {
        return flop;
    }

    public Card getTurn() {
        return turn;
    }

    public Card getRiver() {
        return river;
    }
}
