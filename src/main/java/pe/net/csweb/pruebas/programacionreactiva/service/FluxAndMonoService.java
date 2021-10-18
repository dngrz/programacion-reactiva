package pe.net.csweb.pruebas.programacionreactiva.service;

import java.util.Arrays;

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
