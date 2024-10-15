import heapq as hq

n = int(input())
storage = []
result = []
for _ in range(n):
    x = int(input())
    if x != 0:
        hq.heappush(storage, (abs(x), x))
    elif storage:
        abs_v, v = hq.heappop(storage)
        result.append(v)
    else:
        result.append(0)

print('\n'.join(map(str, result)))