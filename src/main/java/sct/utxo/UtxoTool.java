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

import sct.utxo.data.Scanning;
import sct.utxo.data.Scanning.ToolAi;

public class UtxoTool {

	static final String VERSION = "0.1";
	private BtcdClient client;

	public static void main(String[] args) {
		ToolAi t = new Scanning.ToolAi();
		t.run();
	}

	public UtxoTool() {
		System.out.println("Bitcoin utxo tool v" + VERSION + " by sceat");
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
