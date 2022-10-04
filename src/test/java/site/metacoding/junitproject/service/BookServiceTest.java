package site.metacoding.junitproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import site.metacoding.junitproject.domain.Book;
import site.metacoding.junitproject.domain.BookRepository;
import site.metacoding.junitproject.web.dto.BookRespDto;
import site.metacoding.junitproject.web.dto.BookSaveReqDto;
import site.metacoding.util.MailSender;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;
    
    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit강의");
        dto.setAuthor("메타코딩");

        // stub (가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        // when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        // assertEquals(dto.getTitle(), bookRespDto.getTitle());
        // assertEquals(dto.getAuthor(), bookRespDto.getAuthor());
        assertThat(dto.getTitle()).isEqualTo(bookRespDto.getTitle());
        assertThat(dto.getAuthor()).isEqualTo(bookRespDto.getAuthor());
    }

    @Test
    public void 책목록보기_테스트() {
        // given

        // stub
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "메타강의"));
        books.add(new Book(2L, "spring강의", "겟인데어"));

        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<BookRespDto> bookRespDtoList = bookService.책목록보기();

        // then
        assertThat(bookRespDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookRespDtoList.get(0).getAuthor()).isEqualTo("메타강의");
        assertThat(bookRespDtoList.get(1).getTitle()).isEqualTo("spring강의");
        assertThat(bookRespDtoList.get(1).getAuthor()).isEqualTo("겟인데어");
    }
}
