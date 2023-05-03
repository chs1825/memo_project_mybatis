<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/05/02
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<hr>
슬픈점 :
1. 디비 덤프 하는데 안되서 삽질...시간 낭비(그래도 성공)
2. 오라클 연결하는데 삽질...(근데 실패).. 진짜 시간 낭비



변경사항
□ 수형 그림(sign_hand) 테이블
    1. (일단 오키) 컬럼추가(hand_variation) >> hand_variation 관리 테이블 포린키로 넣기(왜 안되지???) >> null 값 모두 제거해주고 not 설정이 필요할듯
    2. 데이터 추가(손위치/한손두손/같은모양다른모양)
    3.

□ 표제어(sign_word) 테이블
    1. (일단 오키)컬럼 추가 3개(hand_position/hand_number/hand_matching_type)
       속성은 sign_hand랑 동일
    2. 데이터 넣어주기
    3.

□ hand_variation 관리(메타데이터 저장)테이블 >> 나중일
    0. 시퀀스 넘버 pk 자동생성
    1. id 컬럼(hand/hand_position/hand_number/hand_matching_type) varchar(20)
    2. 설명 컬럼 varchar100
    3. 필요한 컬럼 추가 가능
    4. 생성 목적 : 수형 그림이 두가지로 나뉜다면 등록 작업이 필요할 것 같아서 대비 차원





다시 생각해보기
1. 기존 (sign_hand) 테이블 유지 >>  한손두손 컬럼 늘려주고
    수형 종류도 체크 필요 >> 변형 표현??


3. 표제어(sign_word)
    방안1 : 테이블 컬럼 추가 >> 손위치/한손두손/같은모양다른모양
    썸네일 추가 대응도 필요

4. 검색시 쿼리문 조인해서 쿼리를 짜고 검색조건이 없는것은 where 절 조건을 고정시켜서 넘기면 될듯

5. 궁금한점 수어사전 서비스에서 보면 3가지 조건 모두 넣어야 마지막 같은모양 다른모양 선택이 가능하던데 이러한 점도 염두에 둬야하는지?
    프론트에서 조작하면 가능할 것 같긴한데 디비 구조에 영향이 있어야 하는지..?






</body>
</html>
