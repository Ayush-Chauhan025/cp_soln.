import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DMoscowGorillas {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = 1;
        while (t-- > 0) {
            int n = fs.nextInt();
            int a[] = new int[n + 1];
            int b[] = new int[n + 1];
            int loca[] = new int[n + 1];
            int locb[] = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = fs.nextInt();
                loca[a[i]] = i;
            }
            for (int i = 1; i <= n; i++) {
                b[i] = fs.nextInt();
                locb[b[i]] = i;
            }

            int l = -1;
            int r = -1;
            long ans = 1;
            for (int cur_mex = 1; cur_mex <= n; cur_mex++) {
                int locn_a = loca[cur_mex];
                int locn_b = locb[cur_mex];

                int mn = min(locn_a, locn_b);
                int mx = max(locn_a, locn_b);

                if (mn > r || mx < l) {
                    if (l == -1 && r == -1) {
                        long left = mn - 1;
                        long right = n - mx;
                        long mid = max(mx - mn - 1, 0);

                        ans += ((left) * (left + 1)) / 2 + ((right) * (right + 1)) / 2 + ((mid) * (mid + 1)) / 2;
                        l = mn;
                        r = mx;
                        // must include cur l to r
                    } else {
                        // mex on left
                        if (mx < l) {
                            long left = l - mx;
                            long right = n - r + 1;

                            ans += left * right;
                        } else {
                            long left = l;
                            long right = mn - r;

                            ans += left * right;
                        }
                        l = min(l, mn);
                        r = max(r, mx);
                    }
                } else if (mn < l && mx > r) {
                    long left = l - mn;
                    long right = mx - r;

                    ans += (left * right);
                    l = min(l, mn);
                    r = max(r, mx);
                } else {
                    l = min(l, mn);
                    r = max(r, mx);
                }
            }

            System.out.println(ans);
        }
    }

    /*
     * since window does not have monotonicty
     * can do the no of ranges for each index
     * for each mex i get the [l , r] for i - 1 mexes
     * if cur mex lies b/w l, r there does not exist a range where mex will be cur
     * mex
     * if cur_mex . . . [l , r] treat l, r as one element left most boundary is
     * cur_mex and
     * rightmose is n
     * and same for [l , r] ...... cur_mex
     * for n + 1 => +1
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