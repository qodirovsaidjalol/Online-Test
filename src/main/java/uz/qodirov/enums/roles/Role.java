package uz.qodirov.enums.roles;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN, TEACHER, STUDENT,ANONYMUS;

    public static Role getByName(String role) {
        for (Role value : values()) {
            if (value.name().equalsIgnoreCase(role)) return value;
        }
        return Role.STUDENT;
    }


}
