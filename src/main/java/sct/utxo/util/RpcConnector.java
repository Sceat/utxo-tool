package sct.utxo.util;

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

public class RpcConnector {

	public BtcdClient initRPC() throws BitcoindException, CommunicationException, IOException {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpProvider = HttpClients.custom().setConnectionManager(cm).build();
		Properties nodeConfig = new Properties();
		InputStream is = new BufferedInputStream(new FileInputStream("node_config.properties"));
		nodeConfig.load(is);
		is.close();
		return new BtcdClientImpl(httpProvider, nodeConfig);
	}
}
