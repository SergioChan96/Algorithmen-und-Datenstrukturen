package uebung1test;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import uebung1.*;

public class JUnitMessungen {
    private HashDictionary<String, String> hash = new HashDictionary<>();
    private HashDictionary<String, String> hashFull = new HashDictionary<>();
    private SortedArrayDictionary<String, String> sorted = new SortedArrayDictionary<>();
    private SortedArrayDictionary<String, String> sortedFull = new SortedArrayDictionary<>();
    private BinaryTreeDictionary<String, String> binary = new BinaryTreeDictionary<>();
    private BinaryTreeDictionary<String, String> binaryFull = new BinaryTreeDictionary<>();
    private TUI tui = new TUI();
    private Scanner sc;
    @Before
    public void prepare() {
        for (int i = 0; i < 4; i++) {
            try {
                sc = new Scanner(new File("aufgaben/uebung1/dtengl.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            switch (i){
                case 0:
                    fill(hashFull, sc);
                    break;
                case 1:
                    fill(sortedFull, sc);
                    break;
                case 2:
                    fill(binaryFull, sc);
                default:
            }
        }
    }
    private void fill(Dictionary dic, Scanner sc) {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            dic.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestInsert8000EntrysHash() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            hash.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestInsert16000EntrysHash() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            hash.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestSearch8000EntrysHash() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            hashFull.search(keys[0]);
        }
    }
    @Test
    public void TestSearch16000EntrysHash() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            hashFull.search(keys[0]);
        }
    }
    @Test
    public void TestNotFound8000Hash() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            hashFull.search(keys[1]);
        }
    }
    @Test
    public void TestNotFound16000Hash() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            hashFull.search(keys[1]);
        }
    }
    @Test
    public void TestInsert8000Sorted() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            sorted.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestInsert16000Sorted() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            hash.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestSearch8000Sorted() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            sortedFull.search(keys[0]);
        }
    }
    @Test
    public void TestSearch16000Sorted() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            hashFull.search(keys[0]);
        }
    }
    @Test
    public void TestNotFound8000Sorted() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            sortedFull.search(keys[1]);
        }
    }
    @Test
    public void TestNotFound16000Sorted() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            hashFull.search(keys[1]);
        }
    }
    @Test
    public void TestInsert8000EntrysBinary() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            binary.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestInsert16000Binary() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            binary.insert(keys[0], keys[1]);
        }
    }
    @Test
    public void TestSearch8000Binary() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            binaryFull.search(keys[0]);
        }
    }
    @Test
    public void TestSearch16000Binary() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            binaryFull.search(keys[0]);
        }
    }
    @Test
    public void TestNotFound8000Binary() {
        String keys[];
        for (int i = 0; i < 8000; i++) {
            keys = sc.nextLine().split(" ");
            binaryFull.search(keys[1]);
        }
    }
    @Test
    public void TestNotFound16000Binary() {
        String keys[];
        while (sc.hasNext()) {
            keys = sc.nextLine().split(" ");
            binaryFull.search(keys[1]);
        }
    }
}
