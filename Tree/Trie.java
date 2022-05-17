package Tree;

class Trie {

    public class TrieNode {
        char val;
        int p;
        int e;
        TrieNode[] nexts;

        public TrieNode() {
            p = 0;
            e = 0;
            nexts = new TrieNode[26];
        }

        public TrieNode(char val) {
            this.val = val;
            p = 0;
            e = 0;
            nexts = new TrieNode[26];
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pre = root;
        char[] chs = word.toCharArray();
        for (char ch : chs) {
            TrieNode cur =  new TrieNode(ch);
            cur.p++;
            pre.nexts[ch - 97] = cur;
            pre = cur;
        }
        pre.e++;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            cur = cur.nexts[word.charAt(i) - 97];
            if (cur == null) return false;
        }
        return cur.e > 0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return true;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("app");
//        obj.insert("apple");
        System.out.println(obj.search("app"));
//        System.out.println(obj.search("apple"));
    }
}
