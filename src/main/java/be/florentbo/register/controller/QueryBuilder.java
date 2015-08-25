package be.florentbo.register.controller;

import be.florentbo.register.Format;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class QueryBuilder {

    private LocalDate startDate;
    private LocalDate endDate;
    private String paramName;

    private static final String GREATER_THAN_OR_EQUAL_TO = ">=";
    private static final String LESS_THAN_OR_EQUAL_TO = "<=";
    private static final String AND = "and";
    private static final String SPACE = " ";

    static QueryBuilder newBuilder() {
        return new QueryBuilder();
    }


    private QueryBuilder(){
    }

    private QueryBuilder(LocalDate startDate, LocalDate endDate, String paramName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paramName = paramName;
    }

    QueryBuilderClause where(String paramName) {
        this.paramName = paramName;
        return new QueryBuilderClause();
    }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Format.DATE_FORMAT_PATTERN);

    String toParam() {
        return paramName + SPACE + GREATER_THAN_OR_EQUAL_TO + SPACE + dateTimeFormatter.format(startDate) + SPACE + AND +
                SPACE + paramName + SPACE + LESS_THAN_OR_EQUAL_TO + SPACE + dateTimeFormatter.format(endDate);
    }

    String toEncodedAndEscapedParam() throws UnsupportedEncodingException {
        return URLEncoder.encode(StringEscapeUtils.escapeHtml4(toParam()), "UTF-8");
    }

    QueryBuilder.Dates of(String value) {
        int greaterThanIndex = value.indexOf(GREATER_THAN_OR_EQUAL_TO);
        int and = value.indexOf(AND);
        int lesserThanIndex = value.indexOf(LESS_THAN_OR_EQUAL_TO);
        String startDate = value.substring(greaterThanIndex + 3, and - 1);
        String endDate = value.substring(lesserThanIndex + 3, value.length());
        return new QueryBuilder.Dates(LocalDate.parse(startDate,dateTimeFormatter), LocalDate.parse(endDate,dateTimeFormatter));
    }

    Dates ofEncodedAndEscapedParam(String value) throws UnsupportedEncodingException {
        String query = StringEscapeUtils.unescapeHtml4(URLDecoder.decode(value, "UTF-8"));
        return of(query);
    }

    class QueryBuilderClause {
        QueryBuilder orderedBetween(LocalDate startDate, LocalDate endDate) {
            return new QueryBuilder(startDate,endDate,QueryBuilder.this.paramName);
        }
    }

    class Dates {
        private LocalDate startDate;
        private LocalDate endDate;

        public Dates(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }
}
