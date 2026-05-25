import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class C2WeBeFlippingHardVersion {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            long a[] = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextLong();
            }
            long suf[] = new long[n + 1];
            for (int i = n - 1; i >= 0; i--) {
                suf[i] = a[i] + suf[i + 1];
            }
            ArrayList<Integer> list = new ArrayList<>();
            int max = -1;
            long val = suf[0];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] > 0) {
                    sum += a[i];
                    long tot = sum - 2 * a[i] + suf[i + 1];
                    if (tot > val) {
                        val = tot;
                        max = i;
                    }
                } else {
                    sum -= a[i];
                }
            }

            if (max > 0) {
                long prev = 0;
                for (int i = 0; i < max; i++) {
                    if (a[i] > 0) {
                        if (prev < 0)
                            list.add(-(i));
                        prev = a[i];
                    } else {
                        if (prev > 0)
                            list.add(i);
                        prev = a[i];
                    }
                }
                if (prev < 0)
                    list.add(-(max));
                else
                    list.add(max);
            }

            int sz = list.size();
            int neg = -1;
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                int vl = list.get(i);
                int idx = abs(vl) - 1;

                if (vl < 0) {
                    neg = idx;
                } else {
                    if (neg > -1) {
                        ans.add(idx);
                        ans.add(neg);
                    } else {
                        ans.add(idx);
                    }
                    neg = -1;
                }
            }

            if (max != -1)
                ans.add(max);

            int len = ans.size();
            System.out.println(len);
            for (int i = 0; i < len; i++) {
                System.out.print((ans.get(i) + 1) + " ");
            }
            System.out.println();
        }
    }

    /*
     * only positive index can be choosen for operation
     * sign of prefix changes
     * 
     * for every block of -ve -ve +ve we have a choise either keep as it is or
     * change it
     * 
     * -ve -ve +ve -ve -ve +ve
     * +ve +ve -ve +ve +ve -ve
     * +ve +ve -ve -ve +ve -ve
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