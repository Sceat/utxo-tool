package sct.utxo.data;

import java.util.Scanner;
import java.util.stream.IntStream;

import sct.utxo.UtxoTool;

public class Scanning {

	public static void scan() {

	}

	public static class ToolAi implements Runnable {

		private final Scanner s = new Scanner(System.in);
		private final String questions[] = { Constants.Q1, Constants.Q2, Constants.Q3, Constants.Q4, Constants.Q5, Constants.Q6, };

		private InputDatas datas = new InputDatas();

		@Override
		public void run() {
			System.out.println(Constants.W1);
			sec1();
			System.out.println(Constants.W2);
			parseNextLine(s.nextLine());
			System.out.println(Constants.W3);
			sec1();
			System.out.println(Constants.W4);
			sec1();

			IntStream.range(0, 6).forEach(i -> {
				do {
					System.out.println(questions[i]);
				} while (!parseNextLine(s.nextLine(), i));
			});

			datas.print();
		}

		private boolean parseNextLine(String s) {
			return parseNextLine(s, -1);
		}

		private boolean parseNextLine(String s, int index) {
			if (s.equalsIgnoreCase("exit"))
				UtxoTool.shutdown();

			switch (index) {
			case 0:
				datas.setAdressFrom(s);
				return true;
			case 1:
				datas.setAdressTo(s);
				return true;
			case 2:
				if (!isInt(s)) {
					System.out.println(Constants.ERR);
					return false;
				}
				datas.setMaxUtxoPerTransaction(Integer.parseInt(s));
				return true;
			case 3:
				if (!isInt(s)) {
					System.out.println(Constants.ERR);
					return false;
				}
				datas.setFee(Integer.parseInt(s));
				return true;
			case 4:
				datas.setRecursive(s.toLowerCase().contains("y"));
				return true;
			case 5:
				datas.setPwd(s);
				return true;
			default:
				return true;
			}
		}

		void sec1() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		boolean isInt(String s) {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException | NullPointerException e) {
				return false;
			}
			return true;
		}

	}

	public static class Constants {
		public static String W1 = "Welcome young disciple of chad, utxo-tool provide a fast way to deal with all these annoying unspent transactions\nYou will need to complete a serie of questions in order to do so";
		public static String W2 = "\nFirst of all, how are you today ?";
		public static String W3 = "Awesome !";
		public static String W4 = "Ready ? please follow my instructions now :";

		public static String Q1 = "Input the first adress wich contain unspent transactions";
		public static String Q2 = "\nInput the destination adress where you want to send all coins";
		public static String Q3 = "\nInput the maximum utxo per transactions";
		public static String Q4 = "\nInput the fee per byte in satoshis";
		public static String Q5 = "\nDo you want to create as many transactions needed to consolidate all utxo on the adress ?";
		public static String Q6 = "\nInput your password if your wallet is encrypted, or leave blank if you are one of those aliens without password";

		public static String ERR = "\nInvalid answer ! type exit to abandon.";
	}

}
