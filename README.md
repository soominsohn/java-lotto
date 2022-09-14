# java-lotto

로또 미션 저장소

## Participants
앤지 & 조시

## About Mission  
### 기능 요구 사항  
로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.  
로또 1장의 가격은 1000원이다.  

### 실행 결과  

```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```


## 기능 목록

1. 입력받은 금액을 로또로 바꾸는 기능
2. 입력받은 금액을 로또 장수로 바꾸는 기능
3. 수동 로또 생성 기능
   1. 수동 로또 수를 입력받는다.
      1. 생성가능한 로또 장수 초과시 예외 발생.
      2. 0장 입력시 로또 번호를 입력받지 않는다.
4. 자동 로또 생성 기능
   1. 생성 가능한 로또 장수를 확인한다.
      1. 생성 가능한 로또 장수가 없을 시 자동 로또는 생성하지 않는다.
   2. 생성 가능한 로또 장수만큼 자동 로또 생성.
5. 당첨인지 확인하는 기능
   1. 당첨 번호를 입력 받는다.
   2. 보너스 번호를 입력 받는다.
   3. 당첨 등수를 확인한다.
6. 당첨 통계 내는 기능
7. 수익률 계산하는 기능


## 입출력 예시

구입금액을 입력해 주세요.  
14000

수동으로 구매할 로또 수를 입력해 주세요.  
3

수동으로 구매할 번호를 입력해 주세요.  
8, 21, 23, 41, 42, 43  
3, 5, 11, 16, 32, 38  
7, 11, 16, 35, 36, 44  

수동으로 3장, 자동으로 11개를 구매했습니다.  
[8, 21, 23, 41, 42, 43]  
[3, 5, 11, 16, 32, 38]  
[7, 11, 16, 35, 36, 44]  
[1, 8, 11, 31, 41, 42]  
[13, 14, 16, 38, 42, 45]  
[7, 11, 30, 40, 42, 43]  
[2, 13, 22, 32, 38, 45]  
[23, 25, 33, 36, 39, 41]  
[1, 3, 5, 14, 22, 45]  
[5, 9, 38, 41, 43, 44]  
[2, 8, 9, 18, 19, 21]  
[13, 14, 18, 21, 23, 35]  
[17, 21, 29, 37, 42, 45]  
[3, 8, 27, 30, 35, 44]  

지난 주 당첨 번호를 입력해 주세요.  
1, 2, 3, 4, 5, 6  
보너스 볼을 입력해 주세요.  
7  

당첨 통계  
---------  
3개 일치 (5000원)- 1개  
4개 일치 (50000원)- 0개  
5개 일치 (1500000원)- 0개  
5개 일치, 보너스 볼 일치(30000000원) - 0개  
6개 일치 (2000000000원)- 0개  
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
