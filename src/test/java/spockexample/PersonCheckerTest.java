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
public class PersonCheckerTest {

    @RunWith(Theories.class)
    public static class isAdultTest {
        static PersonChecker sut;

        @Before
        public void setup() {
            sut = new PersonChecker();
        }

        /**
         * パラメータ化テストのパラメータとなるFixture定義
         */
        @AllArgsConstructor
        static class Fixture {
            int age;
            String sex;
            boolean result;
        }

        /**
         * テストに使用するパラメータを定義
         */
        @DataPoints
        public static Fixture[] fixtures = {
                new Fixture(0, "m", false), 
                new Fixture(19, "m", false),
                new Fixture(20, "m", true), 
                new Fixture(0, "f", false), 
                new Fixture(19, "f", false),
                new Fixture(20, "f", true),};
        @DataPoint
        public static int errTest = 1;

        @Theory
        public void testIsAdult(Fixture fixture) {
            // Fixtureの値を使ってPersonオブジェクトを初期化
            Person person = new Person(fixture.sex, fixture.age);

            /*
             * テストメソッド実行＆結果判定
             */
            assertThat(sut.isAdult(person), is(fixture.result));
            System.out.println(fixture.age + "歳で性別が" + fixture.sex + "の場合に大人かどうかの判定で"
                    + fixture.result + "が返る()");
        }

        @Theory
        public void errTestIsAdult(int errTest) {
            if (errTest == 1) {
                try {
                    sut.isAdult(null);
                } catch (IllegalArgumentException e) {
                    assertEquals("nullは許可されていません", e.getMessage());
                    System.out.println("メッセージ\"nullは許可されていません\"を出力成功");
                }
            }
        }
    }

    @RunWith(Theories.class)
    public static class isMaleTest {
        static PersonChecker sut;

        @Before
        public void setup() {
            sut = new PersonChecker();
        }

        /**
         * パラメータ化テストのパラメータとなるFixture定義
         */
        static class Fixture {
            int age;
            String sex;
            boolean result;

            Fixture(int age, String sex, boolean expected) {
                this.age = age;
                this.sex = sex;
                this.result = expected;
            }
        }

        /**
         * テストに使用するパラメータを定義
         */
        @DataPoints
        public static Fixture[] fixtures = {
                new Fixture(19, "m", true), 
                new Fixture(20, "m", true),
                new Fixture(19, "f", false), 
                new Fixture(20, "f", false),};
        @DataPoint
        public static int errTest = 1;
        @Theory
        public void testIsMale(Fixture fixture) {
            // Fixtureの値を使ってPersonオブジェクトを初期化
            Person person = new Person(fixture.sex, fixture.age);

            /*
             * テストメソッド実行＆結果判定
             */
            assertThat(sut.isMale(person), is(fixture.result));
            System.out.println(fixture.age + "歳で性別が" + fixture.sex + "の場合に、男性化どうかの判定で"
                    + fixture.result + "が返る()");
        }
        @Theory
        public void errTestIsMale(int errTest) {
            if (errTest == 1) {
                try {
                    sut.isMale(null);
                } catch (IllegalArgumentException e) {
                    assertEquals("nullは許可されていません", e.getMessage());
                    System.out.println("メッセージ\"nullは許可されていません\"を出力成功");
                }
            }
        }
    }
}
