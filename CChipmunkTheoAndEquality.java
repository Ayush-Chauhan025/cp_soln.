import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class CChipmunkTheoAndEquality {
    static final int inf = (int) 1e9 + 2;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
            }

            long[] list = new long[n * 65];
            int ptr = 0;
            for (int i = 0; i < n; i++) {
                int val = a[i];
                int ct = 0;
                while (val > 1) {
                    list[ptr++] = getKey(val, ct);

                    if (val % 2 == 0) {
                        val /= 2;
                    } else {
                        val++;
                    }
                    ct++;
                }
                if (a[i] == 1) {
                    list[ptr++] = getKey(2, 1);
                }
                list[ptr++] = getKey(val, ct);
            }

            long ans = inf;
            long last = -1;
            long tot = 0;
            long steps = 0;
            Arrays.sort(list, 0, ptr);

            for (int i = 0; i < ptr; i++) {
                long val = list[i] >> 32;
                long ct = list[i] & 0xffffff;

                if (val == last) {
                    tot++;
                    steps += ct;
                } else {
                    if (tot == n)
                        ans = min(ans, steps);

                    last = val;
                    tot = 1;
                    steps = ct;
                }
            }
            if (tot == n)
                ans = min(ans, steps);

            System.out.println(ans);
        }
    }

    public static long getKey(long steps, long count) {
        return (steps << 32 | count);
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