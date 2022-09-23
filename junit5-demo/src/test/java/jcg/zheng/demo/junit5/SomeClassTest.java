package jcg.zheng.demo.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import jcg.zheng.demo.SomeClass;

public class SomeClassTest {

	private SomeClass classUnderTest;
	private TestInfo testInfo;
	private TestReporter testReporter;

	@BeforeEach
	public void setup(TestInfo testInfo, TestReporter terstReporter ) {
		this.testInfo = testInfo;
		this.testReporter = terstReporter;
		classUnderTest = new SomeClass();
	}

	@RepeatedTest(5)
	public void test_doubleANumber() {
		assertEquals(6, classUnderTest.doubleANumber(3), "it should return 6");
	}

	@Disabled
	public void test_not_executed() {
		fail("It should not executed");
	}

	@Test
	@DisplayName("It should return false when input data isn't Save")
	public void test_returnBooleanFoo_false() {
		boolean shouldReturnFalse = classUnderTest.returnABoolean("NA");
		assertFalse(shouldReturnFalse);
	}

	@Test
	@DisplayName("It should return true when input data is Save")
	public void test_returnBooleanFoo_true() {
		boolean shouldReturnTrue = classUnderTest.returnABoolean("Save");
		assertTrue(shouldReturnTrue);
		testReporter.publishEntry(testInfo.getDisplayName());
	}

	@Test
	public void test_voidFoo() throws IllegalAccessException {
	 
		try {
			classUnderTest.voidFoo("OK");
		} catch (Exception e) {
			fail("Should not throw exception");
		}
	}

	@Test
	public void test_voidFoo_exception() throws IllegalAccessException {
		assertThrows(IllegalArgumentException.class, () -> {
			classUnderTest.voidFoo("NA");
		});

	}
	
	@Test
	public void test_timeout() {
		assertTimeout(Duration.ofMillis(1), ()-> classUnderTest.doubleANumber(1000));
	}

}
