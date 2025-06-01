<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Quiz Result</title>
  </head>
  <body>
    <h1>Quiz Finished!</h1>
    <p>Your score: ${sessionScope.score} out of 1</p>
    <%-- Adjust '1' if you add more questions --%>
    <p><a href="quiz">Try Again</a></p>
    <p><a href="index.jsp">Home</a></p>
  </body>
</html>
