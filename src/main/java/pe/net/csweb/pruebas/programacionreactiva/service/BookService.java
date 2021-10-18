package pe.net.csweb.pruebas.programacionreactiva.service;

import java.time.Duration;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import pe.net.csweb.pruebas.programacionreactiva.domain.BookBean;
import pe.net.csweb.pruebas.programacionreactiva.domain.BookInfoBean;
import pe.net.csweb.pruebas.programacionreactiva.domain.ReviewBean;
import pe.net.csweb.pruebas.programacionreactiva.exception.BookException;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

@Slf4j
public class BookService {

	private BookInfoService bookInfoService;
	
	private ReviewService reviewService;
	
	public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
		this.bookInfoService = bookInfoService;
		this.reviewService = reviewService;
	}

	public Flux<BookBean> getBooks(){
		
		return bookInfoService.getBooks()
				.flatMap(bookInfo -> {
					Mono<List<ReviewBean>> reviews 
						= reviewService.getReviews(bookInfo.getBookId()).collectList();
					return reviews
							.map(review -> new BookBean(bookInfo, review));
			
				})
				.onErrorMap(throwable -> {
					
					log.error("Exception :: " + throwable);
					return new BookException("Excepcion ocurrida mientras se recuperan los datos");
				})
				.log();
	}
	
	public Flux<BookBean> getBooksRetry(){
		
		return bookInfoService.getBooks()
				.flatMap(bookInfo -> {
					Mono<List<ReviewBean>> reviews 
						= reviewService.getReviews(bookInfo.getBookId()).collectList();
					return reviews
							.map(review -> new BookBean(bookInfo, review));
			
				})
				.onErrorMap(throwable -> {
					
					log.error("Exception :: " + throwable);
					return new BookException("Excepcion ocurrida mientras se recuperan los datos");
				})
				.retry(3)
				.log();
	}
	
	public Flux<BookBean> getBooksRetryWhen(){
		
		return bookInfoService.getBooks()
				.flatMap(bookInfo -> {
					Mono<List<ReviewBean>> reviews 
						= reviewService.getReviews(bookInfo.getBookId()).collectList();
					return reviews
							.map(review -> new BookBean(bookInfo, review));
			
				})
				.onErrorMap(throwable -> {
					
					log.error("Exception :: " + throwable);
					return new BookException("Excepcion ocurrida mientras se recuperan los datos");
				})
				.retryWhen(getRetryBackoffSpec())
				.log();
	}

	private RetryBackoffSpec getRetryBackoffSpec() {
		return Retry.backoff(3, Duration.ofMillis(1000))
			.filter(throwable -> throwable instanceof BookException)
			.onRetryExhaustedThrow(
					(retryBackoffSpec, retriSignal) -> Exceptions.propagate(retriSignal.failure()));
	}
	
	public Mono<BookBean> getBookById(long bookId) {

		Mono<BookInfoBean> book = bookInfoService.getBookById(bookId);
		Mono<List<ReviewBean>> review = reviewService.getReviews(bookId).collectList();
		
		return book.zipWith(review, (b,r) -> new BookBean(b, r));
	}
	
}
