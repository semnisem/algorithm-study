import sys
import heapq as hq

for _ in range(int(sys.stdin.readline().rstrip())):

    min_heap = [ ]
    max_heap = [ ]
    sync = {}

    for _ in range(int(sys.stdin.readline().rstrip())):
        command, value = sys.stdin.readline().rstrip().split()
        v = int(value)

        if command == 'I':
            if v not in sync:
                sync[v]=1
            else:
                sync[v]+=1
            hq.heappush(min_heap, v)
            hq.heappush(max_heap, -v)
            continue
        elif not sync:
            continue
        elif v == -1:
            num = hq.heappop(min_heap)
            while num not in sync:
                num = hq.heappop(min_heap)
            sync[num] -= 1
            if sync[num] == 0:
                del sync[num]
        elif v == 1:
            num = -hq.heappop(max_heap)
            while num not in sync:
                num = -hq.heappop(max_heap)
            sync[num] -= 1
            if sync[num] == 0:
                del sync[num]

    if not sync:
        print('EMPTY')
    else:
        min_num = hq.heappop(min_heap)
        while min_num not in sync:
            min_num = hq.heappop(min_heap)
        max_num = -hq.heappop(max_heap)
        while max_num not in sync:
            max_num = -hq.heappop(max_heap)
        print(max_num, min_num)
