package ru.katalexey.poker.deck;

import ru.katalexey.poker.holdem.holecards.HoleCards;

import java.util.*;

public final class Deck {
	private final LinkedList<Card> deck = new LinkedList<Card>();
	private Random r = new Random(System.currentTimeMillis() + this.hashCode());
	
	public Card getCard(final int pos) {
		return deck.get(pos - 1);
	}
	
	public Card getNext() {
	    Card c = deck.removeFirst();
        deck.addLast(c);
        return c;
	}
	
	public Deck() {
		this(false);
	}
	
	public Deck(final boolean isShuffled) {
		deck.addAll(Card.getAll());
		if (isShuffled) {
			shuffle();
		}
	}

    public boolean remove(Card c) {
        return deck.remove(c);
    }

    public void remove(Card... c) {
        deck.removeAll(Arrays.asList(c));
    }

    public void remove(HoleCards hc) {
        remove(hc.first);
        remove(hc.second);
    }

    public void add(Card c) {
        deck.remove(c);
        deck.addLast(c);
    }

    public void add(HoleCards hc) {
        add(hc.first);
        add(hc.second);    
    }

	public void shuffle() {
        int size = deck.size();
		for (int i = size; i > 0; --i) {
			int p = r.nextInt(i);
            deck.addLast(deck.remove(p));
		}
	}

    // only first n cards will be random
    public void shuffle(int n) {
        int size = deck.size();
        n = Math.min(n, size);
		for (int i = 0; i < n; ++i) {
			int p = i + r.nextInt(size - i);
            deck.addFirst(deck.remove(p));
		}
    }

    public Deck copy() {
        Deck newDeck  = new Deck();
        newDeck.deck.clear();
        for (Card card : deck) {
            newDeck.deck.addLast(card);
        }
        return newDeck;
    }
    public String toString() {
        return deck.toString();
    }
}
