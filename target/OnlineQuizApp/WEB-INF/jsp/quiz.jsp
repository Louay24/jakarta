<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Question</title>
</head>
<body>
    <h1>Question ${question.id}</h1>
    <p>${question.text}</p>

    <form method="POST" action="quiz">
        <c:forEach var="option" items="${question.options}" varStatus="loop">
            <input type="radio" name="answer" value="${loop.index}" id="option${loop.index}">
            <label for="option${loop.index}">${option}</label><br>
        </c:forEach>
        <br>
        <input type="submit" value="Next">
    </form>
</body>
</html>