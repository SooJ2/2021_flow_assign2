# 푸릇푸릇(FruitFruit)
직관적이고 등록이 간편한 식단 관리 앱

## 개발자
이수정
이원재

## 요약
- Kakao SDK
- Kotlin, Android Sutdio, Django(REST Framework), Retrofit 
- Calender, Feed tab


<img src="https://user-images.githubusercontent.com/64190044/148938021-a5fcc7b7-9256-4fdf-9acc-ea5ff6b7b84f.jpg" width="200" height="444"/> <img src="https://user-images.githubusercontent.com/64190044/148938033-9a2b2026-b96a-4f11-a744-f6155287d262.jpg" width="200" height="444"/> 

## 사용
### Calendar
- 서버에 저장된 정보를 불러와 지난날 동안 자신이 섭취한 총 칼로리를 계산한다.
- 달력에 섭취한 칼로리 양에 따라 다른 아이콘을 배치하여 일일 섭취량을 표현한다.(데이터의 시각화)  

#### 고기  
일일 섭취 칼로리가 평균 이상일 경우  
#### 계란  
일일 섭취 칼로리가 평균 수준일 경우  
#### 양상추  
일일 섭취 칼로리가 일정 수준 이하일 경우  

<img src="https://user-images.githubusercontent.com/64190044/148938141-32cfcea8-8ea9-417f-9890-fbc3cf15a0b1.jpg" width="200" height="444"/> 

### Feed
- 서버에 저장된 정보를 불러와 최근에 올린 피드를 보여줌
- 리싸이클러 뷰 안에 리싸이클러 뷰를 넣어 아래위, 좌우로 슬라이드 할 수 있다.

<img src="https://user-images.githubusercontent.com/64190044/148938210-11063532-7a35-4506-8f86-50e0ae0c00cf.jpg"  width="200" height="444"/>

### ADD DIET
- 'ADD DIET' 버튼을 누르면 검색, 바코드 스캔 중 선택할 수 있다.
- 검색의 경우 서버에 저장된 db에서 검색하여 칼로리를 알려준다.
- 바코드 스캔의 경우 공공 API를 이용하여 제품의 이름을 얻고, 서버에 저장된 db의 데이터 이름과 대조하여 칼로리를 알려준다.

<img src="https://user-images.githubusercontent.com/64190044/148938483-be3edfd9-2270-46b4-aa6f-56a619c7ecc7.jpg"  width="200" height="444"/> <img src="https://user-images.githubusercontent.com/64190044/148938485-8245f654-d349-45ac-b66a-41af424eaf9b.jpg"  width="200" height="444"/> <img src="https://user-images.githubusercontent.com/64190044/148938450-29f43a62-d8f9-415c-b57b-12251facf07a.jpg"  width="200" height="444"/> 

