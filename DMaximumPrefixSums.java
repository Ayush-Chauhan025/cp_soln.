import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DMaximumPrefixSums {
    static final long inf = (long) (1e12);

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            String s = fs.next();
            long a[] = new long[n];
            long c[] = new long[n];
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextLong();
            }

            for (int i = 0; i < n; i++) {
                c[i] = fs.nextLong();
            }

            long ans[] = new long[n];

            if (s.charAt(0) - '0' == 1) {
                ans[0] = c[0];
                if (a[0] != c[0])
                    valid = false;
            } else {
                ans[0] = c[0];
            }

            long lst_max = c[0];
            long sum = 0;
            Deque<Integer> q = new ArrayDeque<>();

            for (int i = 1; i < n; i++) {
                long cur_max = c[i];
                long val = a[i];
                int av = s.charAt(i) - '0';

                if (lst_max == cur_max) {
                    if (av == 1) {
                        ans[i] = val;
                        sum += val;
                    } else {
                        sum -= inf;
                        ans[i] = -inf;
                        q.offer(i);
                    }
                } else {
                    if (lst_max > cur_max)
                        valid = false;

                    if (av == 0) {
                        long dist = cur_max - lst_max;
                        ans[i] = dist - sum;
                    } else {
                        long dist = cur_max - lst_max - val;
                        long tot = dist - sum;
                        if (tot != 0) {
                            if (q.isEmpty())
                                valid = false;
                            else {
                                ans[q.peekLast()] += tot;
                            }
                        }
                        ans[i] = val;
                    }
                    sum = 0;
                    q = new ArrayDeque<>();
                    lst_max = cur_max;
                }
            }

            while (!q.isEmpty()) {
                int idx = q.pollFirst();
                ans[idx] = -inf;
            }

            sum = 0;
            long max = -inf;
            for (int i = 0; i < n; i++) {
                sum += ans[i];
                max = Math.max(max, sum);
                if (max != c[i])
                    valid = false;
            }

            if (valid) {
                System.out.println("Yes");
                for (int i = 0; i < n; i++) {
                    System.out.print(ans[i] + " ");
                }
                System.out.println();
            } else {
                System.out.println("No");
            }
        }
    }

    /*
     * whenever we know correct subset its better to fill previous ranges as values
     * will not affected
     * 
     * if we also know correct value ->
     * 
     * if we dont know correct value ->
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