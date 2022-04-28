package uz.qodirov.services.quiz;

import org.bson.types.ObjectId;
import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.dto.quiz.QuizCreateDto;
import uz.qodirov.entity.quiz.QuestionMark;
import uz.qodirov.entity.quiz.Quiz;
import uz.qodirov.mappers.quiz.QuizMapper;
import uz.qodirov.repository.quiz.QuizRepository;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;
import uz.qodirov.services.AbstractService;
import uz.qodirov.services.question.QuestionMarkService;

import java.util.List;

public class QuizService extends AbstractService<QuizRepository, QuizMapper> {

  private final static QuestionMarkService questionMarkService = ApplicationContextHolder.getBean(QuestionMarkService.class);


    public QuizService(QuizRepository repository, QuizMapper mapper) {
        super(repository, mapper);
    }

    public ResponseEntity<Data<ObjectId>> create(QuizCreateDto dto) {
        try {
             //  QuestionMarkService questionMarkService = new QuestionMarkService();

            //            validator.validOnCreate(dto);
            Quiz quiz = mapper.fromCreateDto(dto);
            List<QuestionMark> questionMarks = questionMarkService.getList(dto);
            quiz.setQuestionsMarks(questionMarks);
            return new ResponseEntity<>(new Data<>(repository.creat(quiz)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public ResponseEntity<Data<Quiz>> get(ObjectId id) {
        return new ResponseEntity<>(new Data<>(repository.get(id).get()));
    }


    public void update(Quiz quiz) {
        repository.update(quiz);
    }
}
