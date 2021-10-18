package pe.net.csweb.pruebas.programacionreactiva.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookBean {

	private BookInfoBean bookInfo;

	private List<ReviewBean> reviews = new ArrayList<>();

}
