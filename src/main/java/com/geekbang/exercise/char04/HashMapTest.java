package com.geekbang.exercise.char04;

import java.util.*;

public class HashMapTest {
    public static void main(String[] args) {
        // HashMap
        /**
         * 1、Map与Collection并列存在，用于保存具有映射关系的数据：Key-Value（双列元素）
         * 2、Map中的 **key** 和 value 可以是任何**引用类型**数据，会被封装到 HashMap$Node 对象中
         * 3、Map中的key不允许重复，原因和HashSet一样。value可以重复
         * 4、通过key总是能找到value
         * 5、Map存放数据的key-value，是一对k-v放在一个HashMap$Node中，因为Node实现了 Entry接口
         * **/
        Map map = new HashMap();
        map.put("no1", "mary");
        map.put("no2", "lily");
        map.put("no3", "mary"); // 相同key，等同于替换
        map.get("no1");
        System.out.println("map= "+ map);
        /**
         * 1、k-v 最后是 HashMap$Node node = newNode(hash, key, value, null)
         * 2、k-v 为了方便遍历，还会创建 EntrySet 集合，该集合存放的元素类型为 Entry，而一个Entry
         *      对象就有k,v EntrySet<Entry<K,V>> 即： transient Set<Entry<K,V>> entrySet;
         * 3、entrySet 中，定义类型是 Map.Entry，但是实际上存放的还是 HashMap$Node
         *      因为 static class Node<K,V> implements Map.Entry<K, V>
         * 4、当把 HashMap$Node 对象存放到 entrySet 就方便我们的遍历
         *      因为 EntrySet 存在两个方法 K getKey(); V getValue(); 可以供取出k-v
         * **/
        Set set = map.entrySet();
        System.out.println(set.getClass()); // HashMap$EntrySet 说明这是HashMap的内部类，名字为EntrySet
        for (Object obj : set) {
            System.out.println(obj.getClass()); // HashMap$Node
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        // 第一组：先取出 所有key，通过key取出对应的Value
        Set keySet = map.keySet();
        // 1》增强for
        System.out.println(keySet.getClass()); // HashMap$KeySet
        for (Object key : keySet) {
            System.out.println(key + "-" + map.get(key));
        }
        // 2》迭代器
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key + "-" + map.get(key));
        }

        // 第二组：把所有的values取出。这里可以使用所有Collections使用的遍历方法
        Collection values = map.values();
        System.out.println(values.getClass()); // HashMap$Values
        // 1》增强 for
        for (Object value : values) {
            System.out.println(value);
        }
        // 2》迭代器
        Iterator valuesIterator = values.iterator();
        while (valuesIterator.hasNext()) {
            Object v = valuesIterator.next();
            System.out.println("values: " + v);
        }

        // 第三组：通过EntrySet 来获取k-v
        Set entrySet = map.entrySet(); // EntrySet<Map.Entry<K, V>>
        // 1> 增强for
        for (Object entry : entrySet) {
            // 将 entry 转成 Map.Entry
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
        // 2> 迭代器
        Iterator entrysetIterarot = entrySet.iterator();
        while (entrysetIterarot.hasNext()) {
            Object next = entrysetIterarot.next();
            System.out.println(next.getClass()); // HashMap$Node -实现 -> Map.Entry (getKey, getValue)
            // 向下转型 Map.Entry
            Map.Entry m = (Map.Entry) next;
            System.out.println(m.getKey() + "-" + m.getValue());
        }

    }
}

class HashTableTest {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("lic", 100);
        hashtable.put("lucy", 100);
        // hashtable.put(null, 20);  报错
        hashtable.put("lic", 88); // 替换
        hashtable.get("key");
        hashtable.remove("key");
        // Hashtable的底层
        // 1、底层只有数组 Hashtable$Entry[] 初始化大小为 11
        // 2、临界值 threshold 8 = 11 * 0。75
        // 3、扩容： addEntry(hash, key, value, index);
        //         if(count > threshold){ rehash() { newCapacity = (oldCapacity << 1) + 1 }
    }
}

class TreeSet_ {
    public static void main(String[] args) {
        // 1、使用无参构造器，创建的TreeSet时，仍然是无序的
//        TreeSet treeSet = new TreeSet(); // 这里不传入comparator 也不会报错，是因为String默认继承了Comparator接口
        // 2、支持排序，使用TreeSet提供的构造器，可以传入一个比较器（匿名内部类）并指定排序规则
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // 向下转型，调用String的compareTo进行字符串大小比较
                // !!! 如果比较的值相同，则不会添加到treeSet中
//                return ((String) o1).compareTo((String) o2);
                return ((String) o1).length() - ((String) o2).length();
            }
        });
        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("sp");
        treeSet.add("a");
        treeSet.add("abc"); // 不会被加入，因为长度为3的已经存在

        System.out.println("treeSet= " + treeSet);


        TreeSet treeSet1 = new TreeSet();
        // 报错，因为TreeSet是比较排序，没有传入自定义的comparator，加入的item项内也没有实现Comparable方法。
        // 这一步便报错 comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
        treeSet1.add(new Person1()); // cannot be cast to class java.lang.Comparable
        // 修改Person1
    }
}
//class Person1 {}
class Person1 implements Comparable {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class TreeMapTest {
    public static void main(String[] args) {
        // TreeMap 使用默认的构造器，创建 TreeMap，是无序的。可以使用comparator排序
//        TreeMap treeMap = new TreeMap();
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // 按照传入的 K(String) 的大小进行排序
                return ((String) o1).compareTo((String) o2);
//                return ((String) o1).length() - ((String) o2).length();
            }
        });
        treeMap.put("jack", "杰克");
        treeMap.put("tom", "汤姆");
        treeMap.put("kristina", "克瑞斯提努");
        treeMap.put("smith", "斯密斯");
        treeMap.put("inky", "印刻"); // 按照length比较，这个length为4的，加入不进去。相同直接return

        System.out.println("treeMap: "+ treeMap);
    }
}

class CollectionsTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("tom");
        list.add("smith");
        list.add("king");
        list.add("milan");
        // 测试Collections的静态方法
        Collections.reverse(list);
        // 随机排序
        Collections.shuffle(list);
        Collections.sort(list);
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o2).length() - ((String) o1).length();
            }
        });
        Collections.swap(list, 0, 1);
        Collections.max(list);
        Collections.max(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o2).length() - ((String) o1).length();
            }
        });
        Collections.frequency(list, "tom");
        // void copy(List dest, List src): 将src中的内容复制到dest中
        // 一个完整的copy需要先给dest赋值，大小和list.size()一样
        ArrayList dest = new ArrayList(9);
        for (int i = 0; i < list.size(); i++) {
            dest.add("");
        }
        Collections.copy(dest, list);
        Collections.replaceAll(list, "tom", "汤姆");
    }
}

























