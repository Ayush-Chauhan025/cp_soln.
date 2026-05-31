import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class BCakeLeveling {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            long a[] = new long[n];

            for (int i = 0; i < n; i++) {
                a[i] = fs.nextLong();
            }
            long prev_max = a[0];
            System.out.print(a[0] + " ");
            long wasted = 0;
            for (int i = 1; i < n; i++) {
                long ct = i;
                long ht = a[i] + wasted;
                if (prev_max > ht) {
                    long l = 1;
                    long r = prev_max;
                    long max = prev_max;
                    while (l <= r) {
                        long mid = l + (r - l) / 2;
                        if (mid * (ct + 1) <= (ct * prev_max + ht)) {
                            max = mid;
                            l = mid + 1;
                        } else {
                            r = mid - 1;
                        }
                    }
                    wasted = ((ct * prev_max + ht) - (max * (ct + 1)));
                    prev_max = max;
                } else {
                    wasted = ht - prev_max;
                }
                System.out.print(prev_max + " ");
            }

            System.out.println();
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