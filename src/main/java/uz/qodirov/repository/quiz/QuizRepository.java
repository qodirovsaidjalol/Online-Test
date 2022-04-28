package uz.qodirov.repository.quiz;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import uz.qodirov.criteria.GenericCriteria;
import uz.qodirov.dao.GenericDao;
import uz.qodirov.entity.quiz.Quiz;
import uz.qodirov.entity.user.User;
import uz.qodirov.services.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class QuizRepository extends GenericDao<GenericCriteria, Quiz> {

    public QuizRepository(Class<Quiz> clazz) {
        super(clazz);
    }


    public ObjectId creat(Quiz model) {
        User user = db.getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", SessionService.getSession().getId())).first();
        if (Objects.isNull(user.getQuizzes())) {
            user.setQuizzes(new ArrayList<Quiz>());
        }
        user.getQuizzes().add(model);
        BasicDBObject dbObject = new BasicDBObject("quizzes", user.getQuizzes());
        BasicDBObject updateDBObject = new BasicDBObject("$set", dbObject);
        db.getCollection(User.class.getSimpleName(), User.class).updateOne(Filters.eq("_id", SessionService.getSession().getId()), updateDBObject);
        return model.getId();
    }


    public List<Quiz> list() {
        return null;
    }


    public Optional<Quiz> get(ObjectId id) {
        User user = db.getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", SessionService.getSession().getId())).first();
        return user.getQuizzes().stream().filter(quiz -> quiz.getId().equals(id)).findAny();
    }

    public void update(Quiz quiz) {
        User user = db.getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", SessionService.getSession().getId())).first();
        List<Quiz> quizzes = user.getQuizzes();
        for (int i = 0; i < quizzes.size(); i++) {
            if (quizzes.get(i).equals(quiz.getId())) {
                quizzes.remove(i);
                quizzes.add(quiz);
            }
        }
        user.setQuizzes(quizzes);
        BasicDBObject basicDBObject = new BasicDBObject("quizzes", user.getQuizzes());
        BasicDBObject update = new BasicDBObject("$set", basicDBObject);
        db.getCollection(User.class.getSimpleName(),User.class).updateOne(Filters.eq("_id",SessionService.getSession().getId()),update);
    }
}
