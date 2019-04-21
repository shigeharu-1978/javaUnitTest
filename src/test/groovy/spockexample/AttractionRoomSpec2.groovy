package spockexample
import spock.lang.Specification
import spock.lang.Unroll
class AttractionRoomSpec2 extends Specification {
    @Unroll
    def "#age歳で性別が#sexの場合にキャパシティカウントが#resultを返る()"() {
        setup:
        def sut = new PersonChecker()
        def cc = new CapacityCounter()
        def ar = new AttractionRoom()
        ar.capacityCounter = cc
        ar.personChecker = sut
        when:
        def per = new Person(sex, age)
        for (int i = 0; i < loop; i++) {
            ar.add(per)
        }
        then:
        ar.getCount() == result
        where:
        age | sex | loop || result
        19  | "m" | 1    || 1
        20  | "m" | 1    || 3
        20  | "f" | 1    || 2
        20  | "f" | 10   || 20
    }

    @Unroll
    def "#age歳で性別が#sex、Loopが#loopの場合にIllegalArgumentExceptionがスローされる"() {
        setup:
        def sut = new PersonChecker()
        def cc = new CapacityCounter()
        def ar = new AttractionRoom()
        ar.setCapacityCounter(cc)
        ar.setPersonChecker(sut)
        when:
        if (loop == 1) {
            ar.add(null)
        } else {
            def per = new Person(sex, age)
            for (int i = 0; i < loop; i++) {
                ar.add(per)
            }
        }
        then:
        def ex = thrown(IllegalArgumentException)
        ex.getMessage() == result
        where:
        age | sex | loop || result
        0   | null  |1   || "nullは許可されていません"
        20  | "m"   |7   || "limit over"
    }
}