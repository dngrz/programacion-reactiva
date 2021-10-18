package pe.net.csweb.pruebas.programacionreactiva.service;

import org.junit.jupiter.api.Test;

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
	
}
