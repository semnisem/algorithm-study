n=int(input())

nums=[]
for _ in range(n):
    nums.append(int(input()))

for num in sorted(nums):
    print(num)