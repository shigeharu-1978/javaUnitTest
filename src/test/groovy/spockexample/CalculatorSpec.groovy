package spockexample
import spock.lang.Specification
import spock.lang.Unroll

class CalculatorSpec extends Specification {

  @Unroll
  def add() {
	given:
	  def calc = new Calculator()

	when:
	  def result = calc.add(a, b)

	then:
	  result == expected

	where:
	  a  | b  | expected
	  1  | 1  | 2
	  0  | 0  | 0
	  1  | 0  | 1
	  0  | 1  | 1
  }
}