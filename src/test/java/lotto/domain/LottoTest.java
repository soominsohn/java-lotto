package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.DuplicateNumberException;
import lotto.exception.InvalidSizeException;

public class LottoTest {
	List<LottoNo> numbers;

	@BeforeEach
	void setUp() {
		numbers = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			numbers.add(new LottoNo(String.valueOf(i)));
		}
	}

	@Test
	void isContain() {
		Lotto lotto = new Lotto(numbers);
		assertThat(lotto.contain(new LottoNo("1"))).isTrue();
		assertThat(lotto.contain(new LottoNo("7"))).isFalse();
	}

	@DisplayName("로또 번호의 개수가 6개인지 검사")
	@Test
	void validateLottoSizeTest() {
		numbers.add(new LottoNo("8"));
		assertThatThrownBy(() -> new Lotto(numbers))
			.isInstanceOf(InvalidSizeException.class);
	}

	@DisplayName("로또 번호에 중복된 숫자가 있는지 검사")
	@Test
	void validateDuplicateNumbersTest() {
		numbers.remove(0);
		numbers.add(new LottoNo("6"));
		assertThatThrownBy(() -> new Lotto(numbers))
			.isInstanceOf(DuplicateNumberException.class);

	}
}