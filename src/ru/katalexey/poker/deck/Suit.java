package ru.katalexey.poker.deck;

public enum Suit {
	Hearts('h'), Diamonds('d'), Clubs('c'), Spades('s');
	
	Suit(char n) {
		name = n;
	}
	
	public final char name;
}
