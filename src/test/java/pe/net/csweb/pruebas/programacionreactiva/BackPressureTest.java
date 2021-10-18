package pe.net.csweb.pruebas.programacionreactiva;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

	@Test
	void testBackPressure() {

		Flux<Integer> numbers = Flux.range(1, 100).log();

		// numbers.subscribe(entero -> System.out.println(entero));

		numbers.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("valor = " + value);

				if (value == 3)
					cancel();

			}

			@Override
			protected void hookOnComplete() {
				System.out.println("Completed!!");
			}

			@Override
			protected void hookOnCancel() {
				super.hookOnCancel();
			}

		});
	}
	
	@Test
	void testBackPressureDrop() {

		Flux<Integer> numbers = Flux.range(1, 100).log();

		// numbers.subscribe(entero -> System.out.println(entero));

		numbers
			.onBackpressureDrop(entero -> {
				System.out.println("dropped values = " + entero);
			})
			.subscribe(new BaseSubscriber<Integer>() {

				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(3);
				}
	
				@Override
				protected void hookOnNext(Integer value) {
					System.out.println("valor = " + value);
	
					if (value == 3) hookOnCancel();
	
				}
	
				@Override
				protected void hookOnComplete() {
					System.out.println("Completed!!");
				}
	
				@Override
				protected void hookOnCancel() {
					super.hookOnCancel();
				}
			});
	}
	
	@Test
	void testBackPressureBuffer() {

		Flux<Integer> numbers = Flux.range(1, 100).log();

		// numbers.subscribe(entero -> System.out.println(entero));

		numbers
				.onBackpressureBuffer(10, 
					i -> System.out.println("Buffered value = " + i))
				.subscribe(new BaseSubscriber<Integer>() {

				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(3);
				}
	
				@Override
				protected void hookOnNext(Integer value) {
					System.out.println("valor = " + value);
	
					if (value == 3) hookOnCancel();
	
				}
	
				@Override
				protected void hookOnComplete() {
					System.out.println("Completed!!");
				}
	
				@Override
				protected void hookOnCancel() {
					super.hookOnCancel();
				}
			});
	}

	@Test
	void testBackPressureError() {

		Flux<Integer> numbers = Flux.range(1, 100).log();

		// numbers.subscribe(entero -> System.out.println(entero));

		numbers
				.onBackpressureError()
				.subscribe(new BaseSubscriber<Integer>() {

				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(3);
				}
	
				@Override
				protected void hookOnNext(Integer value) {
					System.out.println("valor = " + value);
	
					if (value == 3) hookOnCancel();
	
				}
				
				@Override
					protected void hookOnError(Throwable throwable) {
						System.out.println("throwable = " + throwable);
					}
	
				@Override
				protected void hookOnComplete() {
					System.out.println("Completed!!");
				}
	
				@Override
				protected void hookOnCancel() {
					super.hookOnCancel();
				}
			});
	}
}
