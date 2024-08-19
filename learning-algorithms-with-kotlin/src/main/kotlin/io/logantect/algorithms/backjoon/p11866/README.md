# [백준 11866번: 요세푸스 문제 0](https://www.acmicpc.net/problem/11866)

## 문제

---
요세푸스 문제는 다음과 같다.

1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다. 이제 순서대로 K번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다. 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다. 예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.

N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.

### 입력

첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 1,000)

### 출력

예제와 같이 요세푸스 순열을 출력한다.

### 입력 예제:

```
7 3
```

### 출력 예제:

```
<3, 6, 2, 7, 5, 1, 4>
```

---

## 1. 추상화

### 문제 설명:

1. `N`명의 사람이 원형으로 앉아있습니다.
2. 이들 중에서 `K`번째 사람을 제거하는 작업을 반복하여 마지막 한 사람이 남을 때까지 이 과정을 계속합니다.
3. 각 제거된 사람의 순서를 구하여 출력하는 문제입니다.

### 목표:

1. `N`명의 사람 중에서 `K`번째 사람을 제거하는 작업을 반복하며, 제거된 사람들의 순서를 출력합니다.
2. 시간 제한 2초, 메모리 제한 512MB.


## 2. 계산

### 불변량(Invariant) 정의:

1. 매번 `K`번째 사람을 제거하고 리스트를 줄이면서, 남아있는 사람들의 리스트와 현재 제거해야 할 사람의 인덱스를 정확하게 유지하는 것이 불변량입니다.
2. 리스트에서 사람을 제거하는 작업이 반복될 때, 인덱스가 리스트의 크기를 초과할 경우 이를 다시 리스트의 처음으로 순환하는 점이 중요합니다.

### 알고리즘 단계:

1. **리스트 초기화**:
    - `1`부터 `N`까지의 숫자로 이루어진 리스트를 생성합니다.
    - 이 리스트는 요세푸스 순열을 만들기 위해 사용됩니다.
2. **K번째 사람 제거**:
    - **인덱스 조정**:
        - 초기 인덱스는 `K-1`로 설정합니다. 이는 `0` 기반 인덱싱을 사용하기 때문입니다.
        - 리스트의 크기가 줄어들 때마다 인덱스가 리스트 크기를 초과할 수 있습니다. 이 경우 `removeIndex %= list.size`를 사용하여 인덱스를 순환시킵니다.
        - 이렇게 함으로써 리스트의 크기가 줄어들 때도 인덱스가 유효한 범위 내에 있도록 합니다.
    - **사람 제거**:
        - `list.removeAt(removeIndex)`를 통해 `K`번째 사람을 리스트에서 제거합니다.
        - 이 작업 후 리스트의 크기가 줄어들기 때문에, 다음 `K`번째 사람을 찾기 위해 `removeIndex += k - 1`를 수행합니다.
    - **중요성**:
        - 이 부분에서 인덱스 조정이 정확하지 않으면 알고리즘이 잘못된 결과를 출력할 수 있습니다.
        - 또한, 이 과정이 반복되면서 리스트가 비어있지 않은 경우는 인덱스를 조정하고, 리스트가 비어있을 경우에는 결과 출력을 준비합니다.
3. **결과 형식 맞추기**:
    - 모든 사람이 제거된 후, 결과를 "<>"로 감싸는 작업을 수행합니다.
    - 이때, 리스트가 비어있지 않은 경우에만 `", "`를 추가하여 마지막 결과의 포맷이 정확하게 유지되도록 합니다.

### 시간 복잡도 분석:

- **리스트 조작**: 리스트에서 요소를 제거하는 작업은 O(N)입니다. 이 작업을 N번 반복하므로 전체 시간 복잡도는 O(N^2)입니다.
- **공간 복잡도**: 리스트와 결과를 저장하기 위해 O(N)의 공간이 필요합니다. 전체적으로 공간 복잡도는 O(N)입니다.

### 세부 구현에서 중요한 부분:

1. **인덱스 관리**:
    - 리스트의 크기가 줄어들면서 인덱스를 정확히 조정하는 것이 중요합니다. 이 작업이 잘못되면 예상치 못한 오류가 발생할 수 있습니다.
2. **결과 형식**:
    - 마지막에 결과를 출력할 때 포맷팅을 정확하게 유지해야 합니다. 특히 마지막 요소 뒤에 `,` 가 붙지 않도록 주의해야 합니다. 이 부분을 `if (list.isNotEmpty())` 조건으로 처리합니다.

## 3. 해석

### 1. **리스트 초기화**:

- 1부터 `N`까지의 숫자로 이루어진 리스트를 생성합니다.
- 예를 들어, `N = 7`이면 리스트는 `[1, 2, 3, 4, 5, 6, 7]`이 됩니다.

### 2. **K번째 사람 제거**:

- 초기 인덱스는 `K-1`로 설정합니다.
- 리스트의 인덱스를 활용하여 매번 `K`번째 사람을 제거하고, 결과 리스트에 추가합니다.
- 제거된 후에도 인덱스를 업데이트하여 다음 제거할 사람을 찾습니다.
- 리스트가 비어있을 때까지 이 작업을 반복합니다.

### 3. **결과 형식 맞추기**:

- 모든 사람이 제거된 후, 결과를 "<>"로 감싸서 출력 형식에 맞춥니다.
- 예를 들어, `N = 7`, `K = 3`일 때 최종 출력은 `<3, 6, 2, 7, 5, 1, 4>`가 됩니다.

## 코드 구현:

```kotlin
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val firstLine = readLine()
    val stringTokenizer = StringTokenizer(firstLine)
    val n = stringTokenizer.nextToken().toInt()
    val k = stringTokenizer.nextToken().toInt()

    val result = JosephusProblem.solution(n, k)
    BufferedWriter(OutputStreamWriter(System.out)).use { writer ->
        writer.write(result)
    }
}

object JosephusProblem {
    fun solution(n: Int, k: Int): String {
        val list = MutableList(n) { it + 1 }
        var removeIndex = k - 1
        val result = StringBuilder("<")

        while (list.isNotEmpty()) {
            if (removeIndex >= list.size) {
                removeIndex %= list.size
            }
            result.append(list.removeAt(removeIndex))
            if (list.isNotEmpty()) {
                result.append(", ")
            }
            removeIndex += k - 1
        }
        result.append(">")
        return result.toString()
    }
}

```

이 문서에서는 요세푸스 문제를 해결하기 위해 리스트를 초기화하고, 특정 인덱스의 요소를 제거하며 인덱스를 조정하는 방법에 대해 설명했습니다. 또한, 결과를 출력할 때의 포맷팅도 중요한 포인트로 다루었습니다.