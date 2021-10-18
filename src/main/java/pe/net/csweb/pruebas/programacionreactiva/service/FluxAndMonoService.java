package pe.net.csweb.pruebas.programacionreactiva.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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
	
	public Flux<String> frutaMonoFlatMapMany(){
		return Mono.just("Mango")
				.flatMapMany(s -> Flux.just(s.split("")))
				.log();
	}

	public Flux<String> frutasFluxTransform(int caracteres) {
		
		Function<Flux<String>, Flux<String>> filterData
			= data -> data.filter(s -> s.length() > caracteres);
		
		return Flux.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.transform(filterData)
				.log();
	}

	public Flux<String> frutasFluxTransformDefaultIfEmpty(int caracteres) {
		
		Function<Flux<String>, Flux<String>> filterData
			= data -> data.filter(s -> s.length() > caracteres);
		
		return Flux.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.transform(filterData)
				.defaultIfEmpty("Default")
				.log();
	}
	
	public Flux<String> frutasFluxTransformSwitchIfEmpty(int caracteres) {
		
		Function<Flux<String>, Flux<String>> filterData
			= data -> data.filter(s -> s.length() > caracteres);
		
		return Flux.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.transform(filterData)
				.switchIfEmpty(Flux.just("Ceresita", "Arándano"))
					.transform(filterData)
				.log();
	}
	
	public Flux<String> frutasFluxConcat() {
		return Flux.concat(Flux.just("Mango", "Naranja"), Flux.just("Tomate", "Limón"))
				.log();
	}
	
	public Flux<String> frutasFluxConcatWith() {
		return Flux.just("Mango", "Naranja")
				.concatWith(Flux.just("Tomate", "Limón")) 
				.log();
	}

	public Flux<String> frutasMonoConcatWith() {
		return Mono.just("Mango")
				.concatWith(Mono.just("Tomate")) 
				.log();
	}
	
	public Flux<String> frutasFluxMerge() {
		return Flux.merge(
					Flux.just("Mango", "Naranja").delayElements(Duration.ofMillis(50)), 
					Flux.just("Tomate", "Limón").delayElements(Duration.ofMillis(75)))
				.log();
	}
	
	public Flux<String> frutasFluxMergeWith() {
		return Flux.just("Mango", "Naranja").delayElements(Duration.ofMillis(50))
				.mergeWith(
						Flux.just("Tomate", "Limón").delayElements(Duration.ofMillis(75))
						)
				.log();
	}

	public Flux<String> frutasFluxMergeWithSequential() {
		return Flux.mergeSequential(
				Flux.just("Mango", "Naranja").delayElements(Duration.ofMillis(50)),
				Flux.just("Tomate", "Limón").delayElements(Duration.ofMillis(75))
				);
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
