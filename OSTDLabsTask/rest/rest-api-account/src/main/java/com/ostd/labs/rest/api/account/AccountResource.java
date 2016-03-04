package com.ostd.labs.rest.api.account;

import com.ostd.labs.dto.account.AccountDTO;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by pkiryukhin
 */
@Path("/account")
public interface AccountResource {

    @GET
    @Path("/getById")
    AccountDTO getById(@QueryParam("id") Long id);

    @GET
    @Path("/getAll")
    List<AccountDTO> getAll();

    @GET
    @Path("/getByBic")
    List<AccountDTO> findByBic(@QueryParam("bic") String bic);

    @GET
    @Path("/getByIban")
    List<AccountDTO> findByIban(@QueryParam("iban") String iban);

    @GET
    @Path("/getByIbanAndBic")
    List<AccountDTO> findByIbanAndBic(@QueryParam("iban") String iban, @QueryParam("bic") String bic);

    @DELETE
    @Path("/deleteById")
    void deleteById(@QueryParam("id") Long id);

    @GET
    @Path("/update")
    void update(@QueryParam("id") Long id, @QueryParam("iban") String iban, @QueryParam("bic") String bic);

    @GET
    @Path("/create")
    void create(@QueryParam("iban") String iban, @QueryParam("bic") String bic);




}