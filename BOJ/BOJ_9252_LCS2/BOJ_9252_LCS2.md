## [📌 9252번](https://www.acmicpc.net/problem/9252)

## 문제 요약

> 두 문자열이 주어졌을 때, LCS(Longest Common Subsequence, 최장 공통 부분 수열)을 찾으세요.
>
> 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.<br/>
> 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.<br/>
> LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.

## 💡 생각 및 풀이

### 1. dp 배열

먼저 첫번째 열```dp[i][0]```과 첫번째 행```dp[0][j]```에서 매칭되는 문자가 등장한다면 다음 모든 칸에서 공통 문자가 되므로, 그 이후 칸들의 값을 1로
고정합니다.

- 문자열 A의 i번째 글자와 문자열 B의 j번째 글자가 **같을 때**

  이 경우는 공통 부분 수열에 포함되므로, 이전 문자들까지의 LCS의 길이에 1을 더해줍니다.


- 문자열 A의 i번째 글자와 문자열 B의 j번째 글자가 **다를 때**

  공통 부분 수열에 포함되지 않으므로, A[i]를 제외하고 B의 이전 부분과 비교한 결과(```dp[i-1][j]```)와 B[j]를 제외하고 A의 이전 부분을 비교한 결과(
  ```dp[i][j-1]```) 중 더 긴 길이를 선택합니다.

따라서 나머지 행과 열은 다음 점화식에 따라 채워줍니다.

$$ dp[i][j]=
\begin{cases}
max(dp[i−1][j],dp[i][j−1]), \;if\; A[i] = B[j]\\
dp[i−1][j−1]+1, \;if\; A[i] \neq B[j]
\end{cases}
$$

그리고 LCS가 존재한다면, dp 배열을 활용하여, 거꾸로 LCS 문자열을 추적합니다.

최종 위치였던 ```(str.length() - 1, str2.length() - 1)```에서 시작하여, dp 배열을 계산할때와 마찬가지로 현재 위치에서 두 문자가 같으면
```dp[i-1][j-1]```로 이동하고, 두 문자가 다르면, ```dp[i-1][j]```와 ```dp[i][j-1]``` 중 더 큰 값의 위치로 i 또는 j가 0이 될
때까지
이동합니다.

😅```i==0 | j==0``` 일때, ```dp[i][j]``` 값이 0인 경우가 있으므로, 0보다 큰 경우에만 저장해야 합니다.