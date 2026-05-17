import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class C1CirnoAndNumberEasyVersion {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            long a = fs.nextLong();
            int n = fs.nextInt();
            int dig[] = new int[n];
            for (int i = 0; i < n; i++) {
                dig[i] = fs.nextInt();
            }
            Arrays.sort(dig);
            char ch[] = Long.toString(a).toCharArray();
            int len = ch.length;
            long inf = (long) (1e18) + 3;
            long ans = inf;
            long dp[][][] = new long[len][n][2];
            for (int i = 0; i < n; i++) {
                int dg = dig[i];
                dp[0][i][0] = inf;
                dp[0][i][1] = inf;
                if (dg == ch[0] - '0')
                    dp[0][i][0] = dg;
                else if (dg > (ch[0] - '0'))
                    dp[0][i][1] = dg;
            }
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < n; j++) {
                    int dg = dig[j];
                    dp[i][j][0] = inf;
                    dp[i][j][1] = inf;
                    for (int k = 0; k < n; k++) {
                        long prev_c = dp[i - 1][k][0];
                        long prev_cl = dp[i - 1][k][1];

                        if (prev_c != inf) {
                            if (dg == ch[i] - '0')
                                dp[i][j][0] = min(dp[i][j][0], prev_c * 10 + dg);
                            else if (dg > ch[i] - '0')
                                dp[i][j][1] = min(dp[i][j][1], prev_c * 10 + dg);
                        }

                        if (prev_cl != inf) {
                            dp[i][j][1] = min(dp[i][j][1], prev_cl * 10 + dg);
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                if (dp[len - 1][i][0] != inf)
                    ans = min(ans, abs(a - dp[len - 1][i][0]));
                if (dp[len - 1][i][1] != inf)
                    ans = min(ans, abs(a - dp[len - 1][i][1]));
            }

            dp = new long[len][n][2];
            for (int i = 0; i < n; i++) {
                int dg = dig[i];
                dp[0][i][0] = -1;
                dp[0][i][1] = -1;
                if (dg == ch[0] - '0')
                    dp[0][i][0] = dg;
                else if (dg < (ch[0] - '0'))
                    dp[0][i][1] = dg;
            }
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < n; j++) {
                    int dg = dig[j];
                    dp[i][j][0] = -1;
                    dp[i][j][1] = -1;
                    for (int k = 0; k < n; k++) {
                        long prev_c = dp[i - 1][k][0];
                        long prev_cl = dp[i - 1][k][1];

                        if (prev_c != -1) {
                            if (dg == ch[i] - '0')
                                dp[i][j][0] = max(dp[i][j][0], prev_c * 10 + dg);
                            else if (dg < ch[i] - '0')
                                dp[i][j][1] = max(dp[i][j][1], prev_c * 10 + dg);
                        }

                        if (prev_cl != -1) {
                            dp[i][j][1] = max(dp[i][j][1], prev_cl * 10 + dg);
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                if (dp[len - 1][i][0] != -1)
                    ans = min(ans, abs(a - dp[len - 1][i][0]));
                if (dp[len - 1][i][1] != -1)
                    ans = min(ans, abs(a - dp[len - 1][i][1]));
            }

            long num = 0;
            if (n > 1) {
                for (int i = 0; i < len + 1; i++) {
                    if (i == 0 && dig[0] == 0)
                        num = num * 10 + dig[1];
                    else
                        num = num * 10 + dig[0];
                }
                ans = min(ans, abs(a - num));
            } else {
                for (int i = 0; i < len + 1; i++) {
                    num = num * 10 + dig[0];
                }
                ans = min(ans, abs(a - num));
            }
            num = 0;

            if (len > 1) {
                for (int i = 0; i < len - 1; i++) {
                    num = num * 10 + dig[n - 1];
                }
                ans = min(ans, abs(a - num));
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