package uz.qodirov.dto.test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.qodirov.dto.GenericBaseDto;
import uz.qodirov.entity.quiz.Variant;
import uz.qodirov.enums.Level;
import uz.qodirov.enums.Subject;
import uz.qodirov.enums.language.Language;

import java.util.List;


@Getter
@Setter
@Builder
public class QuestionCreateDto implements GenericBaseDto {
    private String title;
    private Level level;
    private Language language;
    private Subject subject;
    private List<Variant> variants;


  /*  @Builder(builderMethodName = "childbuilder")
    public QuestionCreateDto(String title, Level level, Language language, Subject subject, List<Variant> variants) {
        this.title = title;
        this.level = level;
        this.language = language;
        this.subject = subject;
        this.variants = variants;
    }*/
}
