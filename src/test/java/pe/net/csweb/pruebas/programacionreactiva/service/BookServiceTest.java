package pe.net.csweb.pruebas.programacionreactiva.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class BookServiceTest {
	
	private BookInfoService bookInfoService = new BookInfoService();
	
	private ReviewService reviewService = new ReviewService();
	
	private BookService bookService = new BookService(bookInfoService, reviewService);
	
	
	@Test
	void getBooks() {
		
		StepVerifier.create(bookService.getBooks())
		.assertNext(book ->  {
			assertEquals( "Book uno", book.getBookInfo().getTitle());
			assertEquals( 2, book.getReviews().size());
		})
		.assertNext(book ->  {
			assertEquals( "Book dos", book.getBookInfo().getTitle());
			assertEquals( 2, book.getReviews().size());
		})
		.assertNext(book ->  {
			assertEquals( "Book tres", book.getBookInfo().getTitle());
			assertEquals( 2, book.getReviews().size());
		})
		.verifyComplete();
	}
}
