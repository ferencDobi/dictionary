package com.codecool.dictionary;

import java.util.LinkedList;
import java.util.Optional;

public class Dictionary<K, V> {
    private final int size;
    private LinkedList<DictionaryEntry<K, V>>[] dictionary;

    @SuppressWarnings("unchecked")
    public Dictionary() {
        size = 16;
        dictionary = new LinkedList[size];
    }


    public void put(K key, V value) {
        DictionaryEntry<K, V> newEntry = new DictionaryEntry<>(key, value);
        int hash = newEntry.toHash();
        int slot = hashToSlot(hash);

        if (dictionary[slot] == null) {
            dictionary[slot] = new LinkedList<>();
        }

        Optional<DictionaryEntry<K, V>> entry = dig(slot, hash);

        if (entry.isPresent()) {
            entry.get().setValue(newEntry.getValue());
        } else {
            dictionary[slot].add(newEntry);
        }
    }

    public V get(K key) {
        int hash = key.hashCode();
        return dig(hashToSlot(hash), hash).map(DictionaryEntry::getValue).orElse(null);
    }

    public void remove(K key) {
        int hash = key.hashCode();
        dictionary[hashToSlot(hash)].removeIf(entry -> entry.toHash() == hash);
    }

    private Optional<DictionaryEntry<K, V>> dig(int slot, int hash) {
        if (dictionary[slot] == null) return Optional.empty();

        return dictionary[slot].stream().filter(x -> x.toHash() == hash).findFirst();
    }

    private int hashToSlot(int hash) {
        return Math.abs(hash % size);
    }
}
