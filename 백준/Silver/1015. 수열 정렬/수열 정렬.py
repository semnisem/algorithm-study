from collections import deque

n = int(input())
a = list(map(int, input().split()))

array = {}
for i, v in enumerate(sorted(a)):
    if v not in array:
        array[v] = deque()
    array[v].append(i)

for element in a:
    print(array[element].popleft(), end=' ')