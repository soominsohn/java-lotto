import domain.LottoGame;
import domain.LottoGenerators;
import domain.ManualLottoGenerator;
import domain.Money;
import domain.RandomLottoGenerator;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        Money money = new Money(InputView.askMoneyInput(), InputView.askManualAmount());

        LottoGame lottoGame = new LottoGame(
            new LottoGenerators(
                new ManualLottoGenerator(money.getManualAmount(),
                    InputView.askManualLottoNumbers(money.getManualAmount())),
                new RandomLottoGenerator(money.getAutoAmount())));

        OutputView.printLottosInformations(lottoGame.getLottos());

        WinningNumbers winningNumbers = InputView.askWinningNumbers();

        WinningChecker winningChecker = lottoGame.makeResult(winningNumbers);

        OutputView.printWinningStatistic(winningChecker);
        OutputView.printYield(lottoGame.getYield());
    }
}
