package spockexample;

public class PersonChecker {
	/**
	 * Personが大人(20歳以上)かどうかを返す
	 * 
	 * @param person 判定する人
	 * @return 大人であればtrue、そうでなければfalse
	 */
	public boolean isAdult(Person person) {
	    if (person == null) {
            throw new IllegalArgumentException("nullは許可されていません");
        }
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