import org.example.CompressionDecorator;
import org.example.DataSource;
import org.example.EncryptionDecorator;
import org.example.FileDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DataSourceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {

        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {

        System.setOut(originalOut);
    }

    @Test
    public void testFileDataSource() {
        DataSource source = new FileDataSource("test.txt");
        source.writeData("hello");
        assertEquals("hello", source.readData());
    }

    @Test
    public void testEncryptionDecorator() {
        DataSource source = new EncryptionDecorator(new FileDataSource("test.txt"));
        source.writeData("secret");
        assertEquals("secret", source.readData());
    }

    @Test
    public void testCompressionDecorator() {
        DataSource source = new CompressionDecorator(new FileDataSource("test.txt"));
        source.writeData("large data");
        assertEquals("large data", source.readData());
    }

    @Test
    public void testMultipleDecorators() {
        DataSource source = new CompressionDecorator(
                new EncryptionDecorator(new FileDataSource("test.txt"))
        );
        source.writeData("final data");
        assertEquals("final data", source.readData());
    }
}