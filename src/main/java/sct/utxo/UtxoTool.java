package sct.utxo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

	static final String[] print = { "Bitcoin utxo tool v" + VERSION + " by sceat", "Connecting to node.. please wait", "Unable to initialize the rpc client, aborting..",
			"successfully connected to node v", };

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
			System.out.println("you have " + client.getBalance().doubleValue() + " Coins");
		} catch (BitcoindException | CommunicationException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
		loggers.add(LogManager.getRootLogger());
		for (Logger logger : loggers) {
			logger.setLevel(Level.OFF);
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
