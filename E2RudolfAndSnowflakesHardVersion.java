import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class E2RudolfAndSnowflakesHardVersion {
    static final int mod = (int) 1e9 + 7;
    public static long upr_bd[] = new long[62];

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        upr_bd[0] = 1;
        for (int i = 1; i <= 61; i++) {
            upr_bd[i] = calc(i);
        }
        int t = fs.nextInt();
        while (t-- > 0) {
            long n = fs.nextLong();
            boolean valid = false;

            for (int i = 2; i <= 61; i++) {
                // last power in eqn is i
                long l = 2;
                long r = upr_bd[i];

                while (l <= r) {
                    long mid = l + (r - l) / 2;
                    long prod = 1;
                    long num = n;
                    for (int j = 0; j <= i; j++) {
                        num -= prod;
                        if (j != i)
                            prod *= mid;
                        if (num < 0)
                            break;
                    }

                    if (num == 0) {
                        valid = true;
                        break;
                    } else if (num < 0) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }

                if (valid)
                    break;
            }

            System.out.println(valid ? "YES" : "NO");
        }
    }

    public static long calc(int idx) {
        long l = 1;
        long r = (long) (1e18);
        long ans = 1;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            boolean valid = true;
            long prod = 1;
            for (int i = 1; i <= idx; i++) {
                if (prod < (long) 1e18 && mid <= (long) (1e18 + prod - 1) / prod) {
                    prod *= mid;
                } else {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /*
     * eqn. 1 + k + k^2 + k^3 + ....
     * 
     * prod * k <= 1e18
     * k <= 1e18/prod
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