import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class CRanomNumbers {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            String str = fs.next();
            String s = new StringBuilder(str).reverse().toString();
            int n = s.length();
            long sum = 0;
            long cost[] = { 1, 10, 100, 1000, 10000 };
            int rest_idx[] = new int[n + 1];
            int arr[][] = new int[n + 1][5];
            Stack<Integer> st = new Stack<>();
            for (int i = 0; i < n; i++) {
                int val = s.charAt(i) - 'A';
                while (!st.isEmpty() && s.charAt(st.peek()) - 'A' <= val) {
                    st.pop();
                }
                if (st.isEmpty())
                    rest_idx[i] = -1;
                else {
                    rest_idx[i] = st.peek();
                    arr[st.peek()][val]++;
                }
                st.push(i);
            }
            int suf[][] = new int[n + 1][5];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < 5; j++) {
                    suf[i][j] = suf[i + 1][j];
                }
                if (rest_idx[i] == -1)
                    suf[i][s.charAt(i) - 'A']++;
            }
            for (int i = 0; i < n; i++) {
                long val = cost[s.charAt(i) - 'A'];
                if (rest_idx[i] == -1)
                    sum += val;
                else
                    sum -= val;
            }

            boolean present[] = new boolean[5];
            long change = 0;
            for (int i = 0; i < n; i++) {
                int val = s.charAt(i) - 'A';
                boolean or_suf[] = new boolean[6];
                for (int j = 4; j >= 0; j--) {
                    or_suf[j] = present[j] || or_suf[j + 1];
                }
                // increment
                for (int j = val + 1; j < 5; j++) {
                    long old_val = (rest_idx[i] == -1) ? cost[val] : -cost[val];
                    long new_val = (!or_suf[j + 1]) ? cost[j] : -cost[j];
                    long ch = new_val - old_val;
                    for (int k = j - 1; k >= 0; k--) {
                        ch -= 2 * suf[i + 1][k] * cost[k];
                    }
                    change = max(ch, change);
                }

                // decrement
                for (int j = val - 1; j >= 0; j--) {
                    long old_val = (rest_idx[i] == -1) ? cost[val] : -cost[val];
                    long new_val = (!or_suf[j + 1]) ? cost[j] : -cost[j];
                    long ch = new_val - old_val;
                    for (int k = val - 1; k >= j; k--) {
                        if (!or_suf[k + 1])
                            ch += 2 * arr[i][k] * cost[k];
                    }
                    change = max(ch, change);
                }
                present[val] = true;
            }

            sum += change;
            System.out.println(sum);
        }
    }

    /*
     * if we increment an element it may remove its restriction only and all the
     * elements who has no restriction
     * before will get restricted based on the change that has happended i.e a -> d
     * then a, b, c
     * 
     * if we decrement an element we remove restriction happening due to this
     * element if and only if
     * this element is not restricted
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