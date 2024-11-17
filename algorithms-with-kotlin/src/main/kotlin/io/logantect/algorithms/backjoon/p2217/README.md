# [백준 2217번: 로프](https://www.acmicpc.net/problem/2217)

## 1. 추상화
### 문제 설명:
1. 여러 개의 로프가 주어졌을 때, 이 로프들을 사용하여 들어올릴 수 있는 최대 중량을 구하는 문제입니다.
2. 각 로프는 하나의 중량을 들 수 있으며, 여러 로프를 함께 사용할 경우에는 각 로프가 들 수 있는 중량이 달라질 수 있습니다.

### 목표:
1. 주어진 로프들을 사용하여 들어올릴 수 있는 최대 중량을 계산합니다.
2. 시간 제한 2초, 메모리 제한 192MB.

## 2. 계산
### 불변량(Invariant) 정의:
1. 로프의 개수(`n`)는 변하지 않습니다.
2. 각 로프가 들 수 있는 중량은 고정되어 있습니다.
3. 여러 로프를 사용할 경우 각 로프가 들 수 있는 최대 중량은 가장 약한 로프의 중량에 의해 제한됩니다.

### 알고리즘 단계:
1. **로프의 중량을 오름차순으로 정렬**:
    - 가장 약한 로프부터 차례대로 고려하기 위해 필요합니다.
2. **각 로프를 사용할 때의 최대 중량 계산**:
    - `i`번째 로프를 사용할 때, 그 이후의 모든 로프를 포함하여 최대 중량을 계산합니다.
    - 이를 계산하기 위해 `ropes[i] * (n - i)`를 사용합니다. 여기서 `n - i`는 현재 로프와 그 이후의 로프들의 개수입니다.
3. **최대 중량 선택**:
    - 각 로프를 사용할 때 계산한 최대 중량 중에서 가장 큰 값을 선택합니다.

### 시간 복잡도:
- 로프의 중량을 오름차순으로 정렬하는 데 O(n log n) 시간이 소요됩니다.
- 각 로프를 순회하며 최대 중량을 계산하는 데 O(n) 시간이 소요됩니다.
- 전체 시간 복잡도는 O(n log n)입니다.

### 공간 복잡도:
- 입력된 로프의 중량을 저장하기 위해 O(n)의 공간이 필요합니다.

### 코드 구현:
```kotlin
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val ropes = IntArray(n) { readLine().toInt() }
    println(Rope.solution(ropes))
}

object Rope {
    fun solution(ropes: IntArray): Int {
        ropes.sort()
        var maxWeight = 0
        for (i in ropes.indices) {
            val weight = ropes[i] * (ropes.size - i)
            if (weight > maxWeight) {
                maxWeight = weight
            }
        }
        return maxWeight
    }
}
```
## 3. 해석

### 1. **로프의 중량을 오름차순으로 정렬**:

로프의 중량을 오름차순으로 정렬하면 가장 약한 로프부터 차례대로 고려할 수 있습니다.

### 2. **각 로프를 사용할 때의 최대 중량 계산**:

- `i`번째 로프를 사용할 때, 그 이후의 모든 로프를 포함하여 최대 중량을 계산합니다.
- `ropes[i] * (n - i)`는 `i`번째 로프와 그 이후의 로프들이 들 수 있는 최대 중량을 의미합니다.

### 3. **최대 중량 선택**:

각 로프에 대해 계산한 최대 중량 중에서 가장 큰 값을 선택합니다.

### 예시

로프의 중량이 `[10, 20, 30, 40]`인 경우를 생각해봅시다:

1. 로프의 중량을 오름차순으로 정렬하면 `[10, 20, 30, 40]`이 됩니다.
2. 각 로프를 사용할 때의 최대 중량을 계산합니다:
    - 첫 번째 로프 (10)를 사용할 경우: 최대 중량 = `10 * (4 - 0) = 10 * 4 = 40`
    - 두 번째 로프 (20)를 사용할 경우: 최대 중량 = `20 * (4 - 1) = 20 * 3 = 60`
    - 세 번째 로프 (30)를 사용할 경우: 최대 중량 = `30 * (4 - 2) = 30 * 2 = 60`
    - 네 번째 로프 (40)를 사용할 경우: 최대 중량 = `40 * (4 - 3) = 40 * 1 = 40`
3. 가장 큰 최대 중량은 `60`입니다.