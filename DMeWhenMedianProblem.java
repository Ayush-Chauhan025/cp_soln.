import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DMeWhenMedianProblem {
    static final int mod = (int) 1e9 + 7;
    public static long inf = (long) 1e15;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int a[] = new int[n];
            int b[] = new int[n];
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
                list.add(a[i]);
            }
            for (int i = 0; i < n; i++) {
                b[i] = fs.nextInt();
                list.add(b[i]);
            }

            Collections.sort(list);

            int s = 0;
            int e = 2 * n - 1;
            int ans = 0;
            while (s <= e) {
                int mid = s + (e - s) / 2;

                boolean ch = valid(a, b, list.get(mid));
                if (ch) {
                    ans = list.get(mid);
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }

            System.out.println(ans);
        }
    }

    public static boolean valid(int a[], int b[], int val) {
        int n = a.length;
        int twos = 0;
        int lst = -1;
        int ones = 0;
        for (int i = 0; i < n; i++) {
            int dig = (a[i] >= val ? 1 : 0) + (b[i] >= val ? 1 : 0);
            if (dig == 1)
                continue;
            if (dig == 0) {
                if (lst == -1) {
                    ones++;
                }
                lst = 0;
            } else {
                twos++;
                lst = -1;
            }
        }

        if (twos > ones)
            return true;
        return false;
    }

    /*
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