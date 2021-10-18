package pe.net.csweb.pruebas.programacionreactiva.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.net.csweb.pruebas.programacionreactiva.domain.BookBean;
import pe.net.csweb.pruebas.programacionreactiva.exception.BookException;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class BookServiceMockTest {
	
	@Mock
	private BookInfoService bookInfoService;
	
	@Mock
	private ReviewService reviewService;
	
	@InjectMocks
	private BookService bookService;
	
	@Test
	void getBooksMock() {
		
		Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong())).thenCallRealMethod();
		
		Flux<BookBean> books = bookService.getBooks();
		
		StepVerifier.create(books)
		.expectNextCount(3)
		.verifyComplete();
		
	}

	@Test
	void getBooksMockOnError() {
		
		Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong()))
			.thenThrow(new IllegalStateException("Excepcion usando test"));
		
		Flux<BookBean> books = bookService.getBooks();
		
		StepVerifier.create(books)
		.expectError(BookException.class)
		.verify();
		
	}
}
