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
	 * Ѱ��SSR
	 * 
	 * @param p
	 * @param s
	 * @param index
	 *
	 */
	public static SSR searchForSSR(int p, String s, int index) {
		// System.out.println("////��ǰλ�ã�"+index);
		String base, next;
		int repeats = 0;
		int pos = index;
		char[] chars = s.toCharArray();
		base = String.copyValueOf(chars, pos, p);// ����ssr
		next = String.copyValueOf(chars, pos, p);// �����ȳ��Ӵ�
		while (base.equals(next) && pos < (s.length() - p - 1)) {// �����ڼ������ƥ�������³���ѭ��
			repeats += 1;
			pos += p;
			if (pos + p <= chars.length) {
				next = String.copyValueOf(chars, pos, p);
			}
		}
		return new SSR(base, repeats, index);
	}

	/**
	 * Array����У�麯��
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
	 * ��ӡSSR
	 * 
	 * @param ssr
	 *
	 */
	public static void print(SSR ssr) {
		System.out.print("��" + ssr.getIndex() + "λ�ã�");
		System.out.print(ssr.getBase() + "�ظ�");
		System.out.println(ssr.getRepeats() + "��");
	}

	public static void main(String[] args) {
		// �����û������Kmer���ȣ��ɶ��������������DNA����
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
		boolean[] F = new boolean[n];// ������������DNA���г���һ�µĲ��������鲢������Ԫ�س�ʼ��Ϊflase
		
		Map<String, SSR> map = new HashMap<String, SSR>();// ��ʱʹ��map

		for (int k = 0; k < p.length; k++) {
			System.out.println(p[k]);
			for (int i = 0; i < n; i++) {
				F[i] = false;
			}
			for (int j = 0; j < s.length() - p[k]; j++) {
				SSR ssr = searchForSSR(p[k], s, j);
				int ssrStartPos = ssr.getIndex();// ��ʼλ��
				int ssrStopPos = ssr.getIndex() + p[k] - 1;// ����λ��
				if (passesBooleanFilter(F, ssrStartPos, ssrStopPos)) {
					if (!map.containsKey(ssr.getBase()) || map.get(ssr.getBase()).getRepeats() < ssr.getRepeats()) {
						if (ssr.getRepeats() > 1) {
							map.put(ssr.getBase(), ssr);
						}
					}
					if (ssr.getRepeats() > 1) {
						print(ssr); // ��ӡssr
					}

					for (int x = ssrStartPos; x <= ssrStopPos; x++) {
						F[x] = true;
					}
					// j += (ssr.getBase().length() * ssr.getRepeats() - p[k] + 1);// ����λ��
				}

			}
		}

		// System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		// System.out.println(map.size());
		// // ��ӡ
		// Iterator<Entry<String, SSR>> iterator = map.entrySet().iterator();
		// while (iterator.hasNext()) {// ��whileѭ�����ж��Ƿ�����һ��
		// Entry<String, SSR> entry = iterator.next();// ����entry����������װ���ַ���
		// System.out.print("��" + entry.getValue().getIndex() + "λ�ã�");
		// System.out.print(entry.getValue().getBase() + "�ظ�");
		// System.out.println(entry.getValue().getRepeats() + "��");
		// System.out.println("=============");
		// }

	}

}