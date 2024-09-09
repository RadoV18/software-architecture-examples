package bo.edu.ucb.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResDto {
    private Long id;
    private String title;
    private String description;
    private Timestamp createDate;
    private Boolean done;
}
