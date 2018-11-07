package lab4.zad2.b;

import java.util.ArrayList;

public class Buffer {
	
	private ArrayList<Integer> buffer = new ArrayList<Integer>(Main.B);
	private int taken;
	
	public Buffer() {
		taken = 0;
		for (int i = 0; i < Main.B; i++) {
			this.buffer.add(0);
		}
		printBuffer();
	}
	
	public void write(int n) {
		for (int i = 0; i < Main.B; i++) {
			if(n!=0) {
				if (buffer.get(i) == 0) {
					buffer.set(i, n);
					n--;
					taken++;
				}
			}
			else return;
		}
	}
	
	public void take(int n) {
		for (int i = 0; i < Main.B; i++) {
			if(n!=0) {
				if (buffer.get(i) != 0) {
					buffer.set(i, 0);
					n--;
					taken--;
				}
			}
			else return;
		}
	}
	
	public boolean fits(int n) {		//czy się zmieści
		if (Main.B - taken < n) {
			return false;
		}
		else return true;
	}
	
	public boolean eatable(int n) {		//czy mamy tyle dostępnych
		if (taken < n) {
			return false;
		}
		else return true;
	}

	public void printBuffer() {
		System.out.println();
		for (Integer i : buffer) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
	}
}
