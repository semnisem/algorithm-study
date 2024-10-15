import sys

n = int(sys.stdin.readline().rstrip())
houses = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(n)]

for h, cost in enumerate(houses):
    if h == 0:
        # print(houses[0])
        continue
    # print(f"{h}번째 집")
    for i in range(3):
        # print(f"color #{i}: {cost[i]}+{min(houses[h-1][(i+1) % 3], houses[h-1][(i+2) % 3])}")
        cost[i] += min(houses[h-1][(i+1) % 3], houses[h-1][(i+2) % 3])
    houses[h] = cost
    # print(houses[h])

print(min(houses[-1]))