package sct.utxo;

import java.math.BigDecimal;

import com.neemre.btcdcli4j.core.domain.Transaction;

public class Utxo {

	private String txId;
	private String vOut;
	private BigDecimal amount;

	private Transaction transaction;

	public Utxo(String tx, String vOut, BigDecimal amount, Transaction transaction) {
		this.txId = tx;
		this.vOut = vOut;
		this.amount = amount;
		this.transaction = transaction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public String getTxId() {
		return txId;
	}

	public String getvOut() {
		return vOut;
	}

}
