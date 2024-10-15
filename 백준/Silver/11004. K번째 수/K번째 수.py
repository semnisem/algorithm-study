n, k = map(int, input().split())  # 1 <= K <= N <= 5*1e6
a = list(map(int, input().split()))  # -10**9 <= a[i] <= 10**9

print(sorted(a)[k-1])