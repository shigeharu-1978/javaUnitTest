package spockexample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 人を表すクラス。 年齢と性別を属性として持つ。
 */
@Getter
@AllArgsConstructor
public class Person {
	/** 性別("m" or "f") */
	private String sex;
	/** 年齢 */
	private int age;
}