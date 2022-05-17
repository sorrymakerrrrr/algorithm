package OrderedTables.SkipList;

import java.util.ArrayList;
import java.lang.Math;

/**
 * 跳表实现
 */
public class SkipList {


    /**
     * 跳表节点定义
     */
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        // 第i层节点 nextNodes.get(i) 即可以得到
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<SkipListNode<K, V>>();
        }

        /**
         * 遍历的时候如果往右遍历到null（next == null），此时遍历结束 最后一个节点的null不调用这个方法
         * 头节点(null)，人为规定为最小
         * 判断node的key是否小于otherNode的key
         */
        public boolean isKeyLess(K otherKey) {
            // 只有头节点可能key == null
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        /**
         * 判断otherNode的key是否等于node的key
         */
        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) ||
                    ((key != null && otherKey != null) && key.compareTo(otherKey) == 0);
        }
    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5;  // < 0.5 加层数 >= 0.5 停止  常量
        private SkipListNode<K, V> head;
        private int size;  // 节点数
        private int maxLevel;  // 最大层数

        /**
         * 初始化跳表 创建一个头节点 k v均为空 nextNodes初始化为第o层
         */
        public SkipListMap() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        /**
         * 从最高层开始 一路找下去
         * 最终找到第0层 找到第0层的小于key的最右节点
         */
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }


        /**
         *  现在来到的节点为cur，cur来到了level层，找到level层上小于key的最后一个节点
         *  即在level层里如何移动
         */
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && cur.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }


        /**
         * 查询是否存在key
         * */
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);  // next != null 可能不需要
        }

        public void put(K key, V value) {
            // 如果key为null 什么都不做
            if (key == null) {
                return ;
            }
            // 判定是否已经存在相同的key 若相同则更新value
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if (find != null && find.isKeyEqual(key)) {
                find.val = value;
            } else {  // 否则则添加key 首先更新 size 新增节点的level maxlevel
                size++;
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i < newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                // 更新跳表
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        /**
         * 删除跳表中的一个key
         * */
        public void remove(K key) {
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    if (next != null && next.isKeyEqual(key)) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    // 在pre指向head 且level不等于0 且此时next为空时 删除head的该层
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        pre.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }


        /**
         * 得到跳表中key的值
         * */
        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }

        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                while (cur.nextNodes.get(level) != null) {
                    cur = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

    }

}
