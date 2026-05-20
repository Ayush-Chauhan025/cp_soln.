import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class BZhilyAndMexAndMax {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            long a[] = new long[n];
            TreeSet<Long> set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
            }
            Arrays.sort(a);
            for (int i = 0; i < n - 1; i++) {
                set.add(a[i]);
            }
            long b[] = new long[n];
            b[0] = a[n - 1];
            int idx = 1;
            for (long val : set) {
                b[idx] = val;
                idx++;
            }
            for (; idx < n; idx++) {
                b[idx] = -1;
            }
            long ans = 0;
            long mex = 0;
            long max = 0;
            set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                max = Math.max(max, b[i]);
                set.add(b[i]);
                while (set.contains(mex))
                    mex++;

                ans += max + mex;
            }
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