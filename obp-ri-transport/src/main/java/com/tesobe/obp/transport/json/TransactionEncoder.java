/*
 * Copyright (c) TESOBE Ltd. 2016. All rights reserved.
 *
 * Use of this source code is governed by a GNU AFFERO license
 * that can be found in the LICENSE file.
 *
 */

package com.tesobe.obp.transport.json;

import com.tesobe.obp.transport.Transaction;
import org.json.JSONObject;

@SuppressWarnings("WeakerAccess") class TransactionEncoder
{
  public TransactionEncoder(Transaction t)
  {
    assert t != null;

    transaction = t;
  }

  public JSONObject toJson()
  {
    // @formatter:off
    @SuppressWarnings("UnnecessaryLocalVariable")
    JSONObject json = new JSONObject()
      .put("id", transaction.id())
      .put("account", transaction.account())
      .put("bank", transaction.bank())
      .put("other", new JSONObject()
        .put("id", transaction.otherId())
        .put("account", transaction.otherAccount()))
      .put("type", transaction.type())
      .put("description", transaction.description())
      .put("posted", Json.toJson(transaction.posted()))
      .put("completed", Json.toJson(transaction.completed()))
      .put("balance", transaction.balance())
      .put("value", transaction.value());
    // @formatter:on

    return json;
  }

  @Override public String toString()
  {
    return transaction.toString();
  }

  private final Transaction transaction;
}
