package ru.katalexey.util;

import java.util.Random;

public class SwingsCalculator {
	private static final int iterations = 50000;
	private static final int tourneys = 50;

	// for 6max
	private static double p_first = 0.182;
	private static double s_first = 2.7;

	private static double p_second = 0.182;
	private static double s_second = 3.47/3.5;
	
	// for hu
	private static double p_win = 0.51;
	private static double s_win = 0.96;

	private static Random r = new Random();

	public static void main(String[] args) {
		int[] ress = new int[220];
		for (int i = 0; i < iterations; i++) {
			double h = runFor6max(i);
			int res = 100 + (int) Math.round(h);
			if (res < 0) ress[0]++; else
				if (res > ress.length - 1) ress[ress.length - 1]++; else
					ress[res]++;
		}
		double c = 0;
		System.out.println("result----------------------");
		c = 0;
		for (int i = 0; i < ress.length; i++) {
			double p = ress[i] * 100.0 / iterations;
			c += p;
			System.out.println(i + "  " + ress[i] + "  " + p + "  " + c);
		}
	}

	private static double runFor6max(int i) {
		double[] hist = new double[tourneys + 1];
		hist[0] = 0;
		for (int j = 1; j < hist.length; j++) {
			double p = r.nextDouble();
			if (p < p_first) {
				hist[j] = hist[j - 1] + s_first;
			} else if (p < p_first + p_second) {
				hist[j] = hist[j - 1] + s_second;
			} else {
				hist[j] = hist[j - 1] - 1;
			}
		}
		return hist[tourneys];
	}
	
	private static double runForHU(int i) {
		double[] hist = new double[tourneys + 1];
		hist[0] = 0;
		for (int j = 1; j < hist.length; j++) {
			double p = r.nextDouble();
			if (p < p_win) {
				hist[j] = hist[j - 1] + s_win;
			} else {
				hist[j] = hist[j - 1] - 1;
			}
		}
		return hist[tourneys];
	}
}
