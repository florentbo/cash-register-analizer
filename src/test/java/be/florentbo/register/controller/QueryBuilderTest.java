package be.florentbo.register.controller;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryBuilderTest {

    private QueryBuilder queryBuilder;

    private LocalDate START_DATE = LocalDate.of(2015,8,5);
    private LocalDate END_DATE = LocalDate.of(2015,8,15);

    @Before
    public void setUp() throws Exception {

        QueryBuilder builder = QueryBuilder.newBuilder();
        queryBuilder = builder.where("orderDate").orderedBetween(START_DATE, END_DATE);
    }

    private static final String QUERY = "orderDate >= 05/08/2015 and orderDate <= 15/08/2015";

    @Test
    public void testToParam() throws Exception {
        String expected = queryBuilder.toParam();
        assertThat(expected).isEqualTo(QUERY);
    }

    private static final String ENCODED_PARAM = "orderDate+%26gt%3B%3D+05%2F08%2F2015+and+orderDate+%26lt%3B%3D+15%2F08%2F2015";

    @Test
    public void testToEncodedAndEscapedParam() throws Exception {
        String expected = queryBuilder.toEncodedAndEscapedParam();
        assertThat(expected).isEqualTo(ENCODED_PARAM);

    }

    @Test
    public void testOf() throws Exception {
        QueryBuilder.Dates expected = queryBuilder.of(QUERY);
        assertThat(expected.getStartDate()).isEqualTo(START_DATE);
        assertThat(expected.getEndDate()).isEqualTo(END_DATE);

    }

    @Test
    public void testOfEncodedAndEscapedParam() throws Exception {
        QueryBuilder.Dates expected = queryBuilder.ofEncodedAndEscapedParam(ENCODED_PARAM);
        assertThat(expected.getStartDate()).isEqualTo(START_DATE);
        assertThat(expected.getEndDate()).isEqualTo(END_DATE);
    }
}