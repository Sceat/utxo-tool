

## UTXO TOOL

Utxo tool is a small java program wich can consolidate your unspent transactions ! When you mine some coins on a pool that doesn't consolidate itself you will be drowned under thousands of utxo (unspent transactions)

![](https://i.imgur.com/aBIT0ue.png)

#### Disclaimer
>This program is open-source and you will need to build it ! I do not provide any binaries as you should never trust a prebuilt file for such a sensitive operation. I'm not a professional developers so make sure to audit the code before any use on your coin mainnet, i will not be responsible for any loss.

---

### How To Build

```
git clone https://github.com/Sceat/utxo-tool.git
cd utxo-tool
gradle jar
``` 

### How to Use

Place the ```utxo_tool.x.jar``` in a new folder (from ```utxo-tool/build/libs```) .
Create a ```node_config.properties``` file and put it in the jar folder.
```
node.bitcoind.rpc.protocol = http
node.bitcoind.rpc.host = my.awesome.ip.adress
node.bitcoind.rpc.port = myawesomeport
node.bitcoind.rpc.user = my_awesome_username
node.bitcoind.rpc.password = my_awesome_password
node.bitcoind.http.auth_scheme = Basic
```
Finally launch utxo-tool ```java -jar utxo-tool.x.jar``` and follow the given instructions.

---

[![forthebadge](http://forthebadge.com/images/badges/pretty-risque.svg)](http://forthebadge.com)
[![forthebadge](http://forthebadge.com/images/badges/certified-snoop-lion.svg)](http://forthebadge.com)