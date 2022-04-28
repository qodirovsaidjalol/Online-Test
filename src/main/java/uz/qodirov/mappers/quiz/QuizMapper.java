package uz.qodirov.mappers.quiz;

import org.bson.types.ObjectId;
import uz.qodirov.dto.GenericDto;
import uz.qodirov.dto.quiz.QuizCreateDto;
import uz.qodirov.entity.quiz.Quiz;
import uz.qodirov.mappers.GenericBaseMapper;

public class QuizMapper implements GenericBaseMapper {



    public Quiz fromDto(GenericDto dto) {
        return null;
    }

    public Quiz fromCreateDto(QuizCreateDto dto) {
        return Quiz.childBuilder()
                .id(new ObjectId())
                .subject(dto.getSubject())
                .level(dto.getLevel())
                .language(dto.getLanguage())
                .count(dto.getCount())
                .duration(dto.getCount()*10)
                .build();
    }


    public GenericDto toDto(Quiz dto) {
        return null;
    }
}
