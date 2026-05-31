import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class F1OmskMetroSimpleVersion {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int mx[] = new int[n + 3];
            int mn[] = new int[n + 3];
            int mx_sum[] = new int[n + 3];
            int mn_sum[] = new int[n + 3];
            int ct = 1;
            mx[1] = 1;
            mn[1] = 0;
            mx_sum[1] = 1;
            mn_sum[1] = 0;
            for (int i = 0; i < n; i++) {
                char ch = fs.next().charAt(0);
                if (ch == '+') {
                    int par = fs.nextInt();
                    int wt = fs.nextInt();
                    ct++;
                    int mx_nxt = mx_sum[par] + wt;
                    int mn_nxt = mn_sum[par] + wt;
                    mx[ct] = Math.max(mx[par], mx_nxt);
                    mn[ct] = Math.min(mn[par], mn_nxt);
                    if (mx_nxt < 0)
                        mx_nxt = 0;
                    if (mn_nxt > 0)
                        mn_nxt = 0;
                    mx_sum[ct] = mx_nxt;
                    mn_sum[ct] = mn_nxt;
                } else {
                    int u = fs.nextInt();
                    int v = fs.nextInt();

                    int k = fs.nextInt();
                    int mxr = mx[v];
                    int mnr = mn[v];
                    if (k >= mnr && k <= mxr) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
            }
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