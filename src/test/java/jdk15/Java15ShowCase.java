package jdk15;

import org.junit.jupiter.api.Test;

public class Java15ShowCase {

	/**
	 * Siehe auch {@linkplain https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Formatter.html}.
	 */
	@Test
	public void printf() {
		System.out.printf("Das ist mein %d. Test", 1);
		System.out.println(String.format("Das ist mein %d. Test.", 1));
	}

//	public String getString(String test) {
//        return switch (test) {
//        case "test3":
//            "test3";
//        case "test1":
//            "test1";
//        case "test2":
//            "test2";
//        default: "default";
//        }
//    }

}
