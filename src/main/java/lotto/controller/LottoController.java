package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.PurchaseInfo;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.service.LottoMachineService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoController {
    private static final int MINIMUM_MANUAL_LOTTO_SIZE = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachineService lottoMachineService;

    public LottoController(InputView inputView, OutputView outputView, Money ticketPrice) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachineService = new LottoMachineService(ticketPrice);
    }

    public void start() {
        PurchaseInfo purchaseInfo = inputMoneyAndNumberOfManualLotto();
        LottoTickets lottoTickets = createAllLottoTickets(purchaseInfo, inputManualLottoNumbers(purchaseInfo));
        printTicketsCondition(purchaseInfo, lottoTickets);

        LottoResult lottoResult = lottoTickets.calculateLottoResult(
                createWinningLotto(inputView.inputWinningNumbers(), inputView.takeBonusNumber()),
                lottoMachineService.getLottoPrice()
        );

        outputView.printLottoResult(lottoResult);
    }

    private void printTicketsCondition(PurchaseInfo purchaseInfo, LottoTickets lottoTickets) {
        outputView.printTicketsSize(purchaseInfo.numberOfManualLotto(), purchaseInfo.numberOfAutoLotto());
        outputView.printAllLottoTickets(lottoTickets);
    }

    private PurchaseInfo inputMoneyAndNumberOfManualLotto() {
        return new PurchaseInfo(
                new Money(inputView.takeLottoMoney())
                , new Money(lottoMachineService.getLottoPrice())
                , inputView.inputSizeOfManualLotto()
        );
    }

    public LottoTickets createAllLottoTickets(PurchaseInfo purchaseInfo, List<LottoNumbers> lottoNumbersBundle) {
        return lottoMachineService.createAllLottoTickets(purchaseInfo, lottoNumbersBundle);
    }

    private List<LottoNumbers> inputManualLottoNumbers(PurchaseInfo purchaseInfo) {
        if (purchaseInfo.numberOfManualLotto() < MINIMUM_MANUAL_LOTTO_SIZE) {
            return Collections.emptyList();
        }

        inputView.printRequestMessageForInputManualLottoNumbers();

        return Stream.generate(() -> new LottoNumbers(inputView.inputManualLottoNumbers()))
                .limit(purchaseInfo.numberOfManualLotto())
                .collect(toList());
    }

    public WinningLottoTicket createWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        return new WinningLottoTicket(winningNumbers, bonusNumber);
    }
}
