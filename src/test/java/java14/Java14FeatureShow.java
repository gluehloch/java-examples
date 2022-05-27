package java14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Show me some Java 11 features!
 *
 * @author winkler
 */
public class Java14FeatureShow {

	String html = "<html>\n" + "  <body>\n" + "    <p>Hello, Text Blocks</p>\n" + "  </body>\n" + "</html>\n";

	String html2 = """
			<html>
			  <body>
			    <p>Hello, Text Blocks</p>
			  </body>
			</html>""";

	private static String statementBreak(int switchArg) {
		String str = "not set";
		switch (switchArg) {
		case 1:
		case 2:
			str = "one or two";
			break;
		case 3:
			str = "three";
			break;
		case 4:
			str = "ok";
			break;
		}
		return str;
	}

	@Tag("Java 14 feature.")
	@Test
	public void switchCase() {
		assertThat(statementBreak(4)).isEqualTo("ok");
	}

	@Tag("Java 14 feature.")
	@Test
	public void multiLineStrings() {
		assertThat(html2).isEqualToIgnoringNewLines(html);
	}

}
