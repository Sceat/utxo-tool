package sct.utxo.tx;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.Output;
import com.neemre.btcdcli4j.core.domain.OutputOverview;

public class Transactions {

	private BtcdClient client;

	public Transactions(BtcdClient client) {
		this.client = client;
	}

	public void makeTransaction(String addr, int maxInput, int fee) throws BitcoindException, CommunicationException {

		System.out.println("Total Utxo amount :" + client.listUnspent().size());

		boolean acceptAll = false;
		boolean asked = false;

		do {
			List<OutputOverview> outputs = client.listUnspent().stream().filter(Output::getSpendable).limit(maxInput).collect(Collectors.toList());
			Optional<BigDecimal> oAmount = outputs.stream().map(o -> (Output) o).map(Output::getAmount).reduce(BigDecimal::add);
			if (!oAmount.isPresent())
				throw new InvalidParameterException("There is not enough coins, aborting...");

			BigDecimal amount = oAmount.get();
			BigDecimal finalAmount = amount.subtract(new BigDecimal(fee * (outputs.size() * 148 + 44) / 100000000.0)); // in*148 + out*34 + 10 plus or minus 'in'

			Map<String, BigDecimal> toAdrr = new HashMap<>(); // address, amount
			toAdrr.put(addr, finalAmount); // as we consolidate outputs we don't need change adress because we are spending
											// all coins

			System.out.println("Will send " + finalAmount.doubleValue() + " Coins for a " + (amount.subtract(finalAmount)).doubleValue() + " Coins fee");
			System.out.println("To : " + addr);

			if (!asked) {
				acceptAll = acceptAll();
				asked = true;
			}
			if (!acceptAll && !acceptTransaction())
				return; // to avoid accepting huge/bugged fee

			try {
				String tx = client.createRawTransaction(outputs, toAdrr);
				client.sendRawTransaction(client.signRawTransaction(tx).getHex());
				System.out.println("\n>> [" + finalAmount.doubleValue() + " sent!]\n");
			} catch (BitcoindException e) {
				e.printStackTrace();
			}
		} while (client.listUnspent().size() > 1);

	}

	@SuppressWarnings("resource")
	private boolean acceptTransaction() {
		System.out.println("\nDo you want to comfirm the transaction ? (y/n)");
		return new Scanner(System.in).nextLine().toLowerCase().contains("y");
	}

	@SuppressWarnings("resource")
	private boolean acceptAll() {
		System.out.println("\nDo you want to automatically accept all transactions instead of confirming one by one ? (y/n)");
		return new Scanner(System.in).nextLine().toLowerCase().contains("y");
	}

}
