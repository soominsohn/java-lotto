package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {

    private final int MINIMUM_LOTTO_NUMBER = 1;
    private final int MAXIMUM_LOTTO_NUMBER = 45;
    private final int LOTTO_SIZE = 6;

    private final String LOTTO_NUMBER_NULL_ERROR = "[ERROR] 로또 번호로 null 값이 올 수 없습니다.";
    private final String LOTTO_NUMBER_RANGE_ERROR = "[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.";
    private final String LOTTO_SIZE_ERROR = "[ERROR] 로또 번호는 6개만 입력 가능합니다.";
    private final String LOTTO_NUMBER_NOT_UNIQUE_ERROR = "[ERROR] 로또 번호는 중복될 수 없습니다.";


    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        checkValidate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }


    private void checkValidate(List<Integer> numbers) {
        checkNull(numbers);
        checkRange(numbers);
        checkSize(numbers);
        checkUnique(numbers);
    }

    private void checkNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NULL_ERROR);
        }
    }

    private void checkRange(List<Integer> numbers) {
        numbers.stream()
            .filter(number -> number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER)
            .findAny()
            .ifPresent(m -> {
                throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR);
            });
    }

    private void checkSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR);
        }
    }

    private void checkUnique(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NOT_UNIQUE_ERROR);
        }
    }
}
