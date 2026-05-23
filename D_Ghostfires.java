import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class D_Ghostfires {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int t = fs.nextInt();
        while (t-- > 0) {
            char ch[] = { 'R', 'G', 'B' };
            int ct[] = new int[3];
            for (int i = 0; i < 3; i++) {
                ct[i] = fs.nextInt();
            }
            int f_s = 0;
            int f_t = 0;
            int t_s = 0;
            while (ct[0] != 0 || ct[1] != 0 || ct[2] != 0) {
                if (ct[0] != 0 && ct[1] != 0 && ct[0] >= ct[2] && ct[1] >= ct[2]) {
                    f_s++;
                    ct[0]--;
                    ct[1]--;
                } else if (ct[0] != 0 && ct[2] != 0 && ct[0] >= ct[1] && ct[2] >= ct[1]) {
                    f_t++;
                    ct[0]--;
                    ct[2]--;
                } else if (ct[2] != 0 && ct[1] != 0 && ct[1] >= ct[0] && ct[2] >= ct[0]) {
                    t_s++;
                    ct[1]--;
                    ct[2]--;
                } else {
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            if (f_s == 0 && f_t == 0 && t_s == 0) {
                if (ct[0] != 0)
                    sb.append(ch[0]);
                else if (ct[1] != 0)
                    sb.append(ch[1]);
                else if (ct[2] != 0)
                    sb.append(ch[2]);
            } else if (f_s == 0 && f_t == 0) {
                if (ct[1] != 0)
                    sb.append(ch[1]);
                for (int i = 0; i < t_s; i++) {
                    sb.append("BG");
                }
                if (ct[2] != 0)
                    sb.append(ch[2]);
                else if (ct[0] != 0)
                    sb.append(ch[0]);
            } else if (f_s == 0 && t_s == 0) {
                if (ct[2] != 0)
                    sb.append(ch[2]);
                for (int i = 0; i < f_t; i++) {
                    sb.append("RB");
                }
                if (ct[0] != 0)
                    sb.append(ch[0]);
                else if (ct[1] != 0)
                    sb.append(ch[1]);
            } else if (f_t == 0 && t_s == 0) {
                if (ct[1] != 0)
                    sb.append(ch[1]);
                for (int i = 0; i < f_s; i++) {
                    sb.append("RG");
                }
                if (ct[0] != 0)
                    sb.append(ch[0]);
                else if (ct[2] != 0)
                    sb.append(ch[2]);
            } else if (f_s == 0) {
                if (ct[0] != 0)
                    sb.append(ch[0]);
                if (ct[1] != 0)
                    sb.append(ch[1]);
                for (int i = 0; i < t_s; i++) {
                    sb.append("BG");
                }
                for (int i = 0; i < f_t; i++) {
                    sb.append("BR");
                }
                if (ct[2] != 0)
                    sb.append(ch[2]);
            } else if (t_s == 0) {
                if (ct[2] != 0)
                    sb.append(ch[2]);
                if (ct[1] != 0)
                    sb.append(ch[1]);
                for (int i = 0; i < f_s; i++) {
                    sb.append("RG");
                }
                for (int i = 0; i < f_t; i++) {
                    sb.append("RB");
                }
                if (ct[0] != 0)
                    sb.append(ch[0]);
            } else if (f_t == 0) {
                if (ct[0] != 0)
                    sb.append(ch[0]);
                if (ct[2] != 0)
                    sb.append(ch[2]);
                for (int i = 0; i < t_s; i++) {
                    sb.append("GB");
                }
                for (int i = 0; i < f_s; i++) {
                    sb.append("GR");
                }
                if (ct[1] != 0)
                    sb.append(ch[1]);
            } else {
                if (ct[0] == 0) {
                    if (ct[1] != 0)
                        sb.append(ch[1]);
                    for (int i = 0; i < f_s; i++) {
                        sb.append("RG");
                    }
                    for (int i = 0; i < t_s; i++) {
                        sb.append("BG");
                    }
                    for (int i = 0; i < f_t; i++) {
                        sb.append("BR");
                    }
                    if (ct[2] != 0)
                        sb.append(ch[2]);
                } else {
                    sb.append(ch[0]);
                    for (int i = 0; i < f_s; i++) {
                        sb.append("GR");
                    }
                    for (int i = 0; i < f_t; i++) {
                        sb.append("BR");
                    }
                    for (int i = 0; i < t_s; i++) {
                        sb.append("BG");
                    }
                }
            }

            System.out.println(sb.toString());
        }
    }

    /*
     * i i + 1
     * i and i + 3 must not be equal
     * 
     * try to make every second char equal -> it will increase length and reduces
     * matching
     * 
     * so our goal is the find the best possible way to divide them into pair &
     * distribute and after formation there is only one color whose element will be
     * left
     * 
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