package uebung1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TUI {
    public Dictionary<String, String> dic;
    public static void main(String args[]){
        TUI tui = new TUI();
        tui.readCommand();
    }

    public void readCommand() {
        String command[];
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        while (sc.hasNext()) {

            String input = sc.nextLine();
            if (input.startsWith("create")) {
                command = input.split(" ");
                if (command.length > 1){
                    create(command[1]);
                } else {
                    create("");
                }
            } else if (input.startsWith("read ")) {
                command = input.split(" ");
                read(Integer.parseInt(command[1]), command[2]);
            } else if (input.startsWith("p")) {
                print();
            } else if (input.startsWith("s ")) {
                command = input.split(" ");
                search(command[1]);
            } else if (input.startsWith("i ")){
                command = input.split(" ");
                insert(command[1], command[2]);
            } else if (input.startsWith("r ")) {
                command = input.split(" ");
                remove(command[1]);
            } else if (input.trim().equals("exit")) {
                System.exit(0);
            } else {
                System.err.println("command unknown");
            }
            System.out.print("> ");
        }
    }
    public void create(String implementation) {
        if (implementation.contains("hash")) {
            dic = new HashDictionary();
        } else if (implementation.contains("Binary")) {
            dic = new BinaryTreeDictionary();
        } else {
            dic = new SortedArrayDictionary();
        }
    }

    public void read(int rows, String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            for (int i = 0; i < rows; i++) {
                String lines[] = sc.nextLine().split(" ");
                dic.insert(lines[0], lines[1]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (NoSuchElementException e) {
            System.err.println("File does not contain that many rows");
        }
    }
    public void print() {
        for (Dictionary.Entry<String, String> e : dic){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    public void search(String key) {
        String value = dic.search(key);
        System.out.println(key + " -> " + value);
    }
    public void insert(String key, String value) {
        dic.insert(key, value);
    }
    public void remove(String key) {
        dic.remove(key);
    }
}
