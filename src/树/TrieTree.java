package 树;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrieTree {
    Node root = new Node();
    ArrayList<String> strings = new ArrayList<>();
    int size = 0;

    public void insert(String string) {
        Node node = root;
        for (int i = 0; i < string.length(); i++) {
            if (node.next.get(string.charAt(i) + "") == null) {
                node.next.put("" + string.charAt(i), new Node());
            }
            node = node.next.get("" + string.charAt(i));
        }
        node.isEnd = true;
        size++;
    }


    @Contract(value = "null->false", pure = true)
    public boolean startWith(@Nullable String string) {
        if (string == null) {
            return false;
        }
        Node node = root;
        for (int i = 0; i < string.length(); i++) {
            if (node.next.get(string.charAt(i)+"") == null) {
                return false;
            }
            node = node.next.get(string.charAt(i) + "");
        }
        return true;
    }


    public ArrayList<String> search(@Nullable String string) {
        if (!startWith(string)) {
            return null;
        }
        Node node = root;
        for (int i = 0; i < string.length(); i++) {
            node = node.next.get(string.charAt(i) + "");
        }
        strings.clear();
        dfs(node, string);
        return strings;
    }

    protected void dfs(Node node, String str) {
        if (node.isEnd) {
            strings.add(str);
            if (node.next.size() == 0) {
                return;
            }
        }
        for (String s : node.next.keySet()) {
            dfs(node.next.get(s), str + s);
        }
    }


    protected class Node {
        Map<String, Node> next;
        boolean isEnd;

        Node() {
            this.isEnd = false;
            this.next = new HashMap<>();
        }

    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("hahaha");
        trieTree.insert("hanaskfh");
        trieTree.insert("aanaskfh");

        System.out.println(trieTree.search("ha"));
    }
}
