package sct.utxo.data;

public class InputDatas {

	private String adressFrom, adressTo, pwd;
	private int fee, maxUtxoPerTransaction;
	private boolean recursive;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setAdressFrom(String adressFrom) {
		this.adressFrom = adressFrom;
	}

	public void setAdressTo(String adressTo) {
		this.adressTo = adressTo;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public void setMaxUtxoPerTransaction(int maxUtxoPerTransaction) {
		this.maxUtxoPerTransaction = maxUtxoPerTransaction;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	public String getAdressFrom() {
		return adressFrom;
	}

	public String getAdressTo() {
		return adressTo;
	}

	public int getFee() {
		return fee;
	}

	public int getMaxUtxoPerTransaction() {
		return maxUtxoPerTransaction;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void print() {
		System.out.println("You want to consolidate " + getMaxUtxoPerTransaction() + " inputs on the adress " + getAdressFrom() + (isRecursive() ? " as many time as possible" : "")
				+ "\nand send the transaction to the adress " + getAdressTo() + " with a fee of " + getFee() + " satoshis/byte");
	}

	@Override
	public String toString() {
		return "InputDatas [adressFrom=" + adressFrom + ", adressTo=" + adressTo + ", pwd=" + pwd + ", fee=" + fee + ", maxUtxoPerTransaction=" + maxUtxoPerTransaction + ", recursive=" + recursive
				+ "]";
	}

}
