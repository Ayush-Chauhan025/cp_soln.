import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class CArrangeTheNumbersInACircle {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            long count[] = new long[n];
            int c2 = 0;
            int c3 = 0;
            int c1 = 0;
            for (int i = 0; i < n; i++) {
                count[i] = (long) fs.nextInt();
                if (count[i] == 1)
                    c1++;
                if (count[i] >= 2)
                    c2++;
                if (count[i] >= 3)
                    c3++;
            }

            if (!(c3 > 0) && !(c2 > 1) && !(c2 == 1 && c1 > 0)) {
                System.out.println(0);
                continue;
            }

            long ans = 0;
            long ct_1 = 0;
            int ct = 0;
            for (int i = 0; i < n; i++) {
                if (count[i] == 1)
                    ct_1++;
                else {
                    ans += count[i];
                    if (count[i] == 3 || count[i] == 2)
                        ct++;
                    if (count[i] >= 4) {
                        ct++;
                        long rem = count[i] % 4;
                        long dis = 0;
                        if (rem == 0 || rem == 1) {
                            dis = 2 * (count[i] / 4) - 1;
                        } else {
                            dis = 2 * (count[i] / 4);
                        }
                        long min = Math.min(dis, ct_1);
                        ans += min;
                        ct_1 -= min;
                    }
                }
            }
            if (ct == 1 && ct_1 > 0)
                ans++;

            System.out.println(ans);
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