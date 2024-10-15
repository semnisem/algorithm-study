n = int(input())

answer = []

stack = []
num = 1
for _ in range(n):
    target = int(input())

    while target >= num:  # 수열 값이 될 때까지 num을 push
        answer.append('+')
        stack.append(num)
        num += 1
    if stack[-1] == target:  # 수열 값이 된 num은 pop
        answer.append('-')
        stack.pop()
    else:
        answer = ['NO']
        break

for a in answer:
    print(a)