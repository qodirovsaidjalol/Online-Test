package uz.qodirov.ui;

import org.bson.types.ObjectId;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;
import uz.qodirov.dto.test.QuestionCreateDto;
import uz.qodirov.entity.quiz.Variant;
import uz.qodirov.enums.Level;
import uz.qodirov.enums.Subject;
import uz.qodirov.enums.language.Language;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;
import uz.qodirov.services.test.TestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestUI extends BaseUI<TestService> {

    public TestUI(TestService service) {
        super(service);
    }


    @Override
    public void create() {
        QuestionCreateDto dto = QuestionCreateDto.builder()
                .title(Input.getStr("Title : "))
                .subject(Subject.getCode(Input.getStr("Subject : ")))
                .language(Language.getByCode(Input.getStr("Language : ")))
                .level(Level.getLevel(Input.getStr("Level : ")))
                .build();
        List<Variant> VARIANTS = createVariant(new ArrayList<>());
        dto.setVariants(VARIANTS);
        ResponseEntity<Data<ObjectId>> response = service.create(dto);
        if (Objects.isNull(response)) {
            Print.println(Color.RED,"ERROR");
        }
        else
            Print.println(Color.BLUE,"Successfully Created");
    }

    private List<Variant> createVariant(ArrayList<Variant> list) {
        while (true) {
            list.add(Variant.childBuilder().answer(Input.getStr("ANSWER : ")).correct(Input.getStr("Is true answer : (y/n) ").startsWith("y")).build());
            String choose = Input.getStr("Add variant ? (q...) ");
            if (choose.startsWith("q")) {
                return list;
            }
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void get() {

    }

    @Override
    public void list() {

    }
}
