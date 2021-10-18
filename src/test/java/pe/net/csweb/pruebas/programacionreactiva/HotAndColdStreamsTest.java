package pe.net.csweb.pruebas.programacionreactiva;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class HotAndColdStreamsTest {

	
	@Test
	void coldStreamTest() {
		
		Flux<Integer> numeros = Flux.range(1, 10);
		
		numeros.subscribe(entero -> System.out.println("Subscriber 1 = " + entero));
		numeros.subscribe(entero -> System.out.println("Subscriber 2 = " + entero));
		
	}
	
}
