package spockexample;

/**
 * Personに対して男性かどうかを判定するisMeleメソッドや、 大人かどうかを判定するisAdultメソッドを持つクラス
 * @author user
 *
 */
public class PersonChecker {
	/**
	 * Personが大人(20歳以上)かどうかを返す
	 * 
	 * @param person 判定する人
	 * @return 大人であればtrue、そうでなければfalse
	 */
	public boolean isAdult(Person person) {
		return person.getAge() >= 20;
	}

	/**
	 * 男性かどうかを判定する
	 * 
	 * @param person 判定する人
	 * @return 男性であればtrue、そうでなければfalse
	 */
	public boolean isMale(Person person) {
		return "m".equals(person.getSex());
	}
}