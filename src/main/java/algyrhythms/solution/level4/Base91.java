package algyrhythms.solution.level4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * BasE91 - это метод кодирования двоичных данных в виде символов ASCII.
 * Он более эффективен, чем Base64, и для представления закодированных данных требуется 91 символ.
 * <p>
 * Используются следующие символы ASCII:
 * <p>
 * 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'
 * '!#$%&()*+,./:;<=>?@[]^_`{|}~"'
 * Создайте две функции, которые кодируют строки в строку basE91 и декодируют наоборот.
 * <p>
 * b91encode('test') = 'fPNKd'
 * b91decode('fPNKd') = 'test'
 * <p>
 * b91decode('>OwJh>Io0Tv!8PE') = 'Привет, мир!'
 * b91encode('Привет, мир!') = '>OwJh>Io0Tv!8PE'
 * Вводимые строки допустимы.
 */
public class Base91 {
    @Test
    public void fixedTests() {
        assertEquals(Base91.encode("test"), "fPNKd");
        assertEquals(Base91.encode("Hello World!"), ">OwJh>Io0Tv!8PE");
        assertEquals(Base91.decode("fPNKd"), "test");
        assertEquals(Base91.decode(">OwJh>Io0Tv!8PE"), "Hello World!");
    }

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&()*+,./:;<=>?@[]^_`{|}~\"";
    private static final byte[] ENCODE_ALPHABET = ALPHABET.getBytes();
    private static final byte[] DECODE_ALPHABET = new byte[256];
    private static final float AVERAGE_ENCODING_RATIO = 1.23f;
    private static final byte BASE = (byte) ALPHABET.length();

    static {
        Arrays.fill(DECODE_ALPHABET, (byte) -1);
        for (int i = 0; i < 91; i++) {
            DECODE_ALPHABET[ENCODE_ALPHABET[i]] = (byte) i;
        }
    }


    public static String encode(String in) {
        byte[] data = in.getBytes();
        int estimatedSize = (int) Math.ceil(data.length * AVERAGE_ENCODING_RATIO);
        ByteArrayOutputStream output = new ByteArrayOutputStream(estimatedSize);

        int ebq = 0;
        int en = 0;
        for (int i = 0; i < data.length; ++i) {
            ebq |= (data[i] & 255) << en;
            en += 8;
            if (en > 13) {
                int ev = ebq & 8191;

                if (ev > 88) {
                    ebq >>= 13;
                    en -= 13;
                } else {
                    ev = ebq & 16383;
                    ebq >>= 14;
                    en -= 14;
                }
                output.write(ENCODE_ALPHABET[ev % BASE]);
                output.write(ENCODE_ALPHABET[ev / BASE]);
            }
        }

        if (en > 0) {
            output.write(ENCODE_ALPHABET[ebq % BASE]);
            if (en > 7 || ebq > 90) {
                output.write(ENCODE_ALPHABET[ebq / BASE]);
            }
        }

        return output.toString();
    }


    public static String decode(String in) {
        byte[] data = in.getBytes();
        int dbq = 0;
        int dn = 0;
        int dv = -1;

        int estimatedSize = Math.round(data.length / AVERAGE_ENCODING_RATIO);
        ByteArrayOutputStream output = new ByteArrayOutputStream(estimatedSize);

        for (int i = 0; i < data.length; ++i) {
            if (DECODE_ALPHABET[data[i]] == -1){
                continue;
            }
            if (dv == -1) {
                dv = DECODE_ALPHABET[data[i]];
            } else {
                dv += DECODE_ALPHABET[data[i]] * BASE;
                dbq |= dv << dn;
                dn += (dv & 8191) > 88 ? 13 : 14;
                do {
                    output.write((byte) dbq);
                    dbq >>= 8;
                    dn -= 8;
                } while (dn > 7);
                dv = -1;
            }
        }

        if (dv != -1) {
            output.write((byte) (dbq | dv << dn));
        }
        return output.toString();
    }
}

