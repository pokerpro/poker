package ru.katalexey.poker.deck;

public enum Rank {

    DEUCE(0, "Deuce", "Deuces","2"),
    THREE(1, "Three", "Threes","3"),
    FOUR(2, "Four", "Fours","4"),
    FIVE(3, "Five", "Fives","5"),
    SIX(4, "Six", "Sixes","6"),
    SEVEN(5, "Seven", "Sevens","7"),
    EIGHT(6, "Eight", "Eights","8"),
    NINE(7, "Nine", "Nines","9"),
    TEN(8, "Ten", "Tens","T"),
    JACK(9, "Jack", "Jacks","J"),
    QUEEN(10, "Queen", "Queens","Q"),
    KING(11, "King", "Kings","K"),
    ACE(12, "Ace", "Aces","A");

    public final int index;
    public final String fullName;
    public final String pluralName;
    public final String name;

    private Rank(int index, String fullName, String pluralName, String name){
        this.index = index;
        this.fullName = fullName;
        this.pluralName = pluralName;
        this.name = name;
    }

    public Card of(Suit s) {
        return Card.of(this, s);
    }
}
