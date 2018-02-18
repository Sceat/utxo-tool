package sct.utxo.data;

public class InputDatas {

	private String adressFrom, adressTo;
	private int fee, maxUtxoPerTransaction;
	private boolean recursive;

	public void setAdressTo(String adressTo) {
		this.adressTo = adressTo;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public void setMaxUtxoPerTransaction(int maxUtxoPerTransaction) {
		this.maxUtxoPerTransaction = maxUtxoPerTransaction;
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

	public void print() {
		System.out.println(
				"You want to consolidate " + getMaxUtxoPerTransaction() + " inputs\nand send the transaction to the adress " + getAdressTo() + " with a fee of " + getFee() + " satoshis/byte");
	}

	@Override
	public String toString() {
		return "InputDatas [adressFrom=" + adressFrom + ", adressTo=" + adressTo + ", fee=" + fee + ", maxUtxoPerTransaction=" + maxUtxoPerTransaction + ", recursive=" + recursive + "]";
	}

}
