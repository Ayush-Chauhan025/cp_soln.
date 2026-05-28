import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DBinaryStringSorting {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            String s = fs.next();
            int n = s.length();
            long swap = (long) (1e12);
            long del = (long) (1e12) + 1;
            long pref[] = new long[n + 2];
            long suf[] = new long[n + 2];
            for (int i = 1; i <= n; i++) {
                char ch = s.charAt(i - 1);
                int val = 0;
                if (ch == '1')
                    val = 1;
                pref[i] = pref[i - 1] + val;
            }
            for (int i = n; i >= 1; i--) {
                char ch = s.charAt(i - 1);
                int val = 0;
                if (ch == '0')
                    val = 1;
                suf[i] = suf[i + 1] + val;
            }
            long min = suf[1] * del;
            for (int i = 1; i <= n; i++) {
                min = Math.min(min, (pref[i] + suf[i + 1]) * del);
                if (i > 1 && s.charAt(i - 1) == '0' && s.charAt(i - 2) == '1') {
                    // use swap
                    min = Math.min(min, (pref[i] - 1 + suf[i + 1]) * del + swap);
                }
            }
            System.out.println(min);
        }
    }

    /*
    
    
    */

    // FastScanner
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do
                c = read();
            while (c <= ' ');
            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long val = 0;
            do
                c = read();
            while (c <= ' ');
            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        String next() throws IOException {
            int c;
            StringBuilder sb = new StringBuilder();
            do
                c = read();
            while (c <= ' ');
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}