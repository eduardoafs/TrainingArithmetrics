import org.junit.jupiter.api.Test;
import tads.ufrn.br.BigInt;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BigIntTest {
    @Test
    public void testNewBigIntEmpty() {
        BigInt intZero = new BigInt();

        assertEquals("0", intZero.toString());
    }

    @Test
    public void testNewBigIntString() {
        BigInt bigInt = new BigInt("3292727864325722739278");

        assertEquals("3292727864325722739278", bigInt.toString());
    }

    @Test
    public void testNewBigIntStringNegative() {
        BigInt bigInt = new BigInt("-3292727864325722739278");

        assertEquals("-3292727864325722739278", bigInt.toString());
    }

    @Test
    public void testBigIntInt() {
        BigInt bigInt = new BigInt(2344498);

        assertEquals("2344498", bigInt.toString());
    }

    @Test
    public void testBigIntNegativeInt() {
        BigInt bigInt = new BigInt(-2344498);

        assertEquals("-2344498", bigInt.toString());
    }

    @Test
    public void testBigIntNegativeZero() {
        BigInt bigInt = new BigInt("-0");
        assertEquals("0", bigInt.toString());
    }

    @Test
    public void testBigIntInvalidCharDot() {
        assertThrows(InvalidParameterException.class, () -> {
            BigInt bigInt = new BigInt("5541.2");
        });
    }

    @Test
    public void testBigIntInvalidCharSpace() {
        assertThrows(InvalidParameterException.class, () -> {
            BigInt bigInt = new BigInt("5541898321 2");
        });
    }

    @Test
    public void testBigIntInvalidCharLetter() {
        assertThrows(InvalidParameterException.class, () -> {
            BigInt bigInt = new BigInt("5568909312321i2");
        });
    }

    @Test
    public void testSumTwoBigInts() {
        BigInt a = new BigInt("111111111111111111111111111");
        BigInt b = new BigInt("888888888888888888888888888888");

        assertEquals("888999999999999999999999999999", BigInt.sum(a,b).toString());
        assertEquals("111111111111111111111111111", a.toString());
        assertEquals("888888888888888888888888888888", b.toString());
    }

    @Test
    public void testSumWithCarry() {
        BigInt a = new BigInt("99999999999999999999999999999999999999999999");
        BigInt b = new BigInt("1");

        assertEquals("100000000000000000000000000000000000000000000", BigInt.sum(a,b).toString());
        assertEquals("99999999999999999999999999999999999999999999", a.toString());
        assertEquals("1", b.toString());
    }

    @Test
    public void testSumNegative() {
        BigInt a = new BigInt("2222222222222222");
        BigInt b = new BigInt("-1111111111111111");

        assertEquals("1111111111111111", BigInt.sum(a, b).toString());
    }

    @Test
    public void testSumTwoNegative() {
        BigInt a = new BigInt("-111111111111111111111111111");
        BigInt b = new BigInt("-888888888888888888888888888888");

        assertEquals("-888999999999999999999999999999", BigInt.sum(a,b).toString());
    }

    @Test
    public void testSumResultNegative() {
        BigInt a = new BigInt("-2222222222222222");
        BigInt b = new BigInt("1111111111111111");

        assertEquals("-1111111111111111", BigInt.sum(a, b).toString());
    }

    @Test
    public void testSubNormal() {
        BigInt a = new BigInt("2222222222222222");
        BigInt b = new BigInt("1111111111111111");

        assertEquals("1111111111111111", BigInt.sub(a, b).toString());
    }

    @Test
    public void testSubNegative() {
        BigInt a = new BigInt("-2222222222222222");
        BigInt b = new BigInt("1111111111111111");

        assertEquals("-3333333333333333", BigInt.sub(a, b).toString());
    }

    @Test
    public void testSubWithCarry() {
        BigInt a = new BigInt("10000000000000000000");
        BigInt b = new BigInt(1);

        assertEquals("9999999999999999999", BigInt.sub(a,b).toString());
    }

    @Test
    public void testSubNegative2WithCarry() {
        BigInt a = new BigInt("999999999999999");
        BigInt b = new BigInt(-1);

        assertEquals("1000000000000000", BigInt.sub(a,b).toString());
    }

    @Test
    public void testMulZero() {
        BigInt a = new BigInt();
        BigInt b = new BigInt(521521231);

        assertEquals("0", BigInt.mul(a,b).toString());
    }

    @Test
    public void testMulNormal() {
        BigInt a = new BigInt(3);
        BigInt b = new BigInt(7);

        assertEquals("21", BigInt.mul(a, b).toString());
    }

    @Test
    public void testMulNegative() {
        BigInt a = new BigInt(3);
        BigInt b = new BigInt(-7);

        assertEquals("-21", BigInt.mul(a, b).toString());
    }

    @Test
    public void testMulNegative2() {
        BigInt a = new BigInt(-3);
        BigInt b = new BigInt(7);

        assertEquals("-21", BigInt.mul(a, b).toString());
    }

    @Test
    public void testMulDoubleNegative() {
        BigInt a = new BigInt(-3);
        BigInt b = new BigInt(-7);

        assertEquals("21", BigInt.mul(a, b).toString());
    }

    @Test
    public void testDivZero() {
        BigInt a = new BigInt();
        BigInt b = new BigInt(521521231);

        assertEquals("0", BigInt.div(a,b).toString());
    }

    @Test
    public void testDivZeroException() {
        BigInt a = new BigInt(521521231);
        BigInt b = new BigInt();

        assertThrows(ArithmeticException.class, () -> {
           BigInt.div(a,b);
        });
    }

    @Test
    public void testDivRegular() {
        BigInt a = new BigInt(111);
        BigInt b = new BigInt(3);

        assertEquals("37", BigInt.div(a,b).toString());
    }

    @Test
    public void testDivNegative() {
        BigInt a = new BigInt(-111);
        BigInt b = new BigInt(3);

        assertEquals("-37", BigInt.div(a,b).toString());
    }

    @Test
    public void testDivNegative2() {
        BigInt a = new BigInt(111);
        BigInt b = new BigInt(-3);

        assertEquals("-37", BigInt.div(a,b).toString());
    }

    @Test
    public void testDivDoubleNegative() {
        BigInt a = new BigInt(-111);
        BigInt b = new BigInt(-3);

        assertEquals("37", BigInt.div(a,b).toString());
    }

    @Test
    public void testDivNonExact() {
        BigInt a = new BigInt(113);
        BigInt b = new BigInt(3);

        assertEquals("37", BigInt.div(a,b).toString());
    }
}
