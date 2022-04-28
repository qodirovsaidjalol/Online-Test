package uz.qodirov.repository.UserRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.criteria.GenericCriteria;
import uz.qodirov.dao.GenericDao;
import uz.qodirov.dto.user.UserUpdateDto;
import uz.qodirov.entity.user.User;
import uz.qodirov.enums.status.Status;
import uz.qodirov.repository.GenericCrudRepository;

import java.lang.reflect.Field;
import java.util.*;

public class UserRepository extends GenericDao<GenericCriteria, User> implements GenericCrudRepository<User, ObjectId> {
    UserUpdateDto USER_UPDATE_DTO = ApplicationContextHolder.getBean(UserUpdateDto.class);
    public UserRepository(Class<User> clazz) {
        super(clazz);
    }

    public User login(String username, String password) {
        for (User user : collection.find()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equalsIgnoreCase(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    public boolean userActive(User user) {
        if (user.getStatus().name().equals("NO_ACTIVE")) {
            user.setStatus(Status.ACTIVE);
        }
        Document query = new Document().append("_id", user.getId());
        Bson updates = Updates.combine(Updates.set("status", "ACTIVE"));
        UpdateOptions updateOptions = new UpdateOptions().upsert(true);
        collection.updateOne(query, updates, updateOptions);
       return true;
    }




    @Override
    public ObjectId creat(User model) {
        collection.insertOne(model);
        return model.getId();

    }
/*
  MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase b4test = mongoClient.getDatabase("b4test");
        MongoCollection<Project> projectCollection = b4test.getCollection("project", Project.class);

  Bson filter = Filters.eq("_id", new ObjectId("61f126a24717673661c57513"));

        Project first = projectCollection.find(filter).first();

        ProjectUpdateDto projectUpdateDto = new ProjectUpdateDto();

 projectUpdateDto.setName("Gjkee");
        List<ProjectColumns> projectColumns = first.getProjectColumns();
        projectColumns.add(new ProjectColumns("Vchuuuuuuuuuuu"));
        projectUpdateDto.setProjectColumns(projectColumns);

    HashMap<String, Object> map = toMap(projectUpdateDto);

    ObjectMapper mapper = new ObjectMapper();
    HashMap<String, Object> hashMap = mapper.convertValue(projectUpdateDto, new TypeReference<>() {});
    BasicDBObject basicDBObject = new BasicDBObject(hashMap);
    BasicDBObject query = new BasicDBObject("$set", basicDBObject);
        projectCollection.updateOne(filter, query);
        */
    @Override
    public void update(User user) {
        Bson filter = Filters.eq("_id", new ObjectId(String.valueOf(user.getId())));
        User first = collection.find(filter).first();

        first.setUsername(user.getUsername());
        USER_UPDATE_DTO.setFullName(user.getFullName());
        USER_UPDATE_DTO.setLanguage(user.getLanguage());


        try {
            HashMap<String, Object> map = toMap(USER_UPDATE_DTO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> hashMap = mapper.convertValue(USER_UPDATE_DTO, new TypeReference<>() {});
        BasicDBObject basicDBObject = new BasicDBObject(hashMap);
        BasicDBObject query = new BasicDBObject("$set", basicDBObject);
        collection.updateOne(filter, query);



    }
    private static HashMap<String, Object> toMap(Object o) throws IllegalAccessException {
        var map = new HashMap<String, Object>();
        Class<?> aClass = o.getClass();

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object value = field.get(o);
            if (value != null) {
                map.put(field.getName(), value);
            }
        }
        return map;
    }

    @Override
    public void delete(ObjectId id) {
//        collection.updateOne()
    }

    @Override
    public List<User> list() {
        List<User> users = new ArrayList<>();
        collection.find().iterator().forEachRemaining(users::add);

        return users;
    }

    @Override
    public Optional<User> get(ObjectId id) {
        User user = collection.find(Filters.eq("_id", id)).first();
        return Objects.isNull(user) ? Optional.empty() : Optional.of(user);
    }


}
