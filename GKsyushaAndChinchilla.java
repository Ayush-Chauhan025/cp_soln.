import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class GKsyushaAndChinchilla {
    static final int mod = (int) 1e9 + 7;
    public static ArrayList<ArrayList<Integer>> tree;
    public static ArrayList<Integer> cut;
    public static HashMap<Long, Integer> index;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            tree = new ArrayList<>();
            cut = new ArrayList<>();
            index = new HashMap<>();
            for (int i = 0; i <= n; i++)
                tree.add(new ArrayList<>());

            for (int i = 1; i <= n - 1; i++) {
                int u = fs.nextInt();
                int v = fs.nextInt();
                int min = min(u, v);
                int max = max(u, v);
                index.put(getKey(min, max), i);

                tree.get(u).add(v);
                tree.get(v).add(u);
            }

            int vis[] = new int[n + 1];
            int sz = dfs(1, vis);
            if (sz == 3) {
                int len = cut.size();
                System.out.println(len);
                for (int i = 0; i < len; i++) {
                    System.out.print(cut.get(i) + " ");
                }
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }

    public static int dfs(int node, int vis[]) {
        int sz = 1;
        vis[node] = 1;
        for (int neigh : tree.get(node)) {
            if (vis[neigh] == 0) {
                int neigh_sz = dfs(neigh, vis);
                if (neigh_sz == 3) {
                    int min = min(node, neigh);
                    int max = max(node, neigh);
                    int idx = index.get(getKey(min, max));
                    cut.add(idx);
                } else {
                    sz += neigh_sz;
                }
            }
        }
        return sz;
    }

    public static long getKey(int a, int b) {
        return ((long) a << 32L) | (long) b;
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