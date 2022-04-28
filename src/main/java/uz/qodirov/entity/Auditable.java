package uz.qodirov.entity;


import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditable {
    @BsonProperty(value = "_id")
    private ObjectId id = new ObjectId();

    private Date createdAt ;

    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditable auditable = (Auditable) o;
        return deleted == auditable.deleted &&
                id.equals(auditable.id) &&
                createdAt.equals(auditable.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, deleted);
    }
}
