package uz.qodirov.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import uz.qodirov.dto.user.UserUpdateDto;
import uz.qodirov.entity.quiz.Question;
import uz.qodirov.entity.quiz.Quiz;
import uz.qodirov.entity.user.User;
import uz.qodirov.mappers.quiz.QuizMapper;
import uz.qodirov.mappers.test.TestMapper;
import uz.qodirov.mappers.userMapper.UserMapper;
import uz.qodirov.repository.UserRepository.UserRepository;
import uz.qodirov.repository.quiz.QuizRepository;
import uz.qodirov.repository.test.TestRepository;
import uz.qodirov.services.question.QuestionMarkService;
import uz.qodirov.services.quiz.QuizService;
import uz.qodirov.services.test.TestService;
import uz.qodirov.services.users.UserService;
import uz.qodirov.ui.QuizUI;
import uz.qodirov.ui.TestUI;
import uz.qodirov.ui.UserUI;
import uz.qodirov.utils.BaseUtils;
import uz.qodirov.utils.validators.UserValidator;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ApplicationContextHolder {
    private static MongoDatabase db;
    private final static UserRepository USERREPOSITORY;
    private final static UserService USER_SERVICE;
    private final static UserMapper USER_MAPPER;

    private final static UserValidator USER_VALIDATOR;
    private final static BaseUtils BASE_UTILS;
    private final static UserUI USER_UI;
    private final static UserUpdateDto USER_UPDATE_DTO;

    private final static QuizRepository QUIZ_REPOSITORY;
    private final static QuizService QUIZ_SERVICE;
    private final static QuizMapper QUIZ_MAPPER;
    private final static QuizUI QUIZ_UI;

   private final static QuestionMarkService QUESTIONMARKSERVICE;


    private final static TestRepository TEST_REPOSITORY;
    private final static TestService TEST_SERVICE;
    private final static TestMapper TEST_MAPPER;
    private final static TestUI TEST_UI;

    static {
        connect();
        BASE_UTILS = new BaseUtils();
        USERREPOSITORY = new UserRepository(User.class);
        USER_MAPPER = new UserMapper();
        USER_VALIDATOR = new UserValidator(BASE_UTILS);
        USER_SERVICE = new UserService(USERREPOSITORY, USER_MAPPER, USER_VALIDATOR);
        USER_UI = new UserUI(USER_SERVICE);
        USER_UPDATE_DTO = new UserUpdateDto();

        QUESTIONMARKSERVICE = new QuestionMarkService();

        TEST_MAPPER = new TestMapper();
        TEST_REPOSITORY = new TestRepository(Question.class);
        TEST_SERVICE = new TestService(TEST_REPOSITORY, TEST_MAPPER);
        TEST_UI = new TestUI(TEST_SERVICE);

        QUIZ_MAPPER = new QuizMapper();
        QUIZ_REPOSITORY = new QuizRepository(Quiz.class);
        QUIZ_SERVICE = new QuizService(QUIZ_REPOSITORY, QUIZ_MAPPER);
        QUIZ_UI = new QuizUI();

    }

    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz.getSimpleName());
    }

    private static <T> T getBean(String beanName) {
        return switch (beanName.toUpperCase(Locale.ROOT)) {
            case "MONGODATABASE" -> (T) db;
            case "USERREPOSITORY" -> (T) USERREPOSITORY;
            case "USERSERVICE" -> (T) USER_SERVICE;
            case "BASEUTILS" -> (T) BASE_UTILS;
            case "USERMAPPER" -> (T) USER_MAPPER;
            case "USERVALIDATOR" -> (T) USER_VALIDATOR;
            case "USERUPDATEDTO" -> (T) USER_UPDATE_DTO;
            case "USERUI" -> (T) USER_UI;

            case "QUESTIONMARKSERVICE" -> (T) QUESTIONMARKSERVICE;

            case "TESTMAPPER" -> (T) TEST_MAPPER;
            case "TESTREPOSITORY" -> (T) TEST_REPOSITORY;
            case "TESTSERVICE" -> (T) TEST_SERVICE;
            case "TESTUI" -> (T) TEST_UI;

            case "QUIZMAPPER" -> (T) QUIZ_MAPPER;
            case "QUIZREPOSITORY" -> (T) QUIZ_REPOSITORY;
            case "QUIZSERVICE" -> (T) QUIZ_SERVICE;
            case "QUIZUI" -> (T) QUIZ_UI;


            default -> throw new RuntimeException("Bean Not Found");
        };
    }


    private static void connect() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

        Logger rootLogger = Logger.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        try {
            MongoClient mongoClient = MongoClients.create(clientSettings);
            db = mongoClient.getDatabase("quizApp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
