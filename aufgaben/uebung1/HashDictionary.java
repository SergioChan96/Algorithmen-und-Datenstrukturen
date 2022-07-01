package uebung1;

import java.util.*;

public class HashDictionary<K,V> implements Dictionary<K,V> {

    private static final int DEF_CAPACITY = 31;
    private int size;
    private LinkedList<Entry<K,V>>[] data;

    public HashDictionary() {
        this(DEF_CAPACITY);
    }

    public HashDictionary(int size){
        this.size = 0;
        this.data = new LinkedList[size];
    }

    @Override
    public V insert(K key, V value) {
        checkForLoad();
        int adr = hash(key);
        if (data[adr] != null) {
            int index = searchAdr(key);
            if (index >= 0) {
                V oldValue = data[adr].get(index).getValue();
                data[adr].get(index).setValue(value);
                return oldValue;
            }
        }
        Entry newEntry = new Entry<>(key, value);
        if(data[adr] == null) {
            data[adr] = new LinkedList<>();
        }
        data[adr].add(newEntry);
        size++;
        return null;
    }

    @Override
    public V search(K key) {
        int adr = hash(key);
        if (data[adr] != null) {
            int index = searchAdr(key);
            if (index >= 0) {
                return data[adr].get(index).getValue();
            }
        }
        return null;
    }

    private int searchAdr(K key) {
        int adr = hash(key);
        if(data[adr] != null) {
            for (int i = 0; i < data[adr].size(); i++) {
                if(data[adr].get(i).getKey().equals(key)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public V remove(K key) {
        int adr = hash(key);
        if (data[adr] != null) {
            int index = searchAdr(key);
            if (index < 0) {
                return null;
            }
            V old = data[adr].get(index).getValue();
            data[adr].remove(index);
            size--;
            return old;
        }
        return null;
    }
    @Override
    public int size() {
        return this.size;
    }
    private void checkForLoad() {
        if (size == data.length) {
            LinkedList<Entry<K, V>>[] holder = data;
            data = new LinkedList[nextPrime(data.length * 2)];
            size = 0;
            for (int i = 0; i < holder.length; i++) {
                if (holder[i] != null) {
                    for (Entry<K, V> e : holder[i]) {
                        insert(e.getKey(),e.getValue());
                    }
                }
            }
        }
    }
    private int hash(K key) {
        int adr = 0;
        adr = key.hashCode();
        if (adr < 0) {
            adr = -(adr);   // in case of Overflow
        }
        return (adr % (data.length));
    }

    public static int nextPrime(int num) {
        num++;
        for (int i = 2; i < num; i++) {
            if(num%i == 0) {
                num++;
                i=2;
            } else {
                continue;
            }
        }
        return num;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashDictionaryIterator();
    }

    private class HashDictionaryIterator implements Iterator<Entry<K,V>>{

        private int idx;
        Iterator<Entry<K,V>> it;

        public HashDictionaryIterator(){
            this.idx = -1;
        }

        @Override
        public boolean hasNext() {
            if (it != null && it.hasNext()) {
                return true;
            } else {
                while (++this.idx < data.length) {
                    if (data[idx]!= null) {
                        it = data[idx].iterator();
                        return it.hasNext();
                    }
                }
                return false;
            }
        }
        @Override
        public Entry<K, V> next() {
            return it.next();
        }
    }
}
