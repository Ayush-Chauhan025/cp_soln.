import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DAWideWideGraph {
    static final int mod = (int) 1e9 + 7;
    public static int farthest[];
    public static ArrayList<ArrayList<Integer>> tree;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = 1;
        while (t-- > 0) {
            int n = fs.nextInt();
            tree = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                tree.add(new ArrayList<>());
            }
            for (int i = 0; i < n - 1; i++) {
                int u = fs.nextInt();
                int v = fs.nextInt();
                tree.get(u).add(v);
                tree.get(v).add(u);
            }

            int dist[] = new int[n + 1];
            int d1 = 1;
            dfs(1, 0, 0, dist);
            for (int i = 1; i <= n; i++) {
                if (dist[i] > dist[d1])
                    d1 = i;
            }

            int d2 = 1;
            int dist1[] = new int[n + 1];
            dfs(d1, 0, 0, dist1);
            for (int i = 1; i <= n; i++) {
                if (dist1[i] > dist1[d2])
                    d2 = i;
            }

            dist = new int[n + 1];
            dfs(d2, 0, 0, dist);

            for (int i = 1; i <= n; i++) {
                dist[i] = Math.max(dist[i], dist1[i]);
            }

            int k[] = new int[n + 2];
            for (int i = 1; i <= n; i++) {
                k[dist[i] + 1]++;
            }

            int comp = 1;
            for (int i = 1; i <= n; i++) {
                comp += k[i];
                System.out.print(min(n, comp) + " ");
            }
            System.out.println();
        }
    }

    public static void dfs(int cur, int par, int depth, int dist[]) {
        dist[cur] = depth;
        for (int neigh : tree.get(cur)) {
            if (neigh != par) {
                dfs(neigh, cur, depth + 1, dist);
            }
        }
    }

    /*
     * a edge in graph exist if dist(u, v) >= k
     * 
     * element will be seperated one by one
     * if for a node k there is no node with dist k then it will be seperated at kl
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