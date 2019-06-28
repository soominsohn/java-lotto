package lotto.domain.ticket;

import com.google.common.base.Joiner;
import lotto.domain.ticket.exception.InvalidDuplicatedNumberException;
import lotto.domain.ticket.exception.InvalidNumberCountException;

import java.util.*;

public class LottoTicket {
    private static final int MAX_LOTTO_TICKET_NUMBER = 6;

    private final SortedSet<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validLength(numbers);
        this.lottoNumbers = Collections.unmodifiableSortedSet(createSortedSet(numbers));
    }

    private static SortedSet<LottoNumber> createSortedSet(final List<LottoNumber> numbers) {
        return validDuplicatedNumber(new TreeSet<>(numbers), numbers.size());
    }

    private static SortedSet<LottoNumber> validDuplicatedNumber(SortedSet<LottoNumber> sortedNumbers, int inputNumbersSize) {
        if (sortedNumbers.size() == inputNumbersSize) {
            return sortedNumbers;
        }
        throw new InvalidDuplicatedNumberException("중복된 숫자가 있으면 안됩니다.");
    }

    private static void validLength(final List<LottoNumber> numbers) {
        if (numbers.size() != MAX_LOTTO_TICKET_NUMBER) {
            throw new InvalidNumberCountException("로또 숫자는 " + MAX_LOTTO_TICKET_NUMBER + "개 입니다.");
        }
    }

    public static int getMaxLottoTicketNumber() {
        return MAX_LOTTO_TICKET_NUMBER;
    }

    public int countSameNumber(LottoTicket winningLotto) {
        return (int) winningLotto.lottoNumbers.stream()
                .filter(this.lottoNumbers::contains)
                .count();
    }

    public boolean isContainsBonusNumber(final LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public SortedSet<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return Joiner.on(", ").join(lottoNumbers);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }


}