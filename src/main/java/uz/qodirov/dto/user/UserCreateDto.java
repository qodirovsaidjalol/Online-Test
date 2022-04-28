package uz.qodirov.dto.user;

import lombok.*;
import uz.qodirov.dto.GenericBaseDto;


@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto implements GenericBaseDto {
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String language;
}
