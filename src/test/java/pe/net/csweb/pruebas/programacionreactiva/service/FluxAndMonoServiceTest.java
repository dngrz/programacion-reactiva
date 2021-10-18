package pe.net.csweb.pruebas.programacionreactiva.service;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoServiceTest {
	
	FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
	
	@Test
	void frutasFlux() {
		
		StepVerifier.create(fluxAndMonoService.frutasFlux())
			.expectNext("Mango", "Naranja", "Plátano")
			.verifyComplete();

	}

	@Test
	void frutasMono() {
		
		StepVerifier.create(fluxAndMonoService.frutaMono())
		.expectNext("Mango")
		.verifyComplete();
		
	}
	
	@Test
	void frutasFluxMap() {
		StepVerifier.create(fluxAndMonoService.frutasFluxMap())
		.expectNext("MANGO", "MANZANA", "PLÁTANO")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxFilter() {
		
		StepVerifier.create(fluxAndMonoService.frutasFluxFilter(5))
		.expectNext("Manzana", "Plátano")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxFilterMap() {
		
		StepVerifier.create(fluxAndMonoService.frutasFluxFilterMap(5))
		.expectNext("MANZANA", "PLÁTANO")
		.verifyComplete();
	}
	
	@Test
	void frutasFlatMap() {
		StepVerifier.create(fluxAndMonoService.frutasFlatMap())
			.expectNextCount(19)
			.verifyComplete();
	}
	
	@Test
	void frutasFlatMapAsync() {
		StepVerifier.create(fluxAndMonoService.frutasFlatMap())
			.expectNextCount(19)
			.verifyComplete();
	}
	
	@Test
	void frutaMonoFlatMap() {
		StepVerifier.create(fluxAndMonoService.frutaMonoFlatMap())
		.expectNextCount(1)
		.verifyComplete();
	}
	
	@Test
	void frutasConcatMap() {
		StepVerifier.create(fluxAndMonoService.frutasConcatMap())
		.expectNextCount(19)
		.verifyComplete();		
	}
	
	@Test
	void frutaMonoFlatMapMany() {
		StepVerifier.create(fluxAndMonoService.frutaMonoFlatMapMany())
		.expectNextCount(5)
		.verifyComplete();		
	}
	
	@Test
	void frutasFluxTransform() {
		StepVerifier.create(fluxAndMonoService.frutasFluxTransform(5))
		.expectNext("Manzana", "Plátano")
		.verifyComplete();		
	}
	
	@Test
	void frutasFluxTransformDefaultIfEmpty() {
		StepVerifier.create(fluxAndMonoService.frutasFluxTransformDefaultIfEmpty(10))
		.expectNext("Default")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxTransformSwitchIfEmpty() {
		StepVerifier.create(fluxAndMonoService.frutasFluxTransformSwitchIfEmpty(7))
		.expectNext("Ceresita", "Arándano")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxConcat() {
		StepVerifier.create(fluxAndMonoService.frutasFluxConcat())
		.expectNext("Mango", "Naranja", "Tomate", "Limón")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxConcatWith() {
		StepVerifier.create(fluxAndMonoService.frutasFluxConcatWith())
		.expectNext("Mango", "Naranja", "Tomate", "Limón")
		.verifyComplete();
	}
	
	@Test
	void frutasMonoConcatWith() {
		StepVerifier.create(fluxAndMonoService.frutasMonoConcatWith())
		.expectNext("Mango", "Tomate")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxMerge() {
		StepVerifier.create(fluxAndMonoService.frutasFluxMerge())
		.expectNext("Mango", "Tomate", "Naranja", "Limón")
		.verifyComplete();
		
	}
	
	@Test
	void frutasFluxMergeWith() {
		StepVerifier.create(fluxAndMonoService.frutasFluxMergeWith())
		.expectNext("Mango", "Tomate", "Naranja", "Limón")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxMergeWithSequential() {
		StepVerifier.create(fluxAndMonoService.frutasFluxMergeWithSequential().log())
		.expectNext("Mango", "Naranja", "Tomate", "Limón")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxZip() {
		StepVerifier.create(fluxAndMonoService.frutasFluxZip().log())
		.expectNext("MangoTomate", "NaranjaLimón")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxZipWith() {
		StepVerifier.create(fluxAndMonoService.frutasFluxZipWith().log())
		.expectNext("MangoTomate", "NaranjaLimón")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxZipTuple() {
		StepVerifier.create(fluxAndMonoService.frutasFluxZipTuple().log())
		.expectNext("MangoTomatePapa", "NaranjaLimónFrijol")
		.verifyComplete();
	}
	
	@Test
	void frutasMonoZipWith() {
		StepVerifier.create(fluxAndMonoService.frutasMonoZipWith().log())
		.expectNext("MangoTomate")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxFilterDoOn() {
		StepVerifier.create(fluxAndMonoService.frutasFluxFilterDoOn(5).log())
		.expectNext("Manzana", "Plátano")
		.verifyComplete();
	}
	
	@Test
	void frutasFluxOnErrorReturn() {
		StepVerifier.create(fluxAndMonoService.frutasFluxOnErrorReturn().log())
		.expectNext("Manzana", "Mango", "Naranja")
		.verifyComplete();
	}
}
