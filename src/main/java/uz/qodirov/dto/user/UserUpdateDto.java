package uz.qodirov.dto.user;

import lombok.*;
import uz.qodirov.dto.GenericDto;
import uz.qodirov.enums.language.Language;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class UserUpdateDto extends GenericDto {
    private String username;
    private String fullName;
    private Language language;

@Builder(builderMethodName = "childbuilder")
    public UserUpdateDto(String id, String username, String fullName, Language language) {
        super(id);
        this.username = username;
        this.fullName = fullName;
        this.language = language;
    }

}
