package pe.net.csweb.pruebas.programacionreactiva.service;

import java.util.Arrays;
import java.util.List;

import pe.net.csweb.pruebas.programacionreactiva.domain.BookInfoBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {

	public Flux<BookInfoBean> getBooks() {

		List<BookInfoBean> bookInfos = Arrays.asList(new BookInfoBean(1, "Book uno", "Autor uno", "11111111"),
				new BookInfoBean(1, "Book dos", "Autor dos", "11112222"),
				new BookInfoBean(1, "Book tres", "Autor tres", "11113333"));

		return Flux.fromIterable(bookInfos);

	}

	public Mono<BookInfoBean> getBookById(long bookId) {

		BookInfoBean bookInfo = new BookInfoBean(bookId, "Book uno", "Autor uno", "11111111");

		return Mono.just(bookInfo);
	}

}
