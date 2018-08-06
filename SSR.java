package p2;

/**
 * @author ivy
 *
 */

public class SSR {

	private String base;//子串
	private int repeats;//重复次数
	private int index;//位置
	
	public SSR(String base,int repeats,int index) {
		this.base=base;
		this.index=index;
		this.repeats=repeats;
	}
	
	/**
	 * auto get and set
	 *
	 */
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public int getRepeats() {
		return repeats;
	}
	public void setRepeats(int repeats) {
		this.repeats = repeats;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
