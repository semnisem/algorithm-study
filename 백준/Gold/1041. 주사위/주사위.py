N=int(input())
a,b,c,d,e,f=list(map(int,input().split()))

combi=dict()
combi['1d']=sorted([a,b,c,d,e,f])
combi['2d']=sorted([a+b, a+c, a+d, a+e,
                    b+c, b+d, d+e, c+e,
                    f+b, f+c, f+d, f+e,])
combi['3d']=sorted([a+b+c, a+c+e, a+d+e, a+b+d,
                    f+b+c, f+c+e, f+d+e, f+b+d,])
combi['min']=[combi['1d'][0], combi['2d'][0], combi['3d'][0]]

answer=0
if N==1:
    answer=sum(combi['1d'][:5])
else:
    dim_3=4
    dim_2=(N-2)*8+4
    dim_1=(N-2)**2*5+4*(N-2)
    dims=[dim_1, dim_2, dim_3]
    answer=sum(d*m for d, m in zip(dims, combi['min']))

print(answer)