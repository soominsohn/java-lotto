import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rewards;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningCheckerTest {

    @Test
    @DisplayName("only 1등 당첨일 때 수익률은 2000000이어야 한다.")
    void checkWinning() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        Lotto winningNumber = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = LottoNumber.from(11);

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getYield()).isEqualTo(2000000);

    }

    @Test
    @DisplayName("only 2등 당첨일 때 수익률은 30000이어야 한다.")
    void checkWinning2() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 7)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        Lotto winningNumber = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = LottoNumber.from(7);

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getYield()).isEqualTo(30000);

    }

    @Test
    @DisplayName("only 3등 당첨일 때 수익률은 1500이어야 한다.")
    void checkWinning3() {

        //given
        Lotto lotto = new Lotto(Stream.of(3, 5, 6, 7, 8, 9)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        Lotto winningNumber = new Lotto(Stream.of(3, 5, 6, 7, 8, 10)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = LottoNumber.from(12);

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getYield()).isEqualTo(1500);

    }

    @Test
    @DisplayName("당첨 안됐을때 수익은 0원이어야 한다.")
    void checkWinning4() {

        //given
        Lotto lotto = new Lotto(Stream.of(11, 12, 13, 14, 8, 9)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        Lotto winningNumber = new Lotto(Stream.of(3, 5, 6, 7, 8, 10)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = LottoNumber.from(15);

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getYield()).isEqualTo(0);
    }

    @Test
    @DisplayName("winningCount 6개 일시 1등 당첨")
    void winning_checker_check_FIRST_REWARD() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(11));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.FIRST_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 5개 & bonusCount 1개 일시 2등 당첨")
    void winning_checker_check_SECOND_REWARD() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 4, 5, 7)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(6));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.SECOND_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 5개 & bonusCount 0개 일시 3등 당첨")
    void winning_checker_check_THIRD_REWARD() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 4, 5, 7)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(11));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.THIRD_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 4개 & bonusCount 0개 일시 4등 당첨")
    void winning_checker_check_FOURTH_REWARD_1() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 4, 7, 8)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(11));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.FOURTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 4개 & bonusCount 1개 일시 4등 당첨")
    void winning_checker_check_FOURTH_REWARD_2() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 4, 8, 9)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(5));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.FOURTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 3개 & bonusCount 0개 일시 5등 당첨")
    void winning_checker_check_FIFTH_REWARD_1() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 7, 8, 9)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(10));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.FIFTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 3개 & bonusCount 1개 일시 5등 당첨")
    void winning_checker_check_FIFTH_REWARD_2() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 3, 7, 8, 9)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(4));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.FIFTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 3개 미만 일시 당첨 아님")
    void winning_checker_check_NO_REWARD() {

        //given
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::from)
            .collect(Collectors.toList()));

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(
            Stream.of(1, 2, 10, 11, 12, 13)
                .map(LottoNumber::from)
                .collect(Collectors.toList())), LottoNumber.from(4));

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        //when
        winningChecker.check();

        //then
        assertThat(winningChecker.getRewardsCount(Rewards.NO_REWARD)).isEqualTo(1);

    }


}
