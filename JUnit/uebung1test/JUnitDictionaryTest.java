package uebung1test;

import org.junit.Assert;
import org.junit.Test;
import uebung1.BinaryTreeDictionary;
import uebung1.Dictionary;
import uebung1.HashDictionary;
import uebung1.SortedArrayDictionary;


public class JUnitDictionaryTest {

    @Test
    public void testSortedArrayDictionary() {
        Dictionary<String, String> dict = new SortedArrayDictionary<>();
        testDict(dict);
    }
    @Test
    public void testHashDictionary() {
        Dictionary<String, String> dict = new HashDictionary<>(3);
        testDict(dict);
    }
    @Test
    public void testBinaryTreeDictionary() {
        Dictionary<String, String> dict = new BinaryTreeDictionary<>();
        testDict(dict);
        BinaryTreeDictionary<Integer, Integer> btd = new BinaryTreeDictionary<>();
        btd.insert(10, 0);
        btd.insert(20, 0);
        btd.insert(50, 0);
        Assert.assertEquals(btd.search(10), (Integer) 0);
        Assert.assertEquals(btd.search(20), (Integer) 0);
        Assert.assertEquals(btd.search(50), (Integer) 0);

        btd.insert(40, 0);
        btd.insert(30, 0);
        Assert.assertEquals(btd.search(40), (Integer) 0);
        Assert.assertEquals(btd.search(30), (Integer) 0);

        btd.insert(21, 0);
        Assert.assertEquals(btd.search(21), (Integer) 0);

        btd.insert(35, 0);
        btd.insert(45, 0);
        Assert.assertEquals(btd.search(35), (Integer) 0);
        Assert.assertEquals(btd.search(45), (Integer) 0);
        System.out.println("insert: successful");
        btd.prettyPrint();

        System.out.println(btd.size());
        System.out.println("For Each Loop:");
        for (Dictionary.Entry<Integer, Integer> e : btd) {
            Assert.assertEquals(e.getValue(), btd.search(e.getKey()));
        }

        Assert.assertNotNull(btd.remove(30));

        Assert.assertNotNull(btd.remove(35));
        Assert.assertNotNull(btd.remove(40));
        Assert.assertNotNull(btd.remove(45));

        Assert.assertNotNull(btd.remove(50));
        System.out.println("remove: successfull");
        System.out.println(btd.size());
        btd.prettyPrint();
    }


    private void testDict(Dictionary<String, String> dict) {
        System.out.println("===== New Test Case ========================");
        System.out.println("test " + dict.getClass());
        Assert.assertEquals("inserting gehen",null, dict.insert("gehen", "go"));
        String s = new String("gehen");
        Assert.assertTrue(dict.search(s) != null);
        Assert.assertEquals("searching gehen", "go", dict.search(s));
        Assert.assertTrue(dict.insert(s, "walk").equals("go"));
        Assert.assertTrue(dict.search("gehen").equals("walk"));
        Assert.assertTrue(dict.remove("gehen").equals("walk"));
        Assert.assertTrue(dict.remove("gehen") == null);
        dict.insert("starten", "start");
        dict.insert("gehen", "go");
        dict.insert("schreiben", "write");
        dict.insert("reden", "say");
        dict.insert("arbeiten", "work");
        dict.insert("lesen", "read");
        dict.insert("singen", "sing");
        dict.insert("schwimmen", "swim");
        dict.insert("rennen", "run");
        dict.insert("beten", "pray");
        dict.insert("tanzen", "dance");
        dict.insert("schreien", "cry");
        dict.insert("tauchen", "dive");
        dict.insert("fahren", "drive");
        dict.insert("spielen", "play");
        dict.insert("planen", "plan");
        dict.insert("diskutieren", "discuss");
        Assert.assertEquals(dict.size(), 17);
        for (Dictionary.Entry<String, String> e : dict) {
            Assert.assertEquals("get and search",e.getValue(), dict.search(e.getKey()));
        }
    }
}
