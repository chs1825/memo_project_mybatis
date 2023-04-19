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
<button type="button" id="uploadBtn">파일 업로드 진행</button>

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
                console.log(res.listData);

                let resDiv = document.querySelector('#resDiv');
                Object.keys(res.mapData).forEach(function(key) {
                    let div = document.createElement('div');
                    div.textContent = res.mapData[key];
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
