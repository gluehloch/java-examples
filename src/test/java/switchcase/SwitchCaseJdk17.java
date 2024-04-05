package switchcase;

import static org.assertj.core.api.Assertions.assertThat;

class SwitchCaseJdk17 {

    void switchCaseTestJdk17() {
        var text = "";

        assertThat(formatterJava17("test")).isEqualTo("String test");
        assertThat(formatterJava17(111)).isEqualTo("int 111");

        assertThat(testString("Java 16")).isEqualTo("Ok"); // Ok
        assertThat(testString("Java 11")).isEqualTo("LTS"); // LTS
        assertThat(testString("")).isEqualTo("Ok"); // Ok
        assertThat(testString(null)).isEqualTo("Unkown"); // Unknown!        
    }

    static String formatter(Object o) {
        String formatted = "unknown";
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    static String formatterJava17(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }

    static String testString(String s) {
        if (s == null) {
            System.out.println("Unknown!");
            return "Unknon!";
        }

        return switch (s) {
        	case "Java 11", "Java 17" -> "LTS";
        	default -> "Ok";
        };
    }

    static void testStringJava17(String s) {
        switch (s) {
        case null -> System.out.println("Unknown!");
        case "Java 11", "Java 17" -> System.out.println("LTS");
        default -> System.out.println("Ok");
        }
    }

    class Shape {
    }

    class Rectangle extends Shape {
    }

    class Triangle extends Shape {
        int calculateArea() {
            return 1;
        }
    }

    static void testTriangle(Shape s) {
        switch (s) {
            case null:
                break;
            case Triangle t:
                if (t.calculateArea() > 100) {
                    System.out.println("Large triangle");
                    break;
                }else{
                    System.out.println("Triangle");
                }
            default:
                System.out.println("Unknown!");
        }
    }

    // TODO Preview feature. Currently disabled.
    static void testTriangle2(Shape s) {
        switch (s) {
            case null -> System.out.println("NULL-String");
            //case Triangle t && (t.calculateArea() > 100) ->
            //        System.out.println("Large triangle");
            case Triangle t ->
                    System.out.println("Triangle: " + t);
            default ->
                    System.out.println("Unknown!");
        }
    }

}
