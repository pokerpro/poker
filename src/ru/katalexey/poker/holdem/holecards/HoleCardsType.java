package ru.katalexey.poker.holdem.holecards;

import ru.katalexey.poker.deck.Rank;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey
 * Date: 09.11.2010
 * Time: 19:02:32
 */
public class HoleCardsType {

    private static final Map<String, HoleCardsType> typesByName= new HashMap<String, HoleCardsType>();
    private static final Map<HoleCardsType, Set<HoleCards>> holeCardsByType = new HashMap<HoleCardsType, Set<HoleCards>>();

    static {
        for (Rank r1 : Rank.values()) {
            String n = "" + r1 + r1;
            typesByName.put(n, new HoleCardsType(n));
            for (Rank r2 : Rank.values()) {
                if (r1.compareTo(r2) > 0) {
                    String ns = "" + r1 + r2 + "s";
                    String no = "" + r1 + r2 + "o";
                    typesByName.put(ns, new HoleCardsType(ns));
                    typesByName.put(no, new HoleCardsType(no));
                }
            }
        }
        for (HoleCardsType hct: typesByName.values()) {
            holeCardsByType.put(hct, new HashSet<HoleCards>());
        }
        for (HoleCards hc: HoleCards.getAll()) {
            holeCardsByType.get(hc.getType()).add(hc);
        }
    }

    public static HoleCardsType getType(String name) {
        return typesByName.get(name);
    }

    public static Set<HoleCards> getHoleCards(String typeName) {
        return Collections.unmodifiableSet(holeCardsByType.get(getType(typeName)));
    }

    public static Set<HoleCards> getHoleCards(HoleCardsType type) {
        return Collections.unmodifiableSet(holeCardsByType.get(type));
    }

    public static Collection<HoleCardsType> getAllTypes() {
        return Collections.unmodifiableCollection(typesByName.values());
    }

    public final String name;

    private HoleCardsType(String n) {
        name = n;
    }
}
