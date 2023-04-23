<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/04/18
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h5>여기는 엑셀 페이지</h5>
<br>

<input type="file" id="file" value="파일 선택">
<button type="button" id="uploadBtn">엑셀 데이터 디비 저장하기</button>

<hr>
<div>
    <h5>엑셀 내용</h5>
    <div id="resDiv"></div>
</div>

</body>
<script>
    let upload = document.querySelector('#uploadBtn');
    upload.addEventListener('click',function(){
        let file = document.querySelector('#file');
        const excelFile = file.files[0];
        let form = new FormData();
        form.append('excelFile', excelFile);

        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'uploadExcel.do');
        xhr.setRequestHeader('enctype', 'multipart/form-data');
        xhr.setRequestHeader('X-Requested-With','XMLHttpRequest');

        xhr.onload = function (){
            if(xhr.status === 200){
                console.log(xhr.response);

                var res = JSON.parse(xhr.responseText);
                console.log(res.mapData);

                let resDiv = document.querySelector('#resDiv');

                res.mapData.forEach(function(row) {
                    let div = document.createElement('div');
                    row.forEach(function(item) {
                        let span = document.createElement('span');
                        let keys = Object.keys(item);
                        span.textContent = keys[0] + ': ' + item[keys[0]];
                        div.appendChild(span);
                    });
                    resDiv.appendChild(div);
                });



            }else{
                console.log("error");
            }
        }
        xhr.send(form);
    });


</script>

</html>
