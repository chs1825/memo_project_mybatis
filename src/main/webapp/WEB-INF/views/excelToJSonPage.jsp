<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/04/21
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>여기는 엑셀을 제이슨 파일로 만드는 페이지 입니다.</h4>

<input type="file" id="file" value="파일 선택">
<button type="button" id="convertBtn">변환하기</button>


</body>


<script>
    let convertExcel = document.querySelector('#convertBtn');
    convertExcel.addEventListener('click', function () {
        const excelFile = document.querySelector('#file').files[0];
        console.log(excelFile);
        let form = new FormData();
        form.append('excelFile', excelFile);
        console.log(form);


        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'convertExcel.do');
        xhr.setRequestHeader('enctype', 'multipart/form-data');
        xhr.setRequestHeader('X-Requested-With','XMLHttpRequest');

        xhr.onload = function (){
            if(xhr.status === 200){
                console.log("success");
                // console.log(xhr.responseText);
            }else{
                console.log('error');
            }
        }

        xhr.send(form);
    });
</script>
</html>
