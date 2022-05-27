package java14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TextBlock {

	public static final String TEXT_BLOCK = """
			Das ist ein Textblock.
			Der geht hier noch weiter.
			""";

	@Test
	@Disabled
	public void textBlock() {
		assertThat(TEXT_BLOCK).isEqualToIgnoringNewLines("Das ist ein Textblock. \nDer geht hier noch weiter.");
	}

}
