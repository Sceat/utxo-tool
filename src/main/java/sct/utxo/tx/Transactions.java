package sct.utxo.tx;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

		List<OutputOverview> outputs = client.listUnspent().stream().filter(Output::getSpendable).limit(maxInput).collect(Collectors.toList());
		Optional<BigDecimal> oAmount = outputs.stream().map(o -> (Output) o).map(Output::getAmount).reduce(BigDecimal::add);
		if (!oAmount.isPresent())
			throw new InvalidParameterException("There is not enough coins, aborting...");

		BigDecimal amount = oAmount.get();
		BigDecimal finalAmount = amount.subtract(new BigDecimal(fee * (outputs.size() * 148 + 44) / 100000000.0)); // in*148 + out*34 + 10 plus or minus 'in'

		Map<String, BigDecimal> toAdrr = new HashMap<>(); // address, amount
		toAdrr.put(addr, finalAmount); // as we consolidate outputs we don't need change adress because we are spending
										// all coins

		System.out.println("Generated=");
		System.out.println("Will send " + finalAmount.doubleValue() + " BTC for a " + (amount.subtract(finalAmount)).doubleValue() + " BTC fee");
		System.out.println("To adress " + addr);
		System.out.println("Amount of unspent outputs : " + outputs.size());

		/*
		 * try { client.createRawTransaction(outputs, toAdrr); } catch
		 * (BitcoindException | CommunicationException e) { e.printStackTrace(); }
		 */
	}

}
