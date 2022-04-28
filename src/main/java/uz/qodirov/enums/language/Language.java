package uz.qodirov.enums.language;

import lombok.Getter;

@Getter
public enum Language {
    RU("RU"), UZ("UZ"), EN("EN");

    private final String code;


    Language(String code) {
        this.code = code;
    }


    public static Language getByCode(String language) {
        for (Language value : values()) {
            if (value.getCode().equalsIgnoreCase(language)) return value;
        }
        return Language.RU;
    }
}
