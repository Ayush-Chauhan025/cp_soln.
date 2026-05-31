import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DMagicalTieredCake {
    static final int mod = (int) 1e9 + 7;
    public static int a[];
    public static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            a = new int[n];
            list = new ArrayList<>();
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
                if (a[i] > i) {
                    valid = false;
                }
            }

            if (valid) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
                continue;
            }

            dfs(n - 1, 1, 2, 3);

            System.out.println(list.size());
            for (int arr[] : list) {
                System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
            }
        }
    }

    public static void dfs(int element, int st, int mid, int ed) {
        if (element < 0)
            return;
        if (element == 0) {
            list.add(new int[] { element + 1, st, ed });
            return;
        }

        int tot = element;
        int need = a[element];
        int move = tot - need;

        if (move == element) {
            dfs(move - 1, st, ed, mid);
            list.add(new int[] { element + 1, st, ed });
            dfs(move - 1, mid, st, ed);
        } else {
            dfs(move - 1, st, ed, mid);
            list.add(new int[] { element + 1, st, ed });
            dfs(move - 1, mid, ed, st);
            dfs(element - 1, st, mid, ed);
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