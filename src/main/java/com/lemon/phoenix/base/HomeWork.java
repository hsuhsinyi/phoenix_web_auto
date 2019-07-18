package com.lemon.phoenix.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class HomeWork {

	public static void main(String[] args) {
		// Java变量及变量类型
		int[] arr = new int[3];
		arr[0] = 100;
		arr[1] = 200;
		arr[2] = 300;
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);

		// Java条件循环控制语句
		// 打印直角三角形
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}

		// 打印等边三角形
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j < 5 - i; j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= i; k++) {
				System.out.print("* ");
			}
			System.out.println("");
		}

		// Java三大集合
		// 作业1：
		// 1.往这个HashSet对象里添加如下String类型的数据：“张三”，“李四”，“王五”，“张三”，”赵六”
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("张三");
		hashSet.add("李四");
		hashSet.add("王五");
		hashSet.add("张三");
		hashSet.add("赵六");
		// 2.判断这个集合是否为空，并打印判断的结果。
		System.out.println(hashSet.isEmpty());
		// 3.打印这个集合的大小。
		System.out.println(hashSet.size());
		// 4.判断这个集合是否包含数据"王五"。
		System.out.println(hashSet.contains("王五"));
		// 5.将”张三”这条数据删掉。
		hashSet.remove("张三");
		// 6.利用循环获取set集合中的每个元素，并打印。
		for (String data : hashSet) {
			System.out.println(data);
		}
		// 7.将set转换成数组。并循环打印数组中的元素
		Object[] datas = hashSet.toArray();
		for (int i = 0; i < datas.length; i++) {
			System.out.println(datas[i]);
		}

		// 作业2：
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// 1.往这个HashMap对象里添加自己的姓名和年龄，键值分别为”name”和”age”。
		hashMap.put("name", "王五");
		hashMap.put("age", "28");
		// 2.判断这个集合是否为空，并打印判断的结果。
		System.out.println(hashMap.isEmpty());
		// 3.打印这个集合的大小。
		System.out.println(hashMap.size());
		// 4.判断这个集合是否包含数据“王五”，并打印判断结果。
		System.out.println(hashMap.containsValue("王五"));
		// 5.将map里的age删掉。
		hashMap.remove("age");
		// 6.获取map的所有键，并打印。
		Set<String> keyValues = hashMap.keySet();
		for (String key : keyValues) {
			System.out.println(key);
		}
		// 7.获取map的所有值，并打印。
		Collection<String> values = hashMap.values();
		for (String value : values) {
			System.out.println(value);
		}
		// 8.用两种方法获取map里所有的键值对，并打印。
		Set<Entry<String, String>> allKeyValues1 = hashMap.entrySet();
		for (Entry<String, String> entry : allKeyValues1) {
			System.out.println(entry);
		}
		Set<Entry<String, String>> allKeyValues2 = hashMap.entrySet();
		Iterator<Entry<String, String>> iterator = allKeyValues2.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		System.out.println("huanhang--");
		System.out.println('\n');
		System.out.println("huanhang-11-");
	}

}
