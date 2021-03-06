// Copyright
package com.tesobe.obp.transport.spi;

import com.tesobe.obp.transport.Account;
import com.tesobe.obp.transport.Bank;
import com.tesobe.obp.transport.Connector;
import com.tesobe.obp.transport.Message;
import com.tesobe.obp.transport.Sender;
import com.tesobe.obp.transport.Transaction;
import com.tesobe.obp.transport.Transport;
import com.tesobe.obp.transport.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * Compatible to mid 2016 OBP-API.
 *
 * @since 2016.9
 */
@SuppressWarnings("WeakerAccess") public class DefaultConnector
  implements Connector
{
  public DefaultConnector(Transport.Version v, Encoder e, Decoder d, Sender s)
  {
    decoder = d;
    encoder = e;
    sender = s;
    version = v;
  }

  @Override public Optional<Account> getAccount(String bankId, String accountId,
    String userId) throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getAccount(userId, bankId, accountId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.account(response);
  }

  @Override public Optional<Account> getAccount(String bankId, String accountId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getAccount(bankId, accountId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.account(response);
  }


  @Override public Iterable<Account> getAccounts(String bankId, String userId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getAccounts(userId, bankId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.accounts(response);
  }

  @Override public Iterable<Bank> getBanks(String userId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getBanks(userId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.banks(response);
  }

  @Override
  public Optional<Transaction> getTransaction(String bankId, String accountId,
    String transactionId, String userId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder
      .getTransaction(bankId, accountId, transactionId, userId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.transaction(response);
  }

  @Override
  public Iterable<Transaction> getTransactions(String bankId, String accountId,
    String userId) throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getTransactions(bankId, accountId, userId)
      .toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.transactions(response);
  }

  @Override public Iterable<Account> getAccounts(String bankId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getAccounts(bankId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.accounts(response);
  }

  @Override public Optional<Bank> getBank(String bankId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getBank(bankId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.bank(response);
  }

  @Override public Optional<Bank> getBank(String bankId, String userId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getBank(userId, bankId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.bank(response);
  }

  @Override public Iterable<Bank> getBanks()
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getBanks().toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.banks(response);
  }

  @Override
  public Optional<Transaction> getTransaction(String bankId, String accountId,
    String transactionId) throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getTransaction(bankId, accountId, transactionId)
      .toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.transaction(response);
  }

  @Override
  public Iterable<Transaction> getTransactions(String bankId, String accountId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getTransactions(bankId, accountId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.transactions(response);
  }

  @Override public Optional<User> getUser(String userId)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder.getUser(userId).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.user(response);
  }

  @Override
  public Optional<String> saveTransaction(String userId, String accountId,
    String currency, String amount, String otherAccountId,
    String otherAccountCurrency, String transactionType)
    throws InterruptedException
  {
    String id = UUID.randomUUID().toString();
    String request = encoder
      .saveTransaction(userId, accountId, currency, amount, otherAccountId,
        otherAccountCurrency, transactionType).toString();
    String response = sender.send(new Message(id, request));

    log.trace("{} \u2192 {}", request, response);

    return decoder.transactionId(response);
  }

  protected final Transport.Version version;
  protected final Decoder decoder;
  protected final Encoder encoder;
  protected final Sender sender;

  protected static final Logger log = LoggerFactory
    .getLogger(DefaultConnector.class);
}
