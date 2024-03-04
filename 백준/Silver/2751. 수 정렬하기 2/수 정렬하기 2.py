import sys

# 입력
n = int(sys.stdin.readline())
nums = [int(sys.stdin.readline()) for _ in range(n)]


# 병합정렬
def merge_sort(arr):
    if len(arr) <= 1:
        return arr

    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])

    return merge(left, right)

def merge(left, right):
    result = []
    i = j = 0

    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1

    result.extend(left[i:])
    result.extend(right[j:])

    return result


arr=merge_sort(nums)

# 출력
for num in arr:
    sys.stdout.write(str(num) + '\n')