import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class C1InteractiveGraphSimpleVersion {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();

            int min = 2;
            int max = 1 << 30;
            int vis[] = new int[n + 2];
            vis[1] = 1;
            for (int i = 2; i <= n; i++) {
                int l = min;
                int r = max;
                int ans = -1;

                while (l <= r) {
                    int mid = l + (r - l) / 2;

                    System.out.println("? " + mid);
                    System.out.flush();

                    int q = fs.nextInt();
                    if (q == 0) {
                        r = mid - 1;
                        max = min(max, mid);
                    } else {
                        int f = fs.nextInt();
                        for (int j = 0; j < q - 1; j++) {
                            int val = fs.nextInt();
                        }

                        if (f >= i) {
                            ans = mid;
                            r = mid - 1;
                        } else {
                            l = mid + 1;
                        }
                    }
                }

                vis[i] = ans;
                min = ans;
            }

            while (min <= max) {
                int mid = min + (max - min) / 2;
                System.out.println("? " + mid);
                System.out.flush();

                int q = fs.nextInt();
                if (q == 0) {
                    vis[n + 1] = mid;
                    max = mid - 1;
                } else {
                    int f = fs.nextInt();
                    for (int j = 0; j < q - 1; j++) {
                        int val = fs.nextInt();
                    }

                    min = mid + 1;
                }
            }
            int hash[] = new int[n + 1];
            ArrayList<int[]> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                int st = vis[i];
                int ed = vis[i + 1];
                hash[i] = ed - st;
                list.add(new int[] { i, ed - st - 1 });
            }

            Collections.sort(list, (x, y) -> Integer.compare(x[1], y[1]));
            ArrayList<int[]> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int node = list.get(i)[0];
                int st = vis[node] + 1;
                int paths = list.get(i)[1];

                while (paths > 0) {
                    System.out.println("? " + st);
                    System.out.flush();

                    int q = fs.nextInt();
                    for (int j = 0; j < q; j++) {
                        int val = fs.nextInt();
                        if (j == 1) {
                            ans.add(new int[] { node, val });
                            paths -= hash[val];
                            st += hash[val];
                        }
                    }
                }
            }

            System.out.println("! " + ans.size());
            for (int i = 0; i < ans.size(); i++) {
                System.out.println(ans.get(i)[0] + " " + ans.get(i)[1]);
            }
        }
    }

    /*
     * find every starting k and calc no of paths starting from node 1
     * 
     * sort them according to the paths can the first node we can skip all the paths
     * and go to next path by it
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