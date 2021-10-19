package pe.net.csweb.pruebas.programacionreactiva;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotAndColdStreamsTest {

	
	@Test
	void coldStreamTest() {
		
		Flux<Integer> numeros = Flux.range(1, 10);
		
		numeros.subscribe(entero -> System.out.println("Subscriber 1 = " + entero));
		numeros.subscribe(entero -> System.out.println("Subscriber 2 = " + entero));
		
	}

	@SneakyThrows
	@Test
	void hotStreamTest() {
		
		Flux<Integer> numeros = Flux.range(1, 10).delayElements(Duration.ofMillis(1000));

		ConnectableFlux<Integer> publisher = numeros.publish();
		
		publisher.connect();
		
		publisher.subscribe(entero -> System.out.println("Subscriber 1 = " + entero));
		Thread.sleep(4000);
		publisher.subscribe(entero -> System.out.println("Subscriber 2 = " + entero));
		Thread.sleep(10000);
		
	}

}
