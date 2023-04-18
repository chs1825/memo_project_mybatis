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

<div id="myTable"></div>
</body>
<script>
    window.onload = function () {
        const table = document.createElement('table');
        for (let i = 0; i < 10; i++) {
            const tr = document.createElement('tr');
            tr.style.border = "1px solid black";

            for (let j = 0; j < 10; j++) {
                const td = document.createElement('td');
                td.style.border = "1px solid black";
                tr.appendChild(td);
            }
            table.appendChild(tr);
        }
        table.style.border = "1px solid black";
        table.style.width = "1200px";
        table.style.height = "1200px";
        document.getElementById('myTable').appendChild(table);
    }

</script>


</html>
