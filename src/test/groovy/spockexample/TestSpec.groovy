package spockexample

import static org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import spock.lang.Specification

class TestSpec extends Specification {

    def "スタブによってモック化したオブジェクトの振る舞いを定義する"() {
        setup:
        def sut = new AttractionRoom()
        def ageChecker = new PersonChecker()
        def capacityCounter = Mock(CapacityCounter)
        // getCount()が常に19を返すようスタブを宣言する
        // 19を返す場合、子どもなら追加できるが、大人は追加できない
        capacityCounter.getCount() >> 19
        sut.personChecker = ageChecker
        sut.capacityCounter = capacityCounter

        when:
        def person2 = new Person("f", 20)
        sut.add(person2)

        then:
        thrown(IllegalArgumentException)
    }
}
