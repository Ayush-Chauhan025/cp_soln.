import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DRatingSystem {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
            }
            long mnt[] = new long[n + 2];
            for (int i = 1; i <= n; i++) {
                long val = a[i - 1];
                mnt[i] = mnt[i - 1] + val;
            }

            long rating = mnt[n];
            long k = 0;
            long suf[] = new long[n + 2];
            suf[n + 1] = Long.MAX_VALUE;
            for (int i = n; i >= 1; i--) {
                suf[i] = Math.min(suf[i + 1], mnt[i]);
            }

            for (int i = 0; i <= n; i++) {
                boolean isPeak = (i == 0 || mnt[i - 1] <= mnt[i]) && (i == n || mnt[i] >= mnt[i + 1]);
                if (isPeak) {
                    long nxt = mnt[n] + mnt[i] - min(mnt[n], suf[i + 1]);
                    if (nxt >= rating) {
                        rating = nxt;
                        k = mnt[i];
                    }
                }
            }

            System.out.println(k);
        }
    }

    /*
     * for a k increase in the final value is then max(|len|) increase under k
     * 
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