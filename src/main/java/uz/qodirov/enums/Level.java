package uz.qodirov.enums;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum Level {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");
    private final String code;

    Level(String code) {
    this.code=code;
    }

    public static Level getLevel(String level){
        for (Level value : values()) {
            if (value.getCode().equals(level.toUpperCase(Locale.ROOT)))
                return value;
        }
        return Level.EASY;
    }

}
