package spockexample

import static org.junit.Assert.*

import spock.lang.Specification
import spock.lang.Unroll

class AttractionRoom2Spec extends Specification {

	def "ポイント加算のメソッドが正しく呼ばれているか"() {
		setup:
		/*
		 *  テスト対象の初期化
		 */
		def sut = new AttractionRoom()
		def ageChecker = new PersonChecker()
		/*
		 *  カウンターをモック化
		 */
		CapacityCounter capacityCounter = Mock()
		/*
		 *  こっちの書き方でも可
		 *  def capacityCounter = Mock(CapacityCounter)
		 *  Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
		 */
		sut.personChecker = ageChecker
		sut.capacityCounter = capacityCounter

		when:
		// 20歳女性の場合
		def person1 = new Person("f", 20)
		sut.add(person1)
		then:
		// 2ポイント加算されるメソッドが1度呼ばれたはず
		1 * capacityCounter.addCount(2)

		when:
		// 19歳女性の場合
		def person2 = new Person("f", 19)
		sut.add(person2)
		then:
		// 1ポイント加算されるメソッドが1度呼ばれたはず
		1 * capacityCounter.addCount(1)

		when:
		// 20歳男性の場合
		def person3 = new Person("m", 20)
		sut.add(person3)
		then:
		// 3ポイント加算されるメソッドが1度呼ばれたはず
		1 * capacityCounter.addCount(3)

		when:
		// 19歳男性の場合
		def person4 = new Person("m", 19)
		sut.add(person4)
		then:
		// 1ポイント加算されるメソッドが1度呼ばれたはず
		1 * capacityCounter.addCount(1)
	}
}
