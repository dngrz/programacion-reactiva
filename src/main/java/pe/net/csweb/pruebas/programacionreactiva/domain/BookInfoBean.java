package pe.net.csweb.pruebas.programacionreactiva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoBean {

	private long bookId;
	private String title;
	private String author;
	private String ISBN;

}
