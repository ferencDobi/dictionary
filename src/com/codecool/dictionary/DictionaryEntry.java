package com.codecool.dictionary;

public class DictionaryEntry<K, V> {
    private K key;
    private V value;

    DictionaryEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    int toHash() {
        return key.hashCode();
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
