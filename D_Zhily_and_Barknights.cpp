#include <bits/stdc++.h>
using namespace std;

const int mod = 998244353;

struct Fraction {
    long long num, den;

    Fraction(long long num, long long den) {
        this->num = num;
        this->den = den;
    }

    bool operator<(const Fraction& other) const {
        long long lhs = num * other.den;
        long long rhs = den * other.num;
        return lhs < rhs;
    }

    int compareTo(const Fraction& other) const {
        long long lhs = num * other.den;
        long long rhs = den * other.num;

        if (lhs < rhs) return -1;
        if (lhs > rhs) return 1;
        return 0;
    }
};

long long power(long long a, long long b) {
    long long res = 1;

    while (b > 0) {
        if (b & 1)
            res = (res * a) % mod;

        a = (a * a) % mod;
        b >>= 1;
    }

    return res;
}

long long modInv(int n) {
    long long val = 1LL * n * (n - 1);
    return power(val, mod - 2);
}

long long bs(vector<Fraction>& f, Fraction val) {
    int sz = f.size();
    int ans = 0;
    int l = 0;
    int r = sz - 1;

    while (l <= r) {
        int mid = l + (r - l) / 2;

        if (f[mid].compareTo(val) > 0) {
            ans = sz - mid;
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }

    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;

        vector<long long> a(n), b(n);

        for (int i = 0; i < n; i++)
            cin >> a[i];

        for (int i = 0; i < n; i++)
            cin >> b[i];

        vector<Fraction> f;
        f.reserve(n * (n - 1));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                f.emplace_back(b[i], b[j]);
            }
        }

        sort(f.begin(), f.end());

        long long ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Fraction val(a[j], a[i]);

                long long comb = bs(f, val);
                ans += comb;
            }
        }

        ans %= mod;
        ans = (ans * modInv(n)) % mod;

        cout << ans << '\n';
    }

    return 0;
}