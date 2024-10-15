import heapq as hq
import sys

min_heap = []
for _ in range(int(sys.stdin.readline().rstrip())):
    x = int(sys.stdin.readline().rstrip())

    if min_heap and x == 0:
        print(hq.heappop(min_heap))
    elif x == 0:
        print(0)
    else:
        hq.heappush(min_heap, x)