package p2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author ivy
 *
 */
public class KMER {

	/**
	 * 寻找SSR
	 * 
	 * @param p
	 * @param s
	 * @param index
	 *
	 */
	public static SSR searchForSSR(int p, String s, int index) {
		// System.out.println("////当前位置："+index);
		String base, next;
		int repeats = 0;
		int pos = index;
		char[] chars = s.toCharArray();
		base = String.copyValueOf(chars, pos, p);// 待定ssr
		next = String.copyValueOf(chars, pos, p);// 后续等长子串
		while (base.equals(next) && pos < (s.length() - p - 1)) {// 在相邻碱基序列匹配的情况下持续循环
			repeats += 1;
			pos += p;
			if (pos + p <= chars.length) {
				next = String.copyValueOf(chars, pos, p);
			}
		}
		return new SSR(base, repeats, index);
	}

	/**
	 * Array数组校验函数
	 * 
	 * @param F
	 * @param ssrStartPos
	 * @param ssrStopPos
	 *
	 */
	public static boolean passesBooleanFilter(boolean[] F, int ssrStartPos, int ssrStopPos) {
		for (int i = ssrStartPos; i <= ssrStopPos; i++) {
			if (!F[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 打印SSR
	 * 
	 * @param ssr
	 *
	 */
	public static void print(SSR ssr) {
		System.out.print("第" + ssr.getIndex() + "位置，");
		System.out.print(ssr.getBase() + "重复");
		System.out.println(ssr.getRepeats() + "次");
	}

	public static void main(String[] args) {
		// 输入用户所需的Kmer长度（可多个）及待分析的DNA序列
		String s;
		Scanner reader = new Scanner(System.in);
		s = reader.next();
		int n = s.length();

//		int[] p = new int[n];
//		for (int i = 0; i < n; i++) {
//			p[i] = i + 1;
//		}
//		Arrays.sort(p);
		int[] p = {2,3,4,5,6};
		boolean[] F = new boolean[n];// 生成与所输入DNA序列长度一致的布尔型数组并将所有元素初始化为flase
		
		Map<String, SSR> map = new HashMap<String, SSR>();// 暂时使用map

		for (int k = 0; k < p.length; k++) {
			System.out.println(p[k]);
			for (int i = 0; i < n; i++) {
				F[i] = false;
			}
			for (int j = 0; j < s.length() - p[k]; j++) {
				SSR ssr = searchForSSR(p[k], s, j);
				int ssrStartPos = ssr.getIndex();// 起始位置
				int ssrStopPos = ssr.getIndex() + p[k] - 1;// 结束位置
				if (passesBooleanFilter(F, ssrStartPos, ssrStopPos)) {
					if (!map.containsKey(ssr.getBase()) || map.get(ssr.getBase()).getRepeats() < ssr.getRepeats()) {
						if (ssr.getRepeats() > 1) {
							map.put(ssr.getBase(), ssr);
						}
					}
					if (ssr.getRepeats() > 1) {
						print(ssr); // 打印ssr
					}

					for (int x = ssrStartPos; x <= ssrStopPos; x++) {
						F[x] = true;
					}
					// j += (ssr.getBase().length() * ssr.getRepeats() - p[k] + 1);// 更新位置
				}

			}
		}

		// System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		// System.out.println(map.size());
		// // 打印
		// Iterator<Entry<String, SSR>> iterator = map.entrySet().iterator();
		// while (iterator.hasNext()) {// 用while循环，判断是否有下一个
		// Entry<String, SSR> entry = iterator.next();// 声明entry，并用它来装载字符串
		// System.out.print("第" + entry.getValue().getIndex() + "位置，");
		// System.out.print(entry.getValue().getBase() + "重复");
		// System.out.println(entry.getValue().getRepeats() + "次");
		// System.out.println("=============");
		// }

	}

}