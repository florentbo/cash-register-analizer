/*
package be.florentbo.register;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlReaderTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test@Ignore
    public void test_parse_sql() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("dbbackup-full.sql").getFile());
        String expectedValue = "kassaorders";
        String result = Files.toString(file, Charsets.UTF_8);
        assertThat(result).contains(expectedValue);
    }

    */
/*@Test
    public void test_start_db() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("dbbackup-small.sql").getFile());
        String result = Files.toString(file, Charsets.UTF_8);

        DataSource ds = JdbcConnectionPool.createRegisterOrders("jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1", "user", "password");
        Connection conn = ds.getConnection();
        conn.createStatement().executeUpdate(result);
        conn.close();

    }*//*

}*/
