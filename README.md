
## UTXO TOOL

Utxo tool is a small java program wich can consolidate your unspent transactions ! When you mine some coins on a pool that doesn't consolidate itself you will be drowned under thousands of utxo (unspent transactions)

![](https://i.imgur.com/aBIT0ue.png)

#### Disclaimer
>This program is open-source and you will need to build it ! I do not provide any binaries as you should never trust a prebuilt file for such a sensitive operation. I'm not a professional developers so make sure to audit the code before any use on your coin mainnet, i will not be responsible for any loss.

---
### Configuration
Under ```src/main/resources/``` there is a ```node_config.properties``` file. Fill in your node infos.
```
node.bitcoind.rpc.protocol = http
node.bitcoind.rpc.host = my.awesome.ip.adress
node.bitcoind.rpc.port = myawesomeport
node.bitcoind.rpc.user = my_awesome_username
node.bitcoind.rpc.password = my_awesome_password
node.bitcoind.http.auth_scheme = Basic
```

### How To Use
```
git clone https://github.com/Sceat/utxo-tool.git
cd utxo-tool
gradle jar
java -jar build/libs/utxo_tool.0.1.jar
``` 
and then follow instructions.

---

[![forthebadge](http://forthebadge.com/images/badges/pretty-risque.svg)](http://forthebadge.com)
[![forthebadge](http://forthebadge.com/images/badges/certified-snoop-lion.svg)](http://forthebadge.com)