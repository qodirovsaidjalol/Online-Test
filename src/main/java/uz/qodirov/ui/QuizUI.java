package uz.qodirov.ui;

import org.bson.types.ObjectId;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;
import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.dto.quiz.QuizCreateDto;
import uz.qodirov.entity.quiz.Question;
import uz.qodirov.entity.quiz.QuestionMark;
import uz.qodirov.entity.quiz.Quiz;
import uz.qodirov.entity.quiz.Variant;
import uz.qodirov.enums.Level;
import uz.qodirov.enums.Subject;
import uz.qodirov.enums.language.Language;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;
import uz.qodirov.services.quiz.QuizService;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import static uz.jl.utils.Color.YELLOW;

public class QuizUI {
    QuizService service = ApplicationContextHolder.getBean(QuizService.class);

    public void play_quiz() {
        QuizCreateDto dto = QuizCreateDto.builder()
                .subject(Subject.getCode(Input.getStr("Subject : ")))
                .level(Level.getLevel(Input.getStr("Level : ")))
                .language(Language.getByCode(Input.getStr("Language : ")))
                .count(Input.getNum("Count : "))
                .questionsMarks(new ArrayList<>())
                .build();
        ResponseEntity<Data<ObjectId>> response = service.create(dto);

        if (Objects.isNull(response)) {
            Print.println(Color.RED, "ERROR");
            return;
        }
        String choice = Input.getStr("Are you ready to start test? Y../No: ");
        if (choice.startsWith("Y") || choice.startsWith("y")) {
            Print.println(YELLOW, "==================Start======================");
            answerQuiz(response.getData().getBody());
        }
    }

    private void answerQuiz(ObjectId quizID) {
        Quiz quiz = service.get(quizID).getData().getBody();
        int count = 0;
        int a = 67;
        for (QuestionMark questionsMark : quiz.getQuestionsMarks()) {
            Question question = questionsMark.getQuestion();
            Print.println(Color.BLUE, (++count) + ". " + question.getTitle());
            for (Variant variant : question.getVariants()) {
                Print.println(Color.GREEN, ((char) a++) + ". " + variant.getAnswer());
            }
            char answer = Input.getStr("Your Answer : ").toUpperCase(Locale.ROOT).charAt(0);
            Variant variant = question.getVariants().get(answer - 65);
            questionsMark.setRight(variant.isCorrect());
            questionsMark.setChosenAnswerId(variant.getId());
        }
        Print.println(Color.BLACK, "==================End======================");
        service.update(quiz);
        showResults(quiz);
    }

    private void showResults(Quiz quiz) {

    }

    public void result_quizes() {

    }
}
