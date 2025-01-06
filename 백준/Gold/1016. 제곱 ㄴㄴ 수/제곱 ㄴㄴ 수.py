small, big = map(int, input().split())

# 초기화
seive=[True]*(big-small+1) # small이상 big이하

# 제곱수 구하기
square_numbers=[]
limit = int(big**0.5)+1
for i in range(2, limit):
    square_numbers.append(i**2)

# 제곱 ㄴㄴ인지 판정하기
result=[]
cnt=0
for sq in square_numbers:
    start = ((small + sq - 1) // sq)*sq  # small이상 이면서 sq 배수인 가장 작은 애
    for multiple in range(start, big+1, sq): # sq의 배수들은 전부 False 처리
        seive[multiple-small] = False

# seive에서 True인 애들만 카운트
cnt=sum(seive)
print(cnt)