package uz.qodirov.entity.user;

import lombok.*;
import org.bson.types.ObjectId;
import uz.qodirov.entity.Auditable;
import uz.qodirov.entity.quiz.Quiz;
import uz.qodirov.enums.language.Language;
import uz.qodirov.enums.roles.Role;
import uz.qodirov.enums.status.Status;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {
    private String username;
    private String fullName;
    private String password;
    private Role role;
    private Status status;
    private Language language;
    private List<Quiz> quizzes;
    private Quiz currentQuiz;


    @Builder(builderMethodName = "childBuilder")
    public User(ObjectId id, Date createdAt, boolean deleted, String username, String fullName, String password, Role role, Status status, Language language, List<Quiz> quizzes, Quiz currentQuiz) {
        super(id, createdAt, deleted);
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.status = status;
        this.language = language;
        this.quizzes = quizzes;
        this.currentQuiz = currentQuiz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User users = (User) o;
        return username.equals(users.username) && fullName.equals(users.fullName) && password.equals(users.password) && role == users.role && status == users.status && language == users.language && quizzes.equals(users.quizzes) && currentQuiz.equals(users.currentQuiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, fullName, password, role, status, language, quizzes, currentQuiz);
    }

}
