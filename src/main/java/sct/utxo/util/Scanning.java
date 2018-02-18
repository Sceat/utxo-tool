package sct.utxo.util;

import java.util.Scanner;
import java.util.stream.IntStream;

import sct.utxo.UtxoTool;
import sct.utxo.data.InputDatas;

public class Scanning {

	public static InputDatas scan() {
		ToolAi t = new ToolAi();
		t.run();
		return t.getDatas();
	}

	public static class ToolAi implements Runnable {

		private final Scanner s = new Scanner(System.in);
		private final String questions[] = { Constants.Q1, Constants.Q2, Constants.Q3, };

		private InputDatas datas = new InputDatas();

		@Override
		public void run() {
			System.out.println(Constants.W1);

			IntStream.range(0, 3).forEach(i -> {
				do {
					System.out.println(questions[i]);
				} while (!parseNextLine(s.nextLine(), i));
			});

			datas.print();
		}

		private boolean parseNextLine(String s, int index) {
			if (s.equalsIgnoreCase("exit"))
				UtxoTool.shutdown();

			switch (index) {
			case 0:
				datas.setAdressTo(s);
				return true;
			case 1:
				if (!isInt(s)) {
					System.out.println(Constants.ERR);
					return false;
				}
				datas.setMaxUtxoPerTransaction(Integer.parseInt(s));
				return true;
			case 2:
				if (!isInt(s)) {
					System.out.println(Constants.ERR);
					return false;
				}
				datas.setFee(Integer.parseInt(s));
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

		public InputDatas getDatas() {
			return datas;
		}

	}

	public static class Constants {
		public static String W1 = "Welcome young disciple of chad, utxo-tool provide a fast way to deal with all these annoying unspent transactions\nYou will need to complete a serie of questions in order to do so";

		public static String Q1 = "\nInput the destination adress where you want to send all coins";
		public static String Q2 = "\nInput the maximum utxo per transactions";
		public static String Q3 = "\nInput the fee per byte in satoshis";

		public static String ERR = "\nInvalid answer ! type exit to abandon.";
	}

}
