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
}
