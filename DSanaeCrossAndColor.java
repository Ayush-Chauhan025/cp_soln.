import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class DSanaeCrossAndColor {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int points[][] = new int[n][2];
            for (int i = 0; i < n; i++) {
                points[i][0] = fs.nextInt();
                points[i][1] = fs.nextInt();
            }

            Arrays.sort(points, (f, s) -> {
                if (f[0] == s[0])
                    return f[1] - s[1];
                else
                    return f[0] - s[0];
            });

            ArrayList<Integer> x_coords = new ArrayList<>();
            ArrayList<ArrayList<Integer>> x_pts = new ArrayList<>();
            HashSet<Integer> y_coords = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int x = points[i][0];
                int y = points[i][1];
                if (x_coords.size() == 0 || x_coords.get(x_coords.size() - 1) != x) {
                    x_coords.add(x);
                    x_pts.add(new ArrayList<>());
                }
                x_pts.get(x_coords.size() - 1).add(y);
                y_coords.add(y);
            }
            int x_len = x_coords.size();
            ArrayList<Integer> y_pts = new ArrayList<>();
            for (int y : y_coords) {
                y_pts.add(y);
            }
            Collections.sort(y_pts);

            TreeMap<Integer, Integer> upper = new TreeMap<>();
            int btm_min = 100000000;
            int btm_max = 0;

            for (int i = 0; i < n; i++) {
                int y = points[i][1];

                upper.put(y, upper.getOrDefault(y, 0) + 1);
            }
            long ans = 0;
            int top_min = upper.firstKey();
            int top_max = upper.lastKey();

            for (int i = x_len - 1; i >= 0; i--) {
                // move all y's to the bottom half
                for (int y : x_pts.get(i)) {
                    if (upper.get(y) == 1)
                        upper.remove(y);
                    else
                        upper.put(y, upper.get(y) - 1);

                    btm_max = Math.max(btm_max, y);
                    btm_min = Math.min(btm_min, y);
                }

                if (upper.isEmpty())
                    continue;
                top_min = upper.firstKey();
                top_max = upper.lastKey();

                int l = max(top_min, btm_min);
                int r = min(top_max, btm_max);

                if (l < r) {
                    int s = bs(y_pts, r);
                    int f = bs(y_pts, l);
                    if (f == -1 || s == -1)
                        continue;
                    ans += s - f;
                }
            }

            System.out.println(ans);
        }
    }

    public static int bs(ArrayList<Integer> bs, int key) {
        int l = 0;
        int r = bs.size() - 1;
        int idx = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int val = bs.get(mid);
            if (key == val)
                return mid;
            else if (key < val)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return idx;
    }

    /*
     * if an element share same x or y then they atmost can have two different color
     * pairs
     * 
     * need to find the no of valid y's such that each quad have atleast one point
     * 
     * can use bs for finding the no of y's available
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