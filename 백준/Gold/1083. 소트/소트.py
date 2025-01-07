n = int(input())
array = list(map(int, input().split())) # 서로 다른 수들
s = int(input())

left = -1
while s > 0 and left < len(array)-2:
    left += 1
    right = min(left+s, len(array)-1)
    if right == len(array):
        max_id = array.index(max(array[left:]))
    else:
        max_id = array.index(max(array[left:right + 1]))
    # print(f"{left}부터 {right}까지 구간의 최댓값{array[max_id]}는 {max_id}에 위치.")
    if max_id == left:
        continue
    else:
        # switch for 'move' times
        temp = array[max_id]
        array[left+1:max_id+1] = array[left:max_id]
        array[left] = temp
        move = max_id - left
        s -= move
    # print(f"{move}번 이동한 결과 >> {array}, 잔여 s: {s}")

print(" ".join(map(str, array)))

