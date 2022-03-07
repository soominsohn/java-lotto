import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 성공")
    void lotto_generate_success() {
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::from).collect(
            Collectors.toList()));

        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);

    }

    @Test
    @DisplayName("로또 번호에 null 값이 들어오면 예외 발생 ")
    void check_null_fail() {
        assertThatThrownBy(
            () -> new Lotto(null)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호로 null 값이 올 수 없습니다.");
    }

    @Test
    @DisplayName("로또 번호에 빈 값이 올 시 예외 발생")
    void lotto_numbers_empty_fail() {

        assertThatThrownBy(() -> new Lotto(new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 6개만 입력 가능합니다.");

    }

    @Test
    @DisplayName("로또 숫자가 6개가 아닐 시 예외 발생")
    void check_size_fail() {
        assertThatThrownBy(
            () -> new Lotto(Stream.of(1, 2, 3, 4, 5, 6, 7)
                .map(LottoNumber::from)
                .collect(Collectors.toList()))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 6개만 입력 가능합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있을 시 예외 발생")
    void check_unique_fail() {
        assertThatThrownBy(
            () -> new Lotto(Stream.of(1, 2, 3, 4, 5, 5)
                .map(LottoNumber::from)
                .collect(Collectors.toList()))
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }


}
