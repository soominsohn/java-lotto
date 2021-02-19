package lotto.ticket;

import java.util.List;

import static lotto.ticket.strategy.RandomNumbersGenerator.*;

public class TicketValidation {
    public static final String ERROR_MESSAGE_DUPLICATED = "중복되는 숫자가 존재합니다.";
    public static final String ERROR_MESSAGE_INVALID_INPUT = "잘못된 입력입니다.";
    public static final String ERROR_MESSAGE_INVALID_SIZE = "숫자는 6개여야 합니다.";
    public static final String ERROR_MESSAGE_INVALID_RANGE = "숫자는 1부터 45사이여야 합니다.";

    public static Integer validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    public static void validateNumberInRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public static void validateSize(List<Number> value) {
        if (value.size() != NUMBER_COUNT_IN_LOTTO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_SIZE);
        }
    }

    public static void validateDuplicated(List<Number> value) {
        boolean duplicated = value.stream()
                .distinct()
                .count() != value.size();

        if (duplicated) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }

    public static void validateSameNumber(Number number, WinnerTicket winnerTicket) {
        if (winnerTicket.isSameNumber(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }
}