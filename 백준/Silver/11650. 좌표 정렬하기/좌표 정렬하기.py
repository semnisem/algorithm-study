import heapq as hq
n = int(input())
points = []
for i in range(n):
    x,y = map(int,input().split())
    hq.heappush(points, (x, y))

while points:
    point = hq.heappop(points)
    print(point[0], point[1])
