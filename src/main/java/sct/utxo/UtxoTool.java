package sct.utxo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;
import com.neemre.btcdcli4j.core.domain.Transaction;

public class UtxoTool {

	static final String VERSION = "0.1";
	private static final Logger LOG = LoggerFactory.getLogger(UtxoTool.class);
	private BtcdClient client;

	/**
	 * 
	 * @param args
	 *            <ul>
	 *            <li><b>-from</b> The adresse which "contains" all utxos</li>
	 *            <li><b>-to</b> The adresse where the tool will send coins</li>
	 *            <li><b>-pwd</b> The pwd to decrypt</li>
	 *            <li><b>-fees</b> The amount of fees per bytes in satoshis</li>
	 *            <li><b>-max</b> The max amount of utxo to include in the
	 *            transaction</li>
	 *            <li><b>-recursive</b> Wether or not continue until there is no
	 *            more utxo on the adress</li>
	 *            </ul>
	 */
	public static void main(String[] args) {
	}

	public UtxoTool() {
		LOG.info("Bitcoin utxo tool v$1 by sceat", VERSION);
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

	public Transaction combine(Utxo... utxos) {
		return null;
	}
}
