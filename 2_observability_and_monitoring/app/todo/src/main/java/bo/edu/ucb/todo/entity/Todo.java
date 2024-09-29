package bo.edu.ucb.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Timestamp createDate;

    private Boolean done;

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private Boolean done;

        public Builder() {

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder done(Boolean status) {
            this.done = status;
            return this;
        }

        public Todo build() {
            Todo todo = new Todo();
            todo.setId(this.id);
            todo.setTitle(this.title);
            todo.setDescription(this.description);
            todo.setDone(this.done);
            todo.setCreateDate(new Timestamp(System.currentTimeMillis()));
            return todo;
        }
    }
}
