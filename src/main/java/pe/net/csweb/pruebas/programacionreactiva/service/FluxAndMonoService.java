package pe.net.csweb.pruebas.programacionreactiva.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService {
	
	public Flux<String> frutasFlux(){
		return Flux.fromIterable(Arrays.asList("Mango", "Naranja", "Plátano")).log();
	}
	
	public Flux<String> frutasFluxMap() {
		return Flux
				.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.map(String::toUpperCase);

	}
	
	public Flux<String> frutasFluxFilter(int caracteres) {
		return Flux
				.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.filter(s -> s.length() > caracteres);
	}

	public Flux<String> frutasFluxFilterMap(int caracteres) {
		return Flux
				.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.filter(s -> s.length() > caracteres)
				.map(String::toUpperCase);
	}
	
	public Flux<String> frutasFlatMap(){
		return Flux
				.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.flatMap(s -> Flux.just(s.split("")))
				.log();
		
	}

	public Flux<String> frutasFlatMapAsync(){
		return Flux
				.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.flatMap(s -> Flux.just(s.split(""))
						.delayElements(
								Duration.ofMillis(new Random().nextInt(1000)
						)))
				.log();
	}
	
	public Flux<String> frutasConcatMap(){
		return Flux.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.concatMap(s -> Flux.just(s.split(""))
						.delayElements(
								Duration.ofMillis(new Random().nextInt(1000)
						)))
				.log();
	}

	public Mono<List<String>> frutaMonoFlatMap(){
		return Mono.just("Mango")
				.flatMap(s -> Mono.just(Arrays.asList(s.split(""))))
				.log();
	}

	public Mono<String> frutaMono(){
		return Mono.just("Mango").log();
	}
	
	public static void main(String[] args) {
		
		FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
		
		fluxAndMonoService.frutasFlux()
			.subscribe(s -> {
				System.out.println("s = " + s);
			});
		
		fluxAndMonoService.frutaMono()
			.subscribe(s -> {
				System.out.println("Mono s = " + s);
			});
		
		fluxAndMonoService.frutasFluxMap()
			.subscribe(s -> {
				System.out.println("s = " + s);
			});
		
	}
	
	

}
