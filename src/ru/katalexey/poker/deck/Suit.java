package ru.katalexey.poker.deck;

public enum Suit {
    CLUBS("Clubs",'c', 0),
    DIAMONDS("Diamonds",'d', 1),
    HEARTS("Hearts",'h', 2),
    SPADES("Spades", 's', 3);

    public final String fullName;
    public final char name;
    public final int index;

    private Suit(String fullName, char name, int index){
        this.fullName = fullName;
        this.name = name;
        this.index = index;
    }
}
