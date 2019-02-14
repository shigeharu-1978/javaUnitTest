package spockexample;

import lombok.Data;

/**
 * 人を表すクラス。 年齢と性別を属性として持つ。
 */
@Data
public class Person {
	/** 性別("m" or "f") */
	private String sex;
	/** 年齢 */
	private int age;

	public Person(String sex, int age) {
		this.sex = sex;
		this.age = age;
	}
}