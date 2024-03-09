package com.nbicocchi.exercises.collections.mycollections;

public class MyHashMap implements MyMap {
    private final static int INITIAL_SIZE = 8;
    private final static double DEFAULT_LOAD_FACTOR = 0.75;
    private int size = 0;
    private MyList[] table;
    public MyHashMap() {
        initTable(INITIAL_SIZE);
    }

    private void initTable(int size) {
        table = new MyList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new MyArrayList();
        }
        this.size = 0;
    }

    public Object get(Object key) {
        int bucket = getBucket(key);
        Entry entry = getElement(key, table[bucket]);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    public void put(Object key, Object value) {
        if (capacityRatio() > DEFAULT_LOAD_FACTOR) {
            enlarge();
        }
        int bucket = getBucket(key);
        Entry entry = getElement(key, table[bucket]);
        if (entry != null) {
            entry.setValue(value);
        } else {
            table[bucket].add(new Entry(key, value));
            size++;
        }
    }

    @Override
    public void remove(Object key) {
        int bucket = getBucket(key);
        Entry pair = getElement(key, table[bucket]);
        if (pair != null) {
            table[bucket].remove(pair);
            size--;
        }
    }

    @Override
    public boolean contains(Object key) {
        int bucket = getBucket(key);
        Entry pair = getElement(key, table[bucket]);
        return pair != null;
    }

    @Override
    public int size() {
        return size;
    }

    private int getBucket(Object key) {
        return (Math.abs(key.hashCode()) % table.length);
    }

    private Entry getElement(Object key, MyList list) {
        for (int i = 0; i < list.size(); i++) {
            Entry pair = (Entry) list.get(i);
            if (pair.getKey().equals(key)) {
                return pair;
            }
        }
        return null;
    }

    private double capacityRatio() {
        return size / (double) table.length;
    }

    private void enlarge() {
        MyList entries = toList();
        initTable(table.length * 2);
        for (int i = 0; i < entries.size(); i++) {
            Entry entry = (Entry) entries.get(i);
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    private MyList toList() {
        MyList dst = new MyArrayList();
        for (MyList myList : table) {
            for (int j = 0; j < myList.size(); j++) {
                Entry pair = (Entry) myList.get(j);
                dst.add(pair);
            }
        }
        return dst;
    }

    public String toStringDebug() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            if (table[i].size() == 0) {
                sb.append(String.format("[bucket %d] -> null\n", i));
            } else {
                sb.append(String.format("[bucket %d] -> %s\n", i, table[i]));
            }
        }
        return sb.toString();
    }

    private static class Entry {
        private final Object key;
        private Object value;

        Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MyHashMapPair{" + "key=" + key + ", value=" + value + '}';
        }
    }
}