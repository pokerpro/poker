package ru.katalexey.util;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created on 17:26:32 13.02.2007
 * User: katalexey
 * Date: 05.04.11
 */
public class Pair<X, Y> {
    public final X first;
    public final Y second;

    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Pair)) {
            return false;
        }
        final Pair<?, ?> p = (Pair<?, ?>) o;
        return (first != null ? first.equals(p.first) : p.first == null) &&
                (second != null ? second.equals(p.second) : p.second == null);
    }

    public int hashCode() {
        return 31 * (first != null ? first.hashCode() : 0) + (second != null ? second.hashCode() : 0);
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static <X, Y> Pair<X, Y> pair(final X first, final Y second) {
        return new Pair<X, Y>(first, second);
    }

    public static <K extends Comparable<? super K>, V> Comparator<Pair<K, V>> compareOnFirstAsc() {
        return new Comparator<Pair<K, V>>() {
            public int compare(final Pair<K, V> o1, final Pair<K, V> o2) {
                return o1.first.compareTo(o2.first);
            }
        };
    }

    public static <K, V extends Comparable<? super V>> Comparator<Pair<K, V>> compareOnSecondAsc() {
        return new Comparator<Pair<K, V>>() {
            public int compare(final Pair<K, V> o1, final Pair<K, V> o2) {
                return o1.second.compareTo(o2.second);
            }
        };
    }

    public static <K extends Comparable<? super K>, V> Comparator<Pair<K, V>> compareOnFirstDesc() {
        return Collections.reverseOrder(Pair.<K, V>compareOnFirstAsc());
    }

    public static <K, V extends Comparable<? super V>> Comparator<Pair<K, V>> compareOnSecondDesc() {
        return Collections.reverseOrder(Pair.<K, V>compareOnSecondAsc());
    }
}