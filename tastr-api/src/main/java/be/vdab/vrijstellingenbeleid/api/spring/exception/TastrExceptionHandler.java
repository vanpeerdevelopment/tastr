package be.vdab.vrijstellingenbeleid.api.spring.exception;

import be.vdab.vrijstellingenbeleid.infrastructure.exception.TastrEntityBestaatNietException;
import be.vdab.vrijstellingenbeleid.infrastructure.exception.TastrException;
import org.hibernate.StaleObjectStateException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static be.vdab.vrijstellingenbeleid.api.spring.exception.Error.error;
import static be.vdab.vrijstellingenbeleid.infrastructure.exception.TastrException.tastrException;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class TastrExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Error exceptionHandler(ConstraintViolationException exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logError(tastrException, request);
        logValidationMessages(exception);
        return error(
            tastrException,
            exception.getConstraintViolations().stream()
                .map(violation -> String.format("%s -> %s", violation.getLeafBean().getClass().getSimpleName(), violation.getMessage()))
                .collect(Collectors.toList()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Error exceptionHandler(IllegalArgumentException exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logError(tastrException, request);
        return error(tastrException);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Error exceptionHandler(IllegalStateException exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logError(tastrException, request);
        return error(tastrException);
    }

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public Error exceptionHandler(OptimisticLockException exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logDebug(tastrException, request);
        return error(tastrException);
    }

    @ExceptionHandler(StaleObjectStateException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public Error exceptionHandler(StaleObjectStateException exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logDebug(tastrException, request);
        return error(tastrException);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public Error exceptionHandler(OptimisticLockingFailureException exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logDebug(tastrException, request);
        return error(tastrException);
    }

    @ExceptionHandler(TastrEntityBestaatNietException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public Error exceptionHandler(TastrEntityBestaatNietException exception, HttpServletRequest request) {
        logDebug(exception, request);
        return error(exception);
    }

    @Order(LOWEST_PRECEDENCE)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error internalErrorHandler(Exception exception, HttpServletRequest request) {
        TastrException tastrException = tastrException(exception);
        logError(tastrException, request);
        return error(tastrException);
    }

    private void logError(TastrException e, HttpServletRequest request) {
        logger.error("REST call threw exception [" + e.getId() + "] , request=" + fullURL(request), e);
    }

    private void logDebug(TastrException e, HttpServletRequest request) {
        logger.debug("REST call threw exception [" + e.getId() + "] , request=" + fullURL(request), e);
    }

    private void logValidationMessages(ConstraintViolationException exception) {
        exception.getConstraintViolations().stream()
            .map(violation -> String.format("=== VALIDATION ERROR MESSAGE:  %s::%s (%s) -> %s", violation.getRootBeanClass().getName(), violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage()))
            .forEach(logger::error);
    }

    private static String fullURL(HttpServletRequest request) {
        return request.getMethod() + " " + request.getRequestURL() +
            ((request.getQueryString() != null) ? "?" + request.getQueryString() : "");
    }
}
