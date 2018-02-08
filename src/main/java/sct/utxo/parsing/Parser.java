package sct.utxo.parsing;

import java.util.Objects;
import java.util.Optional;

public class Parser {

	private String adress_from;
	private String adress_to;
	private Optional<String> passwd = Optional.empty();
	private int fees = 1;
	private int maxStack = 100;
	private boolean recursive;

	public void parse(String... args) {

		Objects.requireNonNull(adress_from, "Missing adress_from argument");
		Objects.requireNonNull(adress_to, "Missing adress_to argument");
		if (fees == -1)
			throw new IllegalArgumentException("Missing fees arguments, the tool can't determine fees by itself (yet ?)");
	}

	public void clearPassword() {
		passwd = Optional.empty();
	}

	public String getAdress_from() {
		return adress_from;
	}

	public String getAdress_to() {
		return adress_to;
	}

	public Optional<String> getPasswd() {
		return passwd;
	}

	public int getFees() {
		return fees;
	}

	public int getMaxStack() {
		return maxStack;
	}

	public boolean isRecursive() {
		return recursive;
	}

}
