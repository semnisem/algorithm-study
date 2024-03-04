k=int(input())
stack=[]
for _ in range(k):
    n=int(input())
    if n==0 and stack:
        stack.pop()
    else:
        stack.append(n)

total=0
if stack:
    total=sum(stack)
print(min(2**31-1, total))