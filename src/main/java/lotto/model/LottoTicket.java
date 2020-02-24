package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int FIRST_INDEX = 0;
    protected static final int LOTTO_NUMBER_LENGTH = 6;
    private List<Integer> lottoTicket = new ArrayList<>();

    public LottoTicket() {
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            lottoTicket.add(i);
        }
        Collections.shuffle(lottoTicket);
        lottoTicket = lottoTicket.subList(FIRST_INDEX, LOTTO_NUMBER_LENGTH);
        Collections.sort(lottoTicket);
    }

    // 수동
    public LottoTicket(List<Integer> lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public List<Integer> getLottoTicket() {
        return lottoTicket;
    }

    public int matchNumber(WinNumber winNumber) {
        return (int) lottoTicket.stream()
                .filter(winNumber::contains)
                .count();
    }

    public boolean matchesWithBonusBall(BonusBall bonusBall) {
        return lottoTicket.contains(bonusBall.getBonusNumber());
    }
}