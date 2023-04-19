package com.sirdave.lendingplatform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class  LendingPlatformApplicationTests {
	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		int result = underTest.add(20, 30);
		assertThat(result).isEqualTo(50);
	}

	static class Calculator{
		int add (int a, int b){
			return  a + b;
		}
	}

}
