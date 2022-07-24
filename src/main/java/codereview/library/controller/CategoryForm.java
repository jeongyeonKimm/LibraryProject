package codereview.library.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryForm {

    private Long id;

    @NotEmpty(message = "도서분류명은 필수 입니다.")
    private String name;
}
