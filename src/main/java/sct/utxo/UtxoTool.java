package sct.utxo;

import java.io.IOException;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;

import sct.utxo.data.InputDatas;
import sct.utxo.tx.Transactions;
import sct.utxo.util.RpcConnector;
import sct.utxo.util.Scanning;

public class UtxoTool {

	static final String VERSION = "0.1.4";

	private final RpcConnector connector = new RpcConnector();
	private BtcdClient client;

	static final String[] print = { "Bitcoin utxo tool v" + VERSION + " by sceat\nTHIS IS A DEBUG PRINT ONLY VERSION, THE TOOL DOESN'T MAKE ANY TRANSACTIONS YET BUT ONLY PRINT INFOS",
			"Connecting to node.. please wait", "Unable to initialize the rpc client, aborting..", "successfully connected to node v", };

	public static void main(String[] args) {
		new UtxoTool();
	}

	public UtxoTool() {

		System.out.println(print[0]);

		InputDatas data = Scanning.scan();

		System.out.println(print[1]);

		try {
			this.client = connector.initRPC();
		} catch (BitcoindException | CommunicationException | IOException e) {
			System.err.println(print[2]);
			e.printStackTrace();
			shutdown();
		}

		System.out.println(print[3] + client.getNodeVersion());

		try {
			System.out.println("you have " + client.getBalance().doubleValue() + " Btc");
		} catch (BitcoindException | CommunicationException e) {
			e.printStackTrace();
		}

		try {
			new Transactions(client).makeTransaction(data.getAdressTo(), data.getMaxUtxoPerTransaction(), data.getFee());
		} catch (BitcoindException | CommunicationException e) {
			e.printStackTrace();
		}
	}

	public static void shutdown() {
		System.out.println("Bye teddy.");
		System.exit(0);
	}
}
