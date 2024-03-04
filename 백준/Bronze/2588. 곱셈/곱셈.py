op1, op2 = [int(input()) for _ in range(2)]

a=op1*(op2%10)
b=op1*((op2%100)//10)
c=op1*(op2//100)
result=a+10*b+100*c

print(a)
print(b)
print(c)
print(result)