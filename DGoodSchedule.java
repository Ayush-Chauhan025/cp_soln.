import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DGoodSchedule {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int a[] = new int[n];
            int b[] = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
            }
            for (int i = 0; i < n; i++) {
                b[i] = fs.nextInt();
            }
            int dp[] = new int[n + 1];
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int va = a[i];
                int vb = b[i];

                if (va == vb) {
                    if (va == 1) {
                        if (!map.containsKey(1))
                            map.put(1, new ArrayList<>());

                        map.get(1).add(i);
                    } else {
                        int prev = va - 1;
                        if (map.containsKey(prev)) {
                            ArrayList<Integer> mov = map.get(prev);
                            map.remove(prev);

                            if (map.containsKey(va)) {
                                map.get(va).addAll(mov);
                            } else {
                                map.put(va, mov);
                            }
                        }
                    }
                } else {
                    int preva = va - 1;
                    int prevb = vb - 1;
                    if (map.containsKey(preva)) {
                        for (int idx : map.get(preva)) {
                            dp[idx] = i - idx;
                        }
                        map.remove(preva);
                    }
                    if (map.containsKey(prevb)) {
                        for (int idx : map.get(prevb)) {
                            dp[idx] = i - idx;
                        }
                        map.remove(prevb);
                    }
                }
            }
            for (int val : map.keySet()) {
                for (int idx : map.get(val)) {
                    dp[idx] = n - idx;
                }
            }

            for (int i = n - 1; i >= 0; i--) {
                if (a[i] != 1 && b[i] != 1) {
                    dp[i] = dp[i + 1] + 1;
                } else if ((a[i] == 1 && b[i] != 1) || (a[i] != 1 && b[i] == 1)) {
                    dp[i] = 0;
                } else {
                    continue;
                }
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += dp[i];
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