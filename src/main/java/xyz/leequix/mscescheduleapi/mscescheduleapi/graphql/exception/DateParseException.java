package xyz.leequix.mscescheduleapi.mscescheduleapi.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateParseException extends RuntimeException implements GraphQLError {
    private Map<String, Object> extensions = new HashMap<>();

    public DateParseException(String message) {
        super(message);
    }

    public DateParseException(String message, String invalidDate) {
        super(message);
        extensions.put("invalidDate", invalidDate);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.InvalidSyntax;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
