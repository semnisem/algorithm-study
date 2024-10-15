while True:
    tree=list(map(int, input().split()))
    age=tree[0]

    if age==0:
        break

    b=1
    for i in range(1,len(tree),2):
        b*=tree[i]
        b-=tree[i+1]

    print(b)
