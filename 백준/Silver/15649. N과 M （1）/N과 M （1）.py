from itertools import permutations
n,m=list(map(int,input().split()))

def find_permu(a, b):
    numbers = list(range(1, a + 1))  # 1부터 a까지의 자연수 리스트
    return list(permutations(numbers, b))  # 길이가 b인 순열 생성

for tup in find_permu(n, m):
    for el in tup:
        print(el, end=" ")
    print()