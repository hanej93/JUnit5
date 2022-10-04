package site.metacoding.junitproject.web.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.junitproject.domain.Book;

@Setter // Controller에서 Setter가 호출되면서 Dto에 같이 채워짐.
@Getter
public class BookSaveReqDto {

    @Size(min = 1, max = 50)
    @NotBlank
    private String title;

    @Size(min = 2, max = 20)
    @NotBlank
    private String author;

    public Book toEntity() {
        return Book.builder()
            .title(title)
            .author(author)
            .build();
    }
}
