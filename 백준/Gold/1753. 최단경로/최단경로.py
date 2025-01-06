import heapq  # 우선순위 큐를 사용해야 선형탐색 복잡도 피할 수 있음.
INF = float('inf')

v, e = map(int, input().split())
k = int(input())

graph = [[] for _ in range(v+1)]
for _ in range(e):
    eu, ev, ew = map(int, input().split())
    graph[eu].append([ev, ew])

def dijkstra(start):
    distance = [INF]*(v+1)
    distance[start] = 0

    # 우선순위 큐: (현재까지 최단거리, 현재노드) 즉, 최소비용 기준으로 방문하게 됨
    pq = []
    heapq.heappush(pq, (0, start))

    while pq:
        dist, now = heapq.heappop(pq)  # 업뎃 가능한 애들 중 비용이 가장 작은 노드를 빠르게 꺼내기
        if distance[now] < dist:  # 이미 더 짧은 거리로 방문이 끝난 노드
            continue

        # 현재 노드에 인접한 노드들 비용 update
        for nxt, w in graph[now]:
            ndist = dist + w  # now노드까지 온 비용+now->nxt로의 비용
            if ndist < distance[nxt]:  # update했다면
                distance[nxt] = ndist  # 현재까지의 최소비용 Vs. 새로운 경유케이스 비용
                heapq.heappush(pq, (ndist, nxt))

    return distance

result = dijkstra(k)

for i in range(1, v + 1):
    if result[i] == INF:
        print("INF")
    else:
        print(result[i])