package uz.qodirov.enums;


import lombok.Getter;

import java.util.Locale;

@Getter
public enum Subject {
    MATH("MATH"),
    ENGLISH("ENGLISH");
    private final String code;

    Subject(String code) {
        this.code = code;
    }

    public static Subject getCode(String name) {
        for (Subject value : values()) {
            if (value.getCode().equals(name.toUpperCase(Locale.ROOT)))
                return value;
        }
        return null;
    }

}
