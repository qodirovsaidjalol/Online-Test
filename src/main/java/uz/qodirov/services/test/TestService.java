package uz.qodirov.services.test;

import org.bson.types.ObjectId;
import uz.qodirov.dto.quiz.QuizCreateDto;
import uz.qodirov.dto.test.QuestionCreateDto;
import uz.qodirov.dto.test.QuestionUpdateDto;
import uz.qodirov.entity.quiz.Question;
import uz.qodirov.mappers.test.TestMapper;
import uz.qodirov.repository.test.TestRepository;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;
import uz.qodirov.services.AbstractService;
import uz.qodirov.services.GenericCrudService;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestService extends AbstractService<TestRepository, TestMapper> implements GenericCrudService<Question, QuestionCreateDto, QuestionUpdateDto, ObjectId> {


    public TestService(TestRepository repository, TestMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<ObjectId>> create(QuestionCreateDto dto) {
        try {

//            validator.validOnCreate(dto);
            Question question = mapper.fromCreateDto(dto);
            return new ResponseEntity<>(new Data<>(repository.creat(question)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<Void>> update(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Question>> get(ObjectId id) {
        return new ResponseEntity<>(new Data<>(repository.get(id).get()));
    }

    @Override
    public ResponseEntity<Data<List<Question>>> getList() {
        return new ResponseEntity<>(new Data<>(repository.list()));
    }


    public List<Question> getListQuestionByCriteria(QuizCreateDto quizDto) {
        List<Question> questions = repository.list();
        questions.removeIf(question -> !(question.getLevel().getCode().
                equals(quizDto.getLevel().getCode()) && question.isDeleted() &&
                question.getSubject().getCode().equals(quizDto.getSubject().getCode())));

        return randomQuestion(quizDto.getCount(), questions);
    }

    private List<Question> randomQuestion(int count, List<Question> questions) {
        List<Question> QUESTIONS = new ArrayList<>();
        Set<Integer> numbers = getRandomNumbers(count, questions.size());
        for (Integer number : numbers) {
            QUESTIONS.add(questions.get(number));
        }
        return QUESTIONS;
    }

    private Set<Integer> getRandomNumbers(int count, int max_value) {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < count; i++) {
            numbers.add((int) (Math.random() * max_value) + 1);
        }
        return numbers;
    }
}
