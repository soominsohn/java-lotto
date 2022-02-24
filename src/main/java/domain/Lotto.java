package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private List<Integer> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateNumber();
    }

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public List<Integer> generateNumber() {

        List<Integer> numbers = IntStream.range(1, 46)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lottoNumbers = numbers.subList(0, 6);
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }

    public Rewards checkWinning(List<Integer> winningNumbers, Integer bonusNumber) {

        int winningCount = (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
        int bonusCount = (int) lottoNumbers.stream()
                .filter(bonusNumber::equals)
                .count();

        if (winningCount < 3) {
            winningCount = 0;
        }

        if (winningCount != 5) {
            bonusCount = 0;
        }
        return Rewards.findReward(winningCount, bonusCount);
    }
}