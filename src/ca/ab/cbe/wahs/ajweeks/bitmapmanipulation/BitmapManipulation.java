package ca.ab.cbe.wahs.ajweeks.bitmapmanipulation;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class BitmapManipulation {
	
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	
	public static void main(String[] args) {
		File image = new File("res/image800x600.bmp");
		Scanner in = new Scanner(System.in);
		String input = "";
		
		try (RandomAccessFile raf = new RandomAccessFile(image, "rw")) {
			do {
				System.out.println("Enter x coordinate: (0-" + (WIDTH - 1) + ")");
				int x = getCoordinate(in, WIDTH);
				
				System.out.println("Enter y coordinate: (0-" + (HEIGHT - 1) + ")");
				int y = getCoordinate(in, HEIGHT);
				
				System.out.println("Current value of pixel at " + x + ", " + y + ": ");
				
				int pos = 54 + (x * 3) + ((HEIGHT - y - 1) * (WIDTH * 3));
				
				raf.seek(pos + 2);
				System.out.print("red = ");
				System.out.println(raf.read());
				
				raf.seek(pos + 1);
				System.out.print("green = ");
				System.out.println(raf.read());
				
				raf.seek(pos);
				System.out.print("blue = ");
				System.out.println(raf.read());
				
				System.out.print("enter red value (0-255): ");
				int r = getColour(in);
				
				System.out.print("enter green value (0-255): ");
				int g = getColour(in);
				
				System.out.print("enter blue value (0-255): ");
				int b = getColour(in);
				
				raf.seek(pos);
				raf.write(b);
				raf.write(g);
				raf.write(r);
				
				System.out.println("Change another pixel? (y/n): ");
				input = in.next();
			} while (input.equals("y") || input.equals("1"));
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int getColour(Scanner in) {
		int result = -1;
		do {
			result = in.nextInt();
			if (result < 0 || result > 255) {
				result = -1;
				System.out.println("Invalid colour value, please enter a number between 0 and 255: ");
			}
		} while (result == -1);
		return result;
	}
	
	public static int getCoordinate(Scanner in, int bound) {
		int result = -1;
		do {
			result = in.nextInt();
			if (result < 0 || result > bound - 1) {
				result = -1;
				System.out.println("Invalid number. Please enter a value between 0 and " + (bound - 1) + ".");
			}
		} while (result == -1);
		return result;
	}
	
}
