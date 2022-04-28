package uz.qodirov.services.question;

import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.dto.quiz.QuizCreateDto;
import uz.qodirov.entity.quiz.Question;
import uz.qodirov.entity.quiz.QuestionMark;
import uz.qodirov.services.test.TestService;

import java.util.ArrayList;
import java.util.List;

public class QuestionMarkService {

    private final TestService questionService = ApplicationContextHolder.getBean(TestService.class);

    public List<QuestionMark> getList(QuizCreateDto quizDto) {
        List<QuestionMark> questionMarks = new ArrayList<>();
        List<Question> list = questionService.getListQuestionByCriteria(quizDto);
        for (Question question : list) {
            questionMarks.add(QuestionMark.childBuilder().question(question).build());
        }
        return questionMarks;
    }

}
