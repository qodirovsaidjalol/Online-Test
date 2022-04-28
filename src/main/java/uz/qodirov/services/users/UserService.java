package uz.qodirov.services.users;

import org.bson.types.ObjectId;
import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.dto.user.UserCreateDto;
import uz.qodirov.dto.user.UserUpdateDto;
import uz.qodirov.entity.user.User;
import uz.qodirov.mappers.userMapper.UserMapper;
import uz.qodirov.repository.UserRepository.UserRepository;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;
import uz.qodirov.services.AbstractService;
import uz.qodirov.services.GenericCrudService;
import uz.qodirov.services.SessionService;
import uz.qodirov.utils.validators.UserValidator;

import java.util.List;
import java.util.Objects;

public class UserService extends AbstractService<UserRepository, UserMapper> implements GenericCrudService<User, UserCreateDto, UserUpdateDto, ObjectId> {
    UserRepository USERREPOSITORY = ApplicationContextHolder.getBean(UserRepository.class);

    private final UserValidator validator;

    public UserService(UserRepository repository, UserMapper mapper, UserValidator userValidator) {
        super(repository, mapper);
        this.validator = userValidator;
    }

    public ResponseEntity<Data<String>> login(String username, String password) {
        User user = USERREPOSITORY.login(username, password);
        if (Objects.isNull(user)){
            return new ResponseEntity<>(new Data<>("User not found"));
        }
        if (!user.isDeleted() && USERREPOSITORY.userActive(user))
            SessionService.setSession(user);
        return new ResponseEntity<>(new Data<>("Successfully login"));
    }


    @Override
    public ResponseEntity<Data<ObjectId>> create(UserCreateDto dto) {

        try {
//            validator.validOnCreate(dto);
            User user = mapper.fromCreateDto(dto);
            return new ResponseEntity<>(new Data<>(repository.creat(user)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<Void>> update(UserUpdateDto dto) {
        try {
            //            validator.validOnUpdate(dto);
            User user = mapper.fromUpdateDto(dto);
            repository.update(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<User>> get(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<User>>> getList() {
        return null;
    }
}
