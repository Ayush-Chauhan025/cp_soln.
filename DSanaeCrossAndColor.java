import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DSanaeCrossAndColor {
    static final int mod = (int) 1e9 + 7;
    public static int inf = (int) (1e8);
    public static int sz = (int) 2e6 + 5;
    public static int min_y[] = new int[sz];
    public static int max_y[] = new int[sz];
    public static int pref_min[] = new int[sz];
    public static int pref_max[] = new int[sz];
    public static int suf_min[] = new int[sz];
    public static int suf_max[] = new int[sz];
    public static int y_pts[] = new int[sz];

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        Arrays.fill(min_y, inf);
        Arrays.fill(pref_min, inf);
        Arrays.fill(suf_min, inf);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int mn_x = inf;
            int mx_x = -1;

            int mx_y = -1;
            for (int i = 0; i < n; i++) {
                int x = fs.nextInt();
                int y = fs.nextInt();

                mn_x = min(mn_x, x);
                mx_x = max(mx_x, x);
                mx_y = max(mx_y, y);

                min_y[x] = min(min_y[x], y);
                max_y[x] = max(max_y[x], y);

                if (y_pts[y] == 0)
                    y_pts[y]++;
            }

            pref_min[mn_x] = min_y[mn_x];
            suf_min[mx_x] = min_y[mx_x];
            pref_max[mn_x] = max_y[mn_x];
            suf_max[mx_x] = max_y[mx_x];

            for (int i = mn_x + 1; i <= mx_x; i++) {
                pref_min[i] = min(min_y[i], pref_min[i - 1]);
                pref_max[i] = max(max_y[i], pref_max[i - 1]);
            }

            for (int i = mx_x - 1; i >= mn_x; i--) {
                suf_min[i] = min(min_y[i], suf_min[i + 1]);
                suf_max[i] = max(max_y[i], suf_max[i + 1]);
            }

            for (int i = 1; i <= mx_y; i++) {
                y_pts[i] += y_pts[i - 1];
            }

            long ans = 0;
            for (int i = mn_x; i <= mx_x; i++) {
                if (min_y[i] == inf)
                    continue;
                int l = Math.max(pref_min[i], suf_min[i + 1]);
                int r = Math.min(pref_max[i], suf_max[i + 1]);

                if (l < r) {
                    ans += y_pts[r - 1] - y_pts[l - 1];
                }
            }

            System.out.println(ans);

            for (int i = mn_x; i <= mx_x; i++) {
                min_y[i] = inf;
                max_y[i] = 0;
                pref_min[i] = inf;
                suf_min[i] = inf;
                pref_max[i] = 0;
                suf_max[i] = 0;
            }

            for (int i = 0; i <= mx_y; i++) {
                y_pts[i] = 0;
            }
        }
    }

    /*
     * if an element share same x or y then they atmost can have two different color
     * pairs
     * 
     * need to find the no of valid y's such that each quad have atleast one point
     * 
     * can use bs for finding the no of y's available
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