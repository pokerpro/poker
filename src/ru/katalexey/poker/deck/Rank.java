package ru.katalexey.poker.deck;

public enum Rank {
	Deuce('2', 2), Three('3', 3), Four('4', 4), Five('5', 5), 
	Six('6', 6), Seven('7', 7), Eight('8', 8), Nine('9', 9), 
	Ten('T', 10), Jack('J', 11), Queen('Q', 12), King('K', 13), Ace('A', 14);
	
	Rank(char n, int i) {
		name = n;
		number = i;
	}
	
	public final char name;
	public final int number;

    public Card of(Suit s) {
        return Card.of(this, s);
    }
}
