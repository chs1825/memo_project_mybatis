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

<br>
<label for="selectHand">수형그림</label>
<select id="selectHand">
    <option value="" selected>선택하지 않음</option>
</select>
<br>
<label for="selectPosition">손 위치</label>
<select id="selectPosition">
    <option value="" selected>선택하지 않음</option>
</select>
<br>
<label for="selectNumber">한 손/두 손</label>
<select id="selectNumber">
    <option value="" selected>선택하지 않음</option>
</select>
<br>
<label for="selectMatchingType">같은 모양/다른 모양</label>
<select id="selectMatchingType">
    <option value="" selected>선택하지 않음</option>
</select>
<br>
<button type="button" onclick="searchWord()">표제어 검색하기</button>
<hr>
<div id="resDiv"></div>


<br><br><br><br><br><br><br><br>
<hr>

◎ 디비 변경사항
□ 수형 그림(sign_hand) 테이블
1. 컬럼추가(hand_variation)
    속성 : varchar(20), not null
    데이터 구분(tbl_hand_variation 테이블 참고) : hand, hand_position, hand_number, hand_matching_type
    제한사항 : metadata 관리할 variation 테이블에서 foreign key 관계가 안맺어지는 상태
    현 상황 : 외래키 관계 맺지 않고 일단 값 넣음

□ 표제어(sign_word) 테이블
1. 컬럼 추가 3개(hand_position/hand_number/hand_matching_type)
    속성 : varchar(100), nullable
    데이터 구분 : tbl_sign_hand 테이블 hand_id 값
2. 썸네일 관련 컬럼 추후 추가 필요(동영상, 사진 관리 방법에 따라 달라짐 >> 현재는 tbl_file에서 통으로 관리중)

□ hand_variation 관리(메타데이터 저장)테이블 >> 필요없으면 굳이 관리 하지 않아도 됨
0. 시퀀스 넘버 pk 자동생성
1. id 컬럼(hand/hand_position/hand_number/hand_matching_type) varchar(20)
2. 설명 컬럼 varchar100
3. 필요한 컬럼 추가 가능
4. 생성 목적 : 수형 그림이 두가지로 나뉜다면 등록 작업이 필요할 것 같아서 대비 차원()

◎ 검색 기능
크게 구현상 이상없음

◎  디비 변경에 따른 등록 기능 추가 소요 사항 제안
1. 미시정보관리 >> 수형 그림 관리 페이지
    현 상태 : hand_variation 등록할 버튼 및 폼 없음
    대안1 : 수형 그림코드 등록 버튼을 hand_variation 등록 버튼으로 변경
           >> (현재 hand_code로 연결되어 디비에 저장되는데 사용하지는 않는 것으로 확인 >> 상원 대리님 확인 필요 )
    대안2 : hand_variation 등록 버튼 하나 더 추가

2. 표제어 관리 >> 표제어 상세보기 버튼
    현 상태 : tbl_sign_word에 등록되어 있는 수형그림(sign_hand)만 표현됨
    대안 : 수형그림 이미지만 대표로 출력해줄 것인지 or 추가된 컬럼(손 위치, 양손 여부, 같은모양 여부) 이미지를 함께 표현해줄 것인지 결정필요

3. 표제어 집필 >> 집필 및 감수 작업 표제어부
    현 상태 : 수형그림(sign_hand)만 등록 가능
    대안 : 수형 그럼 선택시 모달창에서 variation별로 수형이미지 select 하여 이미지 출력 및 선택 기능 개발

◎ 통계 관리
    1. 통계관리 >> 미시정보 통계
    variation 별로 구분해서 select해오는 통계만 추가해주면 이상없을 것 이라고 판단됨

작업이력 ???????????????????????????????????????????????????????????????????????????????

</body>

<script>
    window.onload = function () {
        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'sendData.do');
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let response = JSON.parse(xhr.response);
                console.log(response);
                let selectHand = document.getElementById("selectHand");
                let selectPosition = document.getElementById("selectPosition");
                let selectNumber = document.getElementById("selectNumber");
                let selectMatchingType = document.getElementById("selectMatchingType");

                // 셀렉트 박스에 옵션 추가
                for (let i = 0; i < response.hand.length; i++) {
                    let option = document.createElement("option");
                    option.text = response.hand[i].handId;
                    selectHand.add(option);
                }

                for (let i = 0; i < response.position.length; i++) {
                    let option = document.createElement("option");
                    option.text = response.position[i].handId;
                    selectPosition.add(option);
                }

                for (let i = 0; i < response.number.length; i++) {
                    let option = document.createElement("option");
                    option.text = response.number[i].handId;
                    selectNumber.add(option);
                }

                for (let i = 0; i < response.matchingType.length; i++) {
                    let option = document.createElement("option");
                    option.text = response.matchingType[i].handId;
                    selectMatchingType.add(option);
                }


            }
        };
        xhr.send();
    }

    function searchWord(){
        let hand = document.querySelector("#selectHand").value;
        let position = document.querySelector("#selectPosition").value;
        let number = document.querySelector("#selectNumber").value;
        let matchingType = document.querySelector("#selectMatchingType").value;
        console.log(JSON.stringify({hand, position, number, matchingType}));


        let xhr = new XMLHttpRequest();
        xhr.open('POST','searchWord.do');
        xhr.setRequestHeader('Content-Type', 'application/json');
        // xhr.setRequestHeader('Content-Type', 'text/plain');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log(xhr.responseText);
                let res = JSON.parse(xhr.response);
                console.log(res);

                let resDiv = document.querySelector("#resDiv");
                resDiv.innerHTML = ""; // 이전 결과를 지웁니다.
                if (res.length === 0) {
                    resDiv.innerHTML = "일치하는 결과가 없습니다.";
                } else {
                    let ul = document.createElement("ul");
                    res.forEach((res) => {
                        let li = document.createElement("li");
                        li.textContent = "표제어 ID : " + res.signWordId + ",      표제어명 : " +  res.signTypeNm;
                        ul.appendChild(li);
                    });
                    resDiv.appendChild(ul);
                }



                // 결과를 처리하는 코드를 작성하세요.
            }else {
                console.error();
            }
        };
        let data = JSON.stringify({hand, position, number, matchingType})
        xhr.send(data);


    }


</script>


</html>
