package randomAccess;

import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;

public class RandomAccessTest {
	public static void main(String[] args) {
		Path file = Paths.get("/Users/bobanvesin/uke10/random.txt");
		String s = "XYZ";
		byte data[] = s.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);
		FileChannel fc = null;
		try {
			fc = (FileChannel) Files.newByteChannel(file, READ, WRITE);
			fc.position(0);
			while (out.hasRemaining())
				fc.write(out);
			out.rewind();
			fc.position(22);
			while (out.hasRemaining())
				fc.write(out);
			out.rewind();
			fc.position(12);
			while (out.hasRemaining())
				fc.write(out);
			fc.close();
		} catch (Exception e) {
			System.out.println("Error message: " + e);
		}

	}
}
