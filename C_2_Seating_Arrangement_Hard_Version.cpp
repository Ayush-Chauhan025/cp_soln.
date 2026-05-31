#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

ll t, s;

ll fun(string &str, int chng){
    ll seats = 0;
    ll table = t;
    ll ans = 0;
    for(char ch : str){
        if(ch == 'E'){
            if(seats > 0) seats--, ans++;
        } else if(ch == 'I'){
            if(table > 0) table--, seats += s - 1, ans++;
        } else {
            if(chng > 0 && table > 0) chng--, table--, seats += s - 1, ans++;
            else if(seats > 0) seats--, ans++;
        }
    }

    return ans;
}

int main(){
    ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

    int T; cin >> T;
    
    while(T-- > 0){
        int n;
        cin >> n >> t >> s;
        string str; cin >> str;

        int l = 0, r = 0;
        for(char ch : str) r++;
        ll ans = fun(str, 0);

        while(l <= r){
            int mid = l + (r - l)/2;

            ll x = fun(str, mid);
            ll y = fun(str, mid + 1);

            if(x < y){
                ans = y;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        cout << ans << '\n';
    }
}