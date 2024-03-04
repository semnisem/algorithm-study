def inf_to_postf(exp):
    priority={'+':1, '-':1, '*':2, '/':2}
    stack=[]
    result=[]

    for ch in exp:
        if ch.isalnum():
            result.append(ch)
        elif ch=='(':
            stack.append(ch)
        elif ch==')':
            while stack and stack[-1]!='(':
                result.append(stack.pop())
            stack.pop()
        else:
            while stack and priority.get(stack[-1],0)>=priority[ch]:
                result.append(stack.pop())
            stack.append(ch)

    while stack:
        result.append(stack.pop())

    return ''.join(result)

# 실행
print(inf_to_postf(input()))