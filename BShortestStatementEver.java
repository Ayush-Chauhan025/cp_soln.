import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class BShortestStatementEver {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            long x = fs.nextLong();
            long y = fs.nextLong();

            long p = x;
            long q = y;
            long p_mask = ~x & ((1L << 31) - 1);
            long q_mask = ~y & ((1L << 31) - 1);
            long ch1 = minimum(p_mask, y);
            long ch2 = minimum(q_mask, x);

            long a = abs(y - ch1);
            long b = abs(x - ch2);

            if (a <= b) {
                System.out.println(p + " " + ch1);
            } else {
                System.out.println(ch2 + " " + q);
            }
        }
    }

    public static long minimum(long mask, long target) {
        long min = Long.MAX_VALUE;
        long op = 0;
        long ac = 0;
        for (int i = 30; i >= 0; i--) {
            if ((mask & (1L << i)) == 0)
                continue;

            long chng = ac + (1L << i);
            if (abs(chng - target) < min) {
                min = abs(chng - target);
                op = chng;
            }

            if (chng <= target) {
                ac += (1L << i);
            }
        }

        if (abs(ac - target) < min) {
            op = ac;
        }

        return op;
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