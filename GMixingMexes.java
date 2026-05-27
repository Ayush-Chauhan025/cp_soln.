import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class GMixingMexes {
    static final int mod = (int) 1e9 + 7;
    public static int map[] = new int[(int) (1e6 + 5)];
    public static int ct[] = new int[(int) (1e6 + 5)];

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            ArrayList<HashMap<Integer, Integer>> list = new ArrayList<>();
            ArrayList<Integer> chng = new ArrayList<>();
            int[][] check = new int[n][2];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                list.add(new HashMap<>());
                int len = fs.nextInt();
                for (int j = 0; j < len; j++) {
                    int val = fs.nextInt();
                    list.get(i).put(val, list.get(i).getOrDefault(val, 0) + 1);
                }

                int mex = 0;
                while (list.get(i).containsKey(mex))
                    mex++;
                int f_mex = mex;
                mex++;
                while (list.get(i).containsKey(mex))
                    mex++;
                check[i][0] = f_mex;
                check[i][1] = mex;
            }

            for (int i = 0; i < n; i++) {
                int mex = check[i][0];
                int smex = check[i][1];
                ct[mex]++;
                sum += (long) mex;
                map[mex] += smex;

                chng.add(mex);
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                for (int val : list.get(i).keySet()) {
                    long times = (long) list.get(i).get(val);
                    int mex = check[i][0];
                    int smex = check[i][1];
                    // array whose mex is val will use second mex
                    long use_smex = map[val];
                    // array whose mex is not smex will use max
                    long use_mex = (n - 1) * sum - ct[val] * val
                            - (n - 1) * check[i][0];
                    // cur will either reduce or stay
                    long cur = (n - 1) * ((check[i][0] < val || times > 1) ? check[i][0] : val);

                    ans += times * (use_mex + use_smex + cur);
                }
            }

            for (int i = 0; i < chng.size(); i++) {
                int idx = chng.get(i);
                map[idx] = 0;
                ct[idx] = 0;
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