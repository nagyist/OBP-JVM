/*
 * Copyright (c) TESOBE Ltd. 2016. All rights reserved.
 *
 * Use of this source code is governed by a GNU AFFERO license
 * that can be found in the LICENSE file.
 *
 */
package com.tesobe.obp.transport;

import java.util.Optional;

/**
 * North side API.
 *
 * @since 2016.9
 */
@SuppressWarnings("WeakerAccess") public interface Connector
{
  /**
   * Anonymously get an account.
   *
   * @param bankId An invalid bank id means an empty result.
   * An all white space bank id is invalid.
   * @param accountId An invalid account id means an empty result.
   * An all white space account id is invalid.
   *
   * @return An empty result if the account is not explicitly linked to the
   * user.
   * If the account is public but not linked to the user, empty will be
   * returned.
   *
   * @throws InterruptedException Network trouble
   */
  Optional<Account> getAccount(String bankId, String accountId)
    throws InterruptedException;

  /**
   * @param bankId An invalid bank id means an empty result.
   * An all white space bank id is invalid.
   * @param accountId An invalid account id means an empty result.
   * An all white space account id is invalid.
   * @param userId An invalid user id means an empty result.
   * An all white space user id is invalid.
   *
   * @return An empty result if the account is not explicitly linked to the
   * user.
   * If the account is public but not linked to the user, empty will be
   * returned.
   *
   * @throws InterruptedException Network trouble
   */
  Optional<Account> getAccount(String bankId, String accountId, String userId)
    throws InterruptedException;

  Iterable<Account> getAccounts(String bankId)
    throws InterruptedException;

  /**
   * All private accounts the user is explicitly linked to.
   * No public accounts that the user is not linked to will be returned.
   * The resulting iterable's {@code next()} will not produce {@code null} but
   * fields in the accounts returned may be {@code null}.
   *
   * @param bankId An invalid bank id means an empty result.
   * An all white space bank id is invalid.
   * @param userId An invalid user id means an empty result.
   * An all white space user id is invalid.
   *
   * @return The user's private banks or an empty result.
   *
   * @throws InterruptedException Network trouble
   */
  Iterable<Account> getAccounts(String bankId, String userId)
    throws InterruptedException;

  /**
   * Anonymous request for a bank.
   *
   * @param bankId sent to the south as is, even if it is null
   *
   * @return empty if the bankId is invalid
   *
   * @throws InterruptedException Network trouble
   */
  Optional<Bank> getBank(String bankId)
    throws InterruptedException;

  /**
   * @param bankId An invalid bank id means an empty result.
   * An all white space bank id is invalid.
   * @param userId An invalid user id means an empty result.
   * An all white space user id is invalid.
   *
   * @return An empty result if the bank is not explicitly linked to the user.
   * If the bank is public but not linked to the user, empty will be returned.
   *
   * @throws InterruptedException Network trouble
   */
  Optional<Bank> getBank(String bankId, String userId)
    throws InterruptedException;

  /**
   * Anonymously get banks.
   *
   * @return never null
   *
   * @throws InterruptedException Network trouble
   */
  Iterable<Bank> getBanks() throws InterruptedException;

  /**
   * All private banks the user is explicitly linked to.
   * No public banks that the user is not linked to will be returned.
   * The resulting iterable's {@code next()} will not produce {@code null} but
   * fields in the banks returned may be {@code null}.
   *
   * @param userId An invalid user id means an empty result.
   * An all white space user id is invalid.
   *
   * @return The user's private banks or an empty result.
   *
   * @throws InterruptedException Network trouble
   */
  Iterable<Bank> getBanks(String userId)
    throws InterruptedException;

  Optional<Transaction> getTransaction(String bankId, String accountId,
    String transactionId, String userId)
    throws InterruptedException;

  Iterable<Transaction> getTransactions(String bankId, String accountId,
    String userId) throws InterruptedException;

  Optional<Transaction> getTransaction(String bankId, String accountId,
    String transactionId) throws InterruptedException;

  Iterable<Transaction> getTransactions(String bankId, String accountId)
    throws InterruptedException;

  Optional<User> getUser(String userId)
    throws InterruptedException;

  Optional<String> saveTransaction(String userId, String accountId,
    String currency, String amount, String otherAccountId,
    String otherAccountCurrency, String transactionType)
    throws InterruptedException;
}
