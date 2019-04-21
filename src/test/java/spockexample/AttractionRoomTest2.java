package spockexample;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import lombok.AllArgsConstructor;

@RunWith(Enclosed.class)
public class AttractionRoomTest2 {

    @RunWith(Theories.class)
    public static class addTest {
        static CapacityCounter cc;
        static PersonChecker sut;
        static AttractionRoom ar;

        @Before
        public void setup() {
            sut = new PersonChecker();
            cc = new CapacityCounter();
            ar = new AttractionRoom();
            ar.setCapacityCounter(cc);
            ar.setPersonChecker(sut);
        }

        /**
         * パラメータ化テストのパラメータとなるFixture定義
         */
        @AllArgsConstructor
        static class Fixture {
            int age;
            String sex;
            int result;
            int loop;
        }

        /**
         * テストに使用するパラメータを定義
         */
        @DataPoints
        public static Fixture[] fixtures = {
                new Fixture(19, "m", 1, 1), 
                new Fixture(20, "m", 3, 1), 
                new Fixture(20, "f", 2, 1), 
                new Fixture(20, "f", 20, 10), 
                new Fixture(20, "m", 21, 7) 
                };
        @DataPoint
        public static int errTest = 1;

        @Theory
        public void testIsAdult(Fixture fixture) {
            // Fixtureの値を使ってPersonオブジェクトを初期化
            Person person = new Person(fixture.sex, fixture.age);

            /*
             * テストメソッド実行＆結果判定
             */
            try {
                for (int i = 0; i < fixture.loop; i++) {
                    ar.add(person);
                }
                assertThat(ar.getCount(), is(fixture.result));
                System.out.println(fixture.age + "歳で性別が" + fixture.sex + "の場合にキャパシティカウントが"
                        + fixture.result + "を返る()");
            } catch (IllegalArgumentException e) {
                final String message = "limit over";
                assertEquals(message, e.getMessage());
                System.out.println("メッセージ\""+ message + "\"x出力成功");
            }
        }

        @Theory
        public void errTestIsAdult(int errTest) {
            if (errTest == 1) {
                try {
                    ar.add(null);
                } catch (IllegalArgumentException e) {
                    assertEquals("nullは許可されていません", e.getMessage());
                    System.out.println("メッセージ\"nullは許可されていません\"を出力成功");
                }
            }
        }
    }
}
