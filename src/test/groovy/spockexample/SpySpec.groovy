package spockexample

import spock.lang.Specification

class SpySpec extends Specification {

    def "実際の処理を行いつつスパイで多重化テスト"() {
        setup:
        def sut = new AttractionRoom()
        def ageChecker = new PersonChecker()
        /*
         *  カウンターをスパイとして作成
         */
        CapacityCounter capacityCounter = Spy(CapacityCounter)

        // Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
        sut.personChecker = ageChecker
        sut.capacityCounter = capacityCounter

        when:
        /*
         *  20歳女性の場合
         */
        def person1 = new Person("f", 20)
        /*
         *  19歳女性の場合
         */
        def person2 = new Person("f", 19)
        // 20歳男性の場合
        def person3 = new Person("m", 20)
        // 19歳男性の場合
        def person4 = new Person("m", 19)

        sut.add(person1)
        sut.add(person2)
        sut.add(person3)
        sut.add(person4)

        then:
        /*
         *  スパイにより、メソッド呼び出しが監視できる
         */
        1 * capacityCounter.addCount(3)
        1 * capacityCounter.addCount(2)
        2 * capacityCounter.addCount(1)
        // 0 * はthen:ブロックの最後に書くこと
        0 * capacityCounter.reduceCount(_)

        expect:
        /*
         *  モックではなくスパイを使うことで、本物の振る舞いも確認できる
         */
        capacityCounter.getCount() == 7
    }
}
