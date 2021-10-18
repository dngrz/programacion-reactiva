package pe.net.csweb.pruebas.programacionreactiva.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import pe.net.csweb.pruebas.programacionreactiva.domain.BookBean;
import pe.net.csweb.pruebas.programacionreactiva.domain.ReviewBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
				.log();
	
		
	}
}
