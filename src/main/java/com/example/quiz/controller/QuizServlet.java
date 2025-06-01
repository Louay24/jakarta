package com.example.quiz.controller;

import com.example.quiz.model.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    private List<Question> questions;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize a sample question
        questions = new ArrayList<>();
        questions.add(new Question(1, "What is 2 + 2?", Arrays.asList("3", "4", "5"), 1));
        // Add more questions here for a full quiz
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Integer currentQuestionIndex = (Integer) session.getAttribute("currentQuestionIndex");
        if (currentQuestionIndex == null) {
            currentQuestionIndex = 0;
            session.setAttribute("currentQuestionIndex", currentQuestionIndex);
            session.setAttribute("score", 0);
        }

        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            request.setAttribute("question", currentQuestion);
            request.getRequestDispatcher("/WEB-INF/jsp/quiz.jsp").forward(request, response);
        } else {
            // Quiz finished, show score
            request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer currentQuestionIndex = (Integer) session.getAttribute("currentQuestionIndex");
        Integer score = (Integer) session.getAttribute("score");

        if (currentQuestionIndex == null) {
            response.sendRedirect("quiz"); // Should not happen if started correctly
            return;
        }

        String answerParam = request.getParameter("answer");
        if (answerParam != null) {
            int selectedOption = Integer.parseInt(answerParam);
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (selectedOption == currentQuestion.getCorrectAnswerIndex()) {
                score++;
                session.setAttribute("score", score);
            }
        }

        currentQuestionIndex++;
        session.setAttribute("currentQuestionIndex", currentQuestionIndex);
        response.sendRedirect("quiz"); // Go to next question or result
    }
}