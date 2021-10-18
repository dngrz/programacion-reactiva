package pe.net.csweb.pruebas.programacionreactiva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewBean {

	private String reviewId;
	private String bookId;
	private double rating;
	private String comments;

}
