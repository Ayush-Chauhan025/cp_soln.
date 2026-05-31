import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class FRudolphAndMimic {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int type[] = new int[10];
            int chng[] = new int[10];

            // 1
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
                type[a[i]]++;
            }

            System.out.println("- 0");
            System.out.flush();

            // 2
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
                chng[a[i]]++;
            }

            int chng_ct = 0;
            for (int i = 0; i < 10; i++) {
                if (type[i] != chng[i])
                    chng_ct++;
            }

            if (chng_ct == 0) {
                chng = new int[10];

                System.out.println("- 0");
                System.out.flush();

                // 3
                for (int i = 0; i < n; i++) {
                    a[i] = fs.nextInt();
                    chng[a[i]]++;
                }
            }

            String s = "";
            int ct = 0;
            int n_type[] = new int[10];
            for (int i = 0; i < n; i++) {
                int cur_type = a[i];
                if (chng[cur_type] - type[cur_type] != 1) {
                    s = s + " " + (i + 1);
                    ct++;
                } else {
                    n_type[cur_type]++;
                }
            }
            System.out.println("- " + ct + s);
            System.out.flush();

            a = new int[n - ct];
            type = n_type;
            chng = new int[10];
            // 4
            for (int i = 0; i < n - ct; i++) {
                a[i] = fs.nextInt();
                chng[a[i]]++;
            }

            chng_ct = 0;
            for (int i = 0; i < 10; i++) {
                if (type[i] != chng[i])
                    chng_ct++;
            }

            if (chng_ct == 0) {
                chng = new int[10];

                System.out.println("- 0");
                System.out.flush();
                for (int i = 0; i < n - ct; i++) {
                    a[i] = fs.nextInt();
                    chng[a[i]]++;
                }

                int chnged_type = 0;
                for (int i = 0; i < 10; i++) {
                    if (chng[i] - type[i] == 1)
                        chnged_type = i;
                }

                for (int i = 0; i < n - ct; i++) {
                    if (a[i] == chnged_type) {
                        // 5
                        System.out.println("! " + (i + 1));
                        System.out.flush();
                        break;
                    }
                }
            } else {
                int to_rem = n - ct - 1;
                String str = "";
                int chnged_type = 0;
                for (int i = 0; i < 10; i++) {
                    if (chng[i] - type[i] == 1)
                        chnged_type = i;
                }

                for (int i = 0; i < n - ct; i++) {
                    if (a[i] != chnged_type) {
                        str = str + " " + (i + 1);
                    }
                }
                System.out.println("- " + to_rem + str);
                System.out.flush();
                int idx = fs.nextInt();
                System.out.println("! " + 1);
                System.out.flush();
            }

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