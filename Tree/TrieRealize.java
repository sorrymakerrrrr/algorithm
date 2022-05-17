package Tree;

import java.util.*;


public class TrieRealize {
    /**
     *
     * @param operators string字符串二维数组 the ops
     * @return string字符串一维数组
     */
    public static String[] trieU (String[][] operators) {
        // write code here
        Trie root = new Trie();
        List<String> resList = new ArrayList<>();
        for (String[] opeartor : operators) {
            if (opeartor[0] == "1") {
                root.insert(opeartor[1]);
            }else if (opeartor[0] == "2") {
                root.delete(opeartor[1]);
            }else if (opeartor[0] == "3") {
                resList.add(root.search(opeartor[1]) ? "Yes" : "No");
            }else {
                resList.add(String.valueOf(root.prefixNumber(opeartor[1])));
            }
        }
        String[] res = new String[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        String[][] operators = new String[][]{
                new String[]{"1","qwer"},
                new String[]{"1","qwe"},
                new String[]{"3","qwer"},
                new String[]{"4","q"},
                new String[]{"2","qwer"},
                new String[]{"3","qwer"},
                new String[]{"4","q"}};
        String[] res = trieU(operators);
        for (String value : res) System.out.println(value);
    }

    public static class TrieNode {
        int pass;
        int end;
        TrieNode[] nexts;
        public TrieNode(){
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        private static TrieNode root;  // 根结点

        public Trie() {
            root = new TrieNode();
        }
        // 增
        public static void insert(String word) {
            if (word == null) {
                return ;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }
        // 删
        public static void delete(String word) {
            if (!search(word)) {
                return ;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return ;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
        // 查
        public static boolean search(String word) {
            if (word == null) {
                return false;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return false;
                }
                node = node.nexts[index];
            }
            return node.end != 0;
        }
        // 以字符串pre作为前缀的单词数量
        public static int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
}
