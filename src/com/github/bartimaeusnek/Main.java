package com.github.bartimaeusnek;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author bartimaeusnek
 *
 */
public class Main {

	/**
	 * @param args input mca file, args[1] output files, will get set automatically (32x32 = 4096)
	 */
	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("Usage:");
			System.out.println("splitter.jar <PathToMCA> <PathToOutputDir>");
			return;
		}
		File f = new File(args[0]);
		RegionFile minecraft_regionfile = new RegionFile(f);
		DataInputStream raw_data = null;
		File[][] output = new File[32][32];

		for (int x = 0; x < 32; x++) {
			for(int y = 0; y < 32; y++) {
				System.out.println("Processing Chunk: " + x + " " + y);
				output[x][y] = new File(args[1]+"_"+x+"_"+y);
				output[0][0].getParentFile().mkdirs();
				try {
					raw_data = minecraft_regionfile.getChunkDataInputStream(x,y);
					if (raw_data != null)
						Files.copy(raw_data,output[x][y].toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}