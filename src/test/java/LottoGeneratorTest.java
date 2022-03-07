import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.LottoGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또 생성 성공")
    void generate_lottos() {

        LottoGenerator lottoGenerator = new LottoGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)), 1);

        lottoGenerator.generate();

        assertThat(lottoGenerator.generate().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("수동 1장, 자동 0장 생성 성공")
    void generate_manual_lottos() {

        LottoGenerator lottoGenerator = new LottoGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)), 0);

        assertThat(lottoGenerator.generate().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수동 0장, 자동 1장 생성 성공")
    void generate_auto_lottos() {

        LottoGenerator lottoGenerator = new LottoGenerator(List.of(List.of()), 1);

        assertThat(lottoGenerator.generate().size()).isEqualTo(1);

    }

}
