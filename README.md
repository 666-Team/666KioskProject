# KioskProject

![image](https://github.com/wonowonow/KioskProject/assets/123870616/89af0bab-db1c-4ea2-8a57-abe2554bb68d)
<br>
**개발 기간 (2023-10-18 ~ 2023-10-23)**

# 주요 기능
* **디스플레이**
  * 메인 메뉴 표시
  * 상품 메뉴 선택 및 표시
  <br>
* **주문**
  * 장바구니 추가
  * 장바구니 취소
  * 장바구니 상품 결제 (장바구니 비어있을 시에는 결제 불가)
  * 주문번호 출력
   <br>
* **Admin**
  * Admin 메뉴
  * 총 판매 금액 현황 확인
  * 총 판매 상품 목록 현황 확인
  * 비밀번호 기능
  * 비밀번호 변경 기능
  <br>
   
<h1>2023-10-18</h1>
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F14N8V%2FbtsyLGN4qCe%2FBZHkq7kvRK6rkSTtUYkvZ0%2Fimg.png"><br>
Menu Class
메뉴 클래스 인스턴스화를 배열로 시켜 생성자에 매개변수를 Name, Explain으로 받고<br>
메인화면에 출력시킬 것이다<br>
 
Product Class
메뉴 클래스 설명과 비슷한데, 추가로 필드에 price를 추가하고 생성자에 매개변수를 Name, Explain, Price로 받고 상품 선택화면에 출력할 것이다.<br>
 
Order Class<br>
basket이라는 List에 주문한 것을 담아야한다.<br>
addOrder 메소드를 이용해 담아 줄 것이고<br>
cancleOrder 메소드를 통해 주문을 취소하면 List 클리어를 할 것이다.<br>
getOrder메소드를 사용하면 basket에 담긴 리스트를 보여줄것이고<br>
orderNumber메소드 통해 주문 번호를 뽑아주는 메소드<br>
returnMain 메소드를 통해 메인화면으로 돌아가는 메소드를 만들 것이다.<br>

이렇게 계획했다.<br>

<h1>2023-10-19</h1>

* **장바구니**
  * 장바구니에 상품 추가
  * 장바구니에 담긴 상품 확인 + 장바구니 내의 상품 가격 합계
  * 장바구니 비우기
  * 주문 및 주문번호 출력

이제 리팩토링을 해야하는데 진짜 감이 안잡힌다..
사실 진짜 뼈대만 잡아놓은 느낌이라서
Class의 사용처를 명확히 하고
메소드를 진짜 잘 활용하는 과정으로 리팩토링을 하고 싶은데...

<h1>2023-10-20</h1>
자바의 정석 공부

<h1>2023-10-23</h1>

Display 클래스 적극 활용하기 위해 Main에 있던 것들 Display 메소드로 변경<br>
Order에 있었던 SalesTotalPrice 메소드 Display에 넣기<br>
* **Admin**
  * Admin Menu 추가
  * 판매 상품 목록 SalesTotalProduct 메소드 추가
  * Admin 비밀번호 변경 기능 추가
    
![image](https://github.com/wonowonow/KioskProject/assets/123870616/89af0bab-db1c-4ea2-8a57-abe2554bb68d)

