package uz.qodirov.repository.test;

import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import uz.qodirov.criteria.GenericCriteria;
import uz.qodirov.dao.GenericDao;
import uz.qodirov.entity.quiz.Question;
import uz.qodirov.repository.GenericCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TestRepository extends GenericDao<GenericCriteria, Question> implements GenericCrudRepository<Question, ObjectId> {

    public TestRepository(Class<Question> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId creat(Question model) {
        collection.insertOne(model);
        return model.getId();
    }

    @Override
    public void update(Question model) {

    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public List<Question> list() {
        List<Question> questions = new ArrayList<>();
        collection.find().iterator().forEachRemaining(questions::add);
        return questions;
    }

    @Override
    public Optional<Question> get(ObjectId id) {
        Question question = collection.find(Filters.eq("_id", id)).first();
        return Objects.isNull(question) ? Optional.empty() : Optional.of(question);
    }
}
