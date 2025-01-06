# 임의의 자연수가 주어지면,
# 이를 네 개의 소수의 합으로 분해하는 프로그램을 작성하시오.
# 예를 들어 38 = 5 + 7 + 13 + 13이 된다.

# 난 시간초과 났움ㅠㅠ

n=int(input())
array = [True for i in range(n+1)]

# 에라토스테네스의 체 - 소수판별 알고리즘
for i in range(2, int(n**0.5)+1):
    if array[i] == True:  # 소수로 간주하는 i
        j=2
        while i*j<=n:  # i의 배수들
            array[i*j]=False
            j+=1

# 소수들만 정리하기
nums=[]
for i in range(2, n+1):
    if array[i] == True:
        nums.append(i)
# print(nums)


def four_prime_sum(n, sieve):
    """
    골드바흐 확장 아이디어로 n을 네 소수 합으로 나타내는 예시 함수.
    - n이 8 미만이면 None 반환
    - 8 이상이면 [p1, p2, p3, p4] 형태로 네 소수를 담은 리스트 반환
    - (p1 + p2 + p3 + p4 = n)
    """
    if n < 8:
        return None

    if n % 2 == 0:
        front = [2, 2]
        leftover = n - 4
    else:
        front = [2, 3]
        leftover = n - 5

    # leftover는 항상 짝수가 되므로 2개의 소수로 나눌 수 있는지 확인
    for p in range(2, leftover + 1):
        if p in nums:
            q = leftover - p
            if q >= 2 and q in nums:
                return front + [p, q]

    return None

result = four_prime_sum(n, nums)
if result:
    print(f"{' '.join(map(str, result))}")
else:
    print(-1)