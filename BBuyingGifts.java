import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class BBuyingGifts {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            ArrayList<int[]> list = new ArrayList<>();
            ArrayList<int[]> ls = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int ai = fs.nextInt();
                int bi = fs.nextInt();
                list.add(new int[] { ai, bi, i });
                ls.add(new int[] { ai, bi, i });
            }
            // sorted by a
            Collections.sort(list, (x, y) -> {
                if (x[0] == y[0])
                    return x[1] - y[1];
                else
                    return x[0] - y[0];
            });
            // sorted by b
            Collections.sort(ls, (x, y) -> {
                if (x[1] == y[1])
                    return x[0] - y[0];
                else
                    return x[1] - y[1];
            });
            int ans = Integer.MAX_VALUE;
            // min index of a so that every other is pos
            int mn = n;
            int l = 0;
            int r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                int val = list.get(mid)[0];
                boolean valid = true;
                for (int i = 0; i < n; i++) {
                    int mini = min(list.get(i)[0], list.get(i)[1]);
                    if (mini > val)
                        valid = false;
                }

                if (valid) {
                    mn = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            for (int i = mn; i < n; i++) {
                int val = list.get(i)[0];
                int idx_a = list.get(i)[2];
                l = 0;
                r = n - 1;
                int mx = -1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    int value = ls.get(mid)[1];
                    int idx_b = ls.get(mid)[2];

                    if (idx_a != idx_b) {
                        if (value <= val) {
                            mx = value;
                            l = mid + 1;
                        } else {
                            r = mid - 1;
                        }
                    } else {
                        if (mid > 0 && ls.get(mid - 1)[1] <= val) {
                            mx = ls.get(mid - 1)[1];
                        }
                        l = mid + 1;
                    }
                }

                if (mx != -1)
                    ans = min(ans, abs(mx - val));
            }

            mn = n;
            l = 0;
            r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                int val = ls.get(mid)[1];
                boolean valid = true;
                for (int i = 0; i < n; i++) {
                    int mini = min(ls.get(i)[0], ls.get(i)[1]);
                    if (mini > val)
                        valid = false;
                }

                if (valid) {
                    mn = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            for (int i = mn; i < n; i++) {
                int val = ls.get(i)[1];
                int idx_b = ls.get(i)[2];
                l = 0;
                r = n - 1;
                int mx = -1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    int value = list.get(mid)[0];
                    int idx_a = list.get(mid)[2];
                    if (idx_a != idx_b) {
                        if (value <= val) {
                            mx = value;
                            l = mid + 1;
                        } else {
                            r = mid - 1;
                        }
                    } else {
                        if (mid > 0 && list.get(mid - 1)[0] <= val) {
                            mx = list.get(mid - 1)[0];
                        }
                        l = mid + 1;
                    }
                }

                if (mx != -1)
                    ans = min(ans, abs(mx - val));
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