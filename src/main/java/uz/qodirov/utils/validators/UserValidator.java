package uz.qodirov.utils.validators;

import org.bson.types.ObjectId;
import uz.qodirov.dto.user.UserCreateDto;
import uz.qodirov.dto.user.UserUpdateDto;
import uz.qodirov.utils.BaseUtils;

public class UserValidator extends BaseValidator<UserCreateDto, UserUpdateDto, ObjectId> {
    public UserValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    protected void validKey(ObjectId key) throws IllegalArgumentException {

    }

    @Override
    protected void validOnCreate(UserCreateDto dto) throws IllegalArgumentException {

    }

    @Override
    protected void validOnUpdate(UserUpdateDto dto) throws IllegalArgumentException {

    }
}
