package pe.net.csweb.pruebas.programacionreactiva.service;

import java.util.Arrays;
import java.util.List;

import pe.net.csweb.pruebas.programacionreactiva.domain.ReviewBean;
import reactor.core.publisher.Flux;

public class ReviewService {

	public Flux<ReviewBean> getReviews(long bookId) {
		
		List<ReviewBean> reviews = Arrays.asList(
				new ReviewBean(1, bookId, 9.1, "Buen Libro"),
				new ReviewBean(2, bookId, 8.2, "Buena Lectura")
				);
		
		return Flux.fromIterable(reviews);
	}
}
