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
	
	public Flux<String> frutasFluxZip() {
		return Flux.zip(
				Flux.just("Mango", "Naranja"), Flux.just("Tomate", "Limón"),
				(first, second) -> first + second);
	}

	public Flux<String> frutasFluxZipWith() {
		return Flux.just("Mango", "Naranja")
					.zipWith( Flux.just("Tomate", "Limón"),
							(first, second) -> first + second);
	}

	public Flux<String> frutasFluxZipTuple() {
		return Flux.zip(
				Flux.just("Mango", "Naranja"), 
				Flux.just("Tomate", "Limón"),
				Flux.just("Papa", "Frijol"))
				.map(objects -> objects.getT1() + objects.getT2() + objects.getT3());
	}
	
	public Mono<String> frutasMonoZipWith() {
		return Mono.just("Mango")
					.zipWith( Mono.just("Tomate"),
							(first, second) -> first + second);
	}

	public Mono<String> frutaMono(){
		return Mono.just("Mango").log();
	}
	
	public Flux<String> frutasFluxFilterDoOn(int caracteres) {
		return Flux
				.fromIterable(Arrays.asList("Mango", "Manzana", "Plátano"))
				.filter(s -> s.length() > caracteres)
				.doOnNext(s -> {
					System.out.println("s = " + s);
				})
				.doOnSubscribe(subscription -> {
					System.out.println("subscription = " + subscription.toString());
				})
				.doOnComplete(() -> System.out.println("Completado!"))
				;
	}
	
	public Flux<String> frutasFluxOnErrorReturn(){
		return Flux.just("Manzana", "Mango")
				.concatWith(
						Flux.error(new RuntimeException("Ocurrió una Excepción")
				))
				.onErrorReturn("Naranja")
				;
	}
	
	public Flux<String> frutasFluxOnErrorContinue(){
		return Flux.just("Manzana", "Mango", "Naranja")
				.map(s -> {
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Excepcion Mango");
					return s.toUpperCase();
				})
				.onErrorContinue((e,f) -> {
					System.out.println("e = " + e);
					System.out.println("f = " + f);
				});
	}
	
	public Flux<String> frutasFluxOnErrorMap(){
		return Flux.just("Manzana", "Mango", "Naranja")
				.checkpoint("checkpoint 1")
				.map(s -> {
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Excepcion Mango");
					return s.toUpperCase();
				})
				.checkpoint("checkpoint 2")
				.onErrorMap(throwable -> {
					System.out.println("throwable = " + throwable);
					return new IllegalStateException("Excepcion desde onErrorMap");
				});
	}
	
	public Flux<String> frutasFluxOnError(){
		return Flux.just("Manzana", "Mango", "Naranja")
				.map(s -> {
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Excepcion Mango");
					return s.toUpperCase();
				})
				.doOnError(throwable -> {
					System.out.println("throwable = " + throwable);
				});
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
