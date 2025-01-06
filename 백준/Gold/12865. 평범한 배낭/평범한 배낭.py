
n, capacity=map(int, input().split())  # 10^2, 10^5

things=list()
for _ in range(n):
    w, v = map(int, input().split())
    things.append([w, v])
# print(f"물건 리스트 (무게,가치): {things}")

dp = list()
for i in range(n+1):
    dp.append([0]*(capacity+1))

'''def print_dp():
    print("** DP Table ** >>")
    print("    0, 1, 2, 3, 4, 5, 6, 7")
    print(" |_________________________")
    for i in range(n+1):
        print(f"{i}| {dp[i]}")
    print()'''

# print_dp()

for i in range(1,n+1):
    for j in range(1, capacity+1):
        if things[i-1][0] > j:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i - 1][j],
                           dp[i - 1][j - things[i-1][0]] + things[i-1][1])

    # print_dp()
print(f"{dp[n][capacity]}")