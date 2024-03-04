x,y,w,h=map(int,input().split())

d=min([w-x, x, h-y, y])
print(d)