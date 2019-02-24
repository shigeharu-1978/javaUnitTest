package spockexample;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AttractionRoomTest {
	CapacityCounter capacityCounter;
	AttractionRoom sut;
	PersonChecker ageChecker;

	@Before
	public void setup() {
		sut = new AttractionRoom();
		ageChecker = new PersonChecker();
		// capacityCounter をモック化
		capacityCounter = mock(CapacityCounter.class);
		// getCount()が常に19を返すようスタブを宣言する
		// 19を返す場合、子どもなら追加できるが、大人は追加できない
		when(capacityCounter.getCount()).thenReturn(19);
		/*
		 * こっちの書き方でも可 def capacityCounter = Mock(CapacityCounter)
		 * Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
		 */
		sut.setPersonChecker(ageChecker);
		sut.setCapacityCounter(capacityCounter);
	}

	@Test(expected=IllegalArgumentException.class)
	public void test1() {
		Person person1 = new Person("m", 19);
		sut.add(person1);
		verify(capacityCounter, times(1)).addCount(1);
		Person person2 = new Person("f", 20);
		sut.add(person2);
	}
}
