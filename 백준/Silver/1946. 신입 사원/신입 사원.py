T = int(input())
for test_case in range(T):
    n = int(input())
    cnt = 0

    candidates = []
    for _ in range(n):
        doc, interview = map(int, input().split())
        candidates.append((doc, interview))

    candidates.sort()  # sort by doc

    min_interview = float('inf')
    for doc, interview in candidates:
        if interview <= min_interview:
            cnt += 1
            min_interview = interview

    print(cnt)