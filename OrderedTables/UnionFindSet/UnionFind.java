package OrderedTables.UnionFindSet;

import java.util.*;

public class UnionFind {

    /**
     * 由于在java中 Hashmap对于内置类型(int、float...)是值引用 如果不把值包装成一个类 则一个map中只能有一个相同的值
     */
    public static class Element<V> {
        V val;

        public Element(V val) {
            this.val = val;
        }
    }

    public static class UnionFindSet<V> {
        HashMap<V, Element<V>> elementMap;
        HashMap<Element<V>, Element<V>> fatherMap;
        HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V val : list) {
                Element<V> element = new Element<>(val);
                elementMap.put(val, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }


        /**
         * 寻找一个元素的头节点
         * 注意在查找过程中经过的点的父亲节点需要修改成最后找到的头节点
         * */
        private Element<V> findHead(Element<V> element) {
            HashSet<Element<V>> path = new HashSet<>();
            Element<V> cur = element;
            while (cur != fatherMap.get(cur)) {
                path.add(cur);
                cur = fatherMap.get(cur);
            }
            for (Element<V> val : path) {
                fatherMap.put(val, cur);
            }
            return cur;
        }


        /**
         * 并查集的查询操作
         */
        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        /**
         * 合并操作 将size比较大的头节点作为size比较小的头节点的父亲节点 再更新size
         * */
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }

    }
}
