package uz.qodirov.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.qodirov.dto.GenericBaseDto;
import uz.qodirov.entity.quiz.QuestionMark;
import uz.qodirov.enums.Level;
import uz.qodirov.enums.Subject;
import uz.qodirov.enums.language.Language;

import java.util.List;


@Getter
@Setter
@Builder
public class QuizCreateDto implements GenericBaseDto {
    private Subject subject;
    private Level level;
    private Language language;
    private int count;
    private List<QuestionMark> questionsMarks;
}
