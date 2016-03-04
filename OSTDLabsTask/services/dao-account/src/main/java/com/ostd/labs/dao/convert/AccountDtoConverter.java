package com.ostd.labs.dao.convert;

import com.ostd.labs.dto.account.AccountDTO;
import com.ostd.labs.dao.domain.Account;
import com.ostd.labs.lib.dao.convert.DtoConvertor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author pkiryukhin
 */
public class AccountDtoConverter
        extends DtoConvertor<Account, AccountDTO, Long> {

    public static AccountDtoConverter INSTANCE = new AccountDtoConverter();

    @Override
    public void toDto(final Account entity, final AccountDTO dto) {
        dto.setId(entity.getId());
        dto.setIban(entity.getIban());
        dto.setBic(entity.getBic());
    }

    @Override
    public void fromDto(final Account entity, final AccountDTO dto) {
        entity.setIban(dto.getIban());
        entity.setBic(dto.getBic());    }

    @Override
    protected void toDtoDeep(final Account entity, final AccountDTO dtoShallow,
                             final ToDtoContext context) {

    }

    @Override
    protected Long getPk(final AccountDTO dto) {
        return dto.getId();
    }


    public List<AccountDTO> convertAccountListToDto(final List<Account> accountList) {
        final List<AccountDTO> result = new ArrayList();
        if (accountList == null) {
            return result;
        }
        for (Account account : accountList) {
            final AccountDTO accountDTO = AccountDtoConverter.INSTANCE.toDto(account);
            result.add(accountDTO);
        }
        return result;
    }
}
