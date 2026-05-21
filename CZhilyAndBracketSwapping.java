import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class CZhilyAndBracketSwapping {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            char a[] = fs.next().toCharArray();
            char b[] = fs.next().toCharArray();

            boolean valid = true;

            if (a[0] != '(' || b[0] != '(' || a[n - 1] != ')' || b[n - 1] != ')') {
                valid = false;
            }

            int ct = 1;
            int dif = 0;
            for (int i = 1; i < n - 1; i++) {
                if (a[i] == b[i]) {
                    if (a[i] == '(')
                        ct++;
                    else
                        ct--;
                } else {
                    dif++;
                }

                if ((ct == 0 && dif % 2 == 1) || ct < 0)
                    valid = false;
            }
            ct--;

            if (ct != 0 || dif % 2 != 0)
                valid = false;

            if (valid) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    /*
     * since different brackets can only face one way
     * then if x is no of diff brackets
     * and a has p opening brackets then it needs p closing ones from x
     * and b will get x - p opening ones from x
     * 
     * so greedily give x - p opening ones to f at st indexes and p to closing one
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