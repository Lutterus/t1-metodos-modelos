package bee1158;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

class Calculator {
    static String sum(String value1, String value2) {
        int X = Integer.parseInt(value1);
        int Y = Integer.parseInt(value2);
        int sum = 0;
        int hits = 0;

        while (hits < Y) {
            if (X % 2 != 0) {
                sum += X;
                hits++;
            }
            X++;
        }

        return String.valueOf(sum);
    }

    static String calculate(ByteArrayInputStream inputstream) {
        String error = "Parametros invalidos";
        try {
            String answer = "";

            // Get test case size
            int data = inputstream.read();
            int size = Character.getNumericValue((char) data);

            // Ignore first \n
            data = inputstream.read();

            for (int i = 0; i < size; i++) {
                // Get X
                String X = "";
                data = inputstream.read();
                char tempData = (char) data;
                while (Character.isDigit(tempData)) {
                    X += tempData;
                    data = inputstream.read();
                    tempData = (char) data;
                }
                // Get Y
                String Y = "";
                data = inputstream.read();
                tempData = (char) data;
                while (Character.isDigit(tempData)) {
                    Y += tempData;
                    data = inputstream.read();
                    tempData = (char) data;
                }

                if (!answer.contentEquals("")) {
                    answer += "\n";
                }
                answer += sum(X, Y);
            }

            if (data != -1) {
                answer = error;
            }

            return answer;
        } catch (Exception e) {
            return error;
        }
    }
}

public class StepDefinitions {
    private String input;
    private String actual;

    @Given("the input is")
    public void the_input_is(String docString) {
        this.input = docString;
    }

    @When("the program runs")
    public void the_program_runs() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(this.input.getBytes(Charset.forName("UTF-8")));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String s = Calculator.calculate(inputStream);
        int count = 0;

        while (count < s.length()) {
            baos.write(s.charAt(count));
            count++;
        }

        PrintStream outputStream = new PrintStream(baos);
        System.setIn(inputStream);
        System.setOut(outputStream);

        // Main.main(null);

        this.actual = baos.toString();
        inputStream.close();
        outputStream.close();

    }

    @Then("the output should be")
    public void the_output_should_be(String expected) {
        assertEquals(expected, this.actual);
    }
}
