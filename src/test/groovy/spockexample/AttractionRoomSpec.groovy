package spockexample

import static org.junit.Assert.*

import spock.lang.Specification
import spock.lang.Unroll

class AttractionRoomSpec extends Specification {

	@Unroll
	def "例外のテスト"() {
		setup:
		/*
		 * テスト対象の初期化
		 */
		def sut = new AttractionRoom()

		when:
		sut.add(null)

		then:
		// IllegalArgumentExceptionがスローされるはず
		def ex = thrown(IllegalArgumentException)
		// Exceptionのメッセージは「null is not acceptable.」のはず
		ex.getMessage() == "nullは許可されていません"
	}

}
