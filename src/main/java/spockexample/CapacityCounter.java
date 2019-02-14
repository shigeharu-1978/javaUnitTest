package spockexample;

import lombok.Getter;

/**
 * キャパシティを数値で管理するクラス。
 */
@Getter
public class CapacityCounter {
	private int count = 0;

	public CapacityCounter() {
	}

	/**
	 * 指定された数だけカウントを加算する
	 * 
	 * @param count 加算するカウント
	 */
	public void addCount(int count) {
		this.count += count;
	}

	/**
	 * 指定された数だけカウントを減算する
	 * 
	 * @param count 減算するカウント
	 */
	public void reduceCount(int count) {
		this.count -= count;
	}

}