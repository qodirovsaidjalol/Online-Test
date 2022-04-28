package uz.qodirov.mappers.userMapper;

import org.bson.types.ObjectId;
import uz.qodirov.dto.GenericDto;
import uz.qodirov.dto.user.UserCreateDto;
import uz.qodirov.dto.user.UserUpdateDto;
import uz.qodirov.entity.user.User;
import uz.qodirov.enums.language.Language;
import uz.qodirov.enums.roles.Role;
import uz.qodirov.enums.status.Status;
import uz.qodirov.mappers.GenericMapper;

public class UserMapper implements GenericMapper<User, GenericDto, UserCreateDto, UserUpdateDto> {
    @Override
    public User fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public User fromCreateDto(UserCreateDto dto) {
        return User.childBuilder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(Role.getByName(dto.getRole()))
                .status(Status.NO_ACTIVE)
                .language(Language.getByCode(dto.getLanguage()))
                .build();
    }

    @Override
    public User fromUpdateDto(UserUpdateDto dto) {
        return User.childBuilder()
                .id(new ObjectId(String.valueOf(dto.getId())))
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .language(dto.getLanguage())
                .build();
    }

    @Override
    public GenericDto toDto(User dto) {
        return null;
    }
}
