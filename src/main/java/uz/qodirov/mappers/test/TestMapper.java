package uz.qodirov.mappers.test;

import uz.qodirov.dto.GenericDto;
import uz.qodirov.dto.test.QuestionCreateDto;
import uz.qodirov.dto.test.QuestionUpdateDto;
import uz.qodirov.entity.quiz.Question;
import uz.qodirov.mappers.GenericMapper;

public class TestMapper implements GenericMapper<Question, GenericDto, QuestionCreateDto, QuestionUpdateDto> {

    @Override
    public Question fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public Question fromCreateDto(QuestionCreateDto dto) {
        return Question.childBuilder().
                title(dto.getTitle()).
                subject(dto.getSubject()).
                level(dto.getLevel()).
                variants(dto.getVariants())
                .build();
    }

    @Override
    public Question fromUpdateDto(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public GenericDto toDto(Question dto) {
        return null;
    }
}
