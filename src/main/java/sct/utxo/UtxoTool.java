package sct.utxo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;

public class UtxoTool {

	static final String VERSION = "0.1.3";
	private BtcdClient client;

	public static void main(String[] args) {
		new UtxoTool();
	}

	public UtxoTool() {
		System.out.println("Bitcoin utxo tool v" + VERSION + " by sceat\n");
		// InputDatas data = Scanning.scan();
		System.out.println("\nConnection to node.. please wait");
		try {
			initRPC();
		} catch (BitcoindException | CommunicationException | IOException e) {
			e.printStackTrace();
			shutdown();
		}
		System.out.println("successfully connected to node " + client.getNodeVersion());
	}

	private void initRPC() throws BitcoindException, CommunicationException, IOException {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpProvider = HttpClients.custom().setConnectionManager(cm).build();
		Properties nodeConfig = new Properties();
		InputStream is = new BufferedInputStream(new FileInputStream("node_config.properties"));
		nodeConfig.load(is);
		is.close();
		this.client = new BtcdClientImpl(httpProvider, nodeConfig);
	}

	public static void shutdown() {
		System.out.println("Bye teddy.");
		System.exit(0);
	}
}
