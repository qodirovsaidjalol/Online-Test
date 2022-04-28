package uz.qodirov.entity.quiz;

import lombok.*;
import org.bson.types.ObjectId;
import uz.qodirov.entity.Auditable;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Variant extends Auditable {
    private String answer;
    private boolean correct;

    @Builder(builderMethodName = "childBuilder")
    public Variant(ObjectId id, Date createdAt, boolean deleted, String answer, boolean correct) {
        super(id, createdAt, deleted);
        this.answer = answer;
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variant variant = (Variant) o;
        return correct == variant.correct && answer.equals(variant.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, correct);
    }

}
