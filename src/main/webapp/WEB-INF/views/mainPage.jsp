<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/04/05
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hello world
<br>
<button type="button" id="ajaxBtnNoAnotation" name="ajaxBtnName">ajax 어노테이션 없이 통신 테스트</button>
<br>
<button type="button" id="ajaxBtnReturnJson" name="ajaxBtnName">ajax 반환을 json에 model 담아서 반환 테스트</button>
<br>
<button type="button" id="ajaxBtnPost" name="ajaxBtnName">ajax Post 통신 테스트</button>
<br>
<button type="button" id="moveFilePage" onclick="mvFilePage()">파일리스트 페이지 이동</button>
<br>
<button type="button" id="moveExcelPage" onclick="location.href='excelPage.do'">엑셀 페이지 이동</button>
<br>
<button type="button" id="moveJsonPage" onclick="location.href='jsonPage.do'">엑셀 to 제이슨 페이지 이동</button>


<br>
<button type="button" id="logout" onclick="location.href='logout.do'">로그아웃 버튼</button>

</body>

</html>

<script>

    function mvFilePage() {
        location.href = "/filePage/start.do"
    }


    let mBtn = document.querySelector('#ajaxBtnReturnJson');
    mBtn.addEventListener('click', function () {
        let xhr = new XMLHttpRequest();
        let data = {
            id: 'ccc',
            name: 'test',
            email: 'ccc@ddd.comdad'
        }

        let encodedData = "";
        for (let key in data) {
            encodedData += encodeURIComponent(key) + "=" + encodeURIComponent(data[key]) + "&";
        }
        encodedData = encodedData.substring(0, encodedData.length - 1);

        xhr.open('POST', 'ajaxModel.do');
        xhr.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');

        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log("성공");
                console.log(xhr.responseText);
                console.log(xhr.responseText.test);
            } else {
                console.log("error");
            }
        }

        xhr.send(encodedData);

    });

    let tBtn = document.querySelector('#ajaxBtnNoAnotation');
    tBtn.addEventListener('click', function () {
        let xhr = new XMLHttpRequest();
        let data = {
            id: 'ccc',
            name: 'test',
            email: 'ccc@ddd.comads'
        }

        let encodedData = "";
        for (let key in data) {
            encodedData += encodeURIComponent(key) + "=" + encodeURIComponent(data[key]) + "&";
        }
        console.log(encodedData);
        encodedData = encodedData.substring(0, encodedData.length - 1);
        console.log(encodedData);

        xhr.open('POST', 'ajaxNoAnotation.do');
        xhr.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log("ajaxNoAnotation 성공");
                console.log(xhr.responseText);
            } else {
                console.log("ajaxNoAnotation 실패");
            }
        }
        xhr.send(encodedData);
    });

    let pBtn = document.querySelector('#ajaxBtnPost');
    pBtn.addEventListener('click', function () {
        let xhr = new XMLHttpRequest();
        let data = {
            id: 'ccc',
            name: 'test',
            email: 'ccc@ddd.asadf'
        }
        console.log(data);
        console.log(JSON.stringify(data));
        let jData = JSON.stringify(data);

        xhr.open('POST', 'ajaxPost.do');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log("ajax 성공!");
                console.log(xhr.responseText);
            } else {
                console.error(xhr.statusText);
            }
        };
        xhr.send(jData);
    });

</script>
