<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/04/13
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="file" id='file' value="파일 선택">
<button type="button" id="uploadFile">파일 업로드</button>
</body>

<script>
    let uploadF = document.querySelector('#uploadFile');
    uploadF.addEventListener('click', function () {
        let file = document.querySelector('#file');
        console.log(file);
        console.log(file.files);
        console.log(file.files[0]);
        file = file.files[0];
        let form = new FormData();
        form.append('file',file);

        let xhr = new XMLHttpRequest();
        xhr.open('POST','uploadFile.do');
        xhr.setRequestHeader('enctype','multipart/form-data');
        // xhr.setRequestHeader('Content-Type','multipart/form-data');
        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        xhr.onload = function (){
            if(xhr.status === 200){
                console.log("success");
                console.log(xhr.responseText);
            }else{
                console.log("error");
            }
        }
        xhr.send(form);
    });


</script>


</html>
