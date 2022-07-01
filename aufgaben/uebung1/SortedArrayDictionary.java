package uebung1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedArrayDictionary<K, V> implements Dictionary<K, V> {

    private Comparator<? super K> cmp;
    private int size;
    private Entry<K,V>[] data;
    private static final int DEF_CAPACITY = 16;

    public SortedArrayDictionary(Comparator<? super K> c) {
        cmp = c;
        size = 0;
        data = new Entry[DEF_CAPACITY];
    }

    public SortedArrayDictionary() {
        this((x,y) -> ((Comparable<? super K>) x).compareTo(y));
    }

    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);
        if (i != -1) {
            V r = data[i].getValue();
            data[i].setValue(value);
            return r;
        }
        if (data.length == size) {
            data = Arrays.copyOf(data, 2*size);
        }
        int j = size-1;

        while (j >= 0 && cmp.compare(key, data[j].getKey()) < 0) {
            data[j+1] = data[j];
            j--;
        }
        data[j+1] = new Entry<K,V>(key,value);
        size++;
        return null;
    }

    @Override
    public V search(K key) {
        int index = searchKey(key);
        if (index < 0){
            return null;
        } else {
            return data[index].getValue();
        }
    }

    public int searchKey(K key){
        int li = 0;
        int re = size - 1;
        while (re >= li) {
            int m = (li + re)/2;
            if (cmp.compare(key, data[m].getKey()) < 0) {
                re = m - 1;
            } else if (cmp.compare(key, data[m].getKey()) > 0) {
                li= m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    @Override
    public V remove(K key) {
        int i = searchKey(key);
        if (i == -1)
            return null;
        V r = data[i].getValue();
        for (int j = i; j < size-1; j++)
            data[j] = data[j+1];
        data[--size] = null;
        return r;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            private int current = 0;
            @Override
            public boolean hasNext() {
                if (size == current) {
                    return false;
                }
                return true;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                Entry<K, V> e = data[current];
                current++;
                return e;
            }
        };
    }
}
