/**
 * 
 */
package com.github.bartimaeusnek;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author bartimaeusnek
 *
 */
public class Main {

	/**
	 * @param args[0] input mca file, args[1] output files, will get set automatically (32x32 = 4096)
	 */
	public static void main(String[] args) {
		File f = new File(args[0]);
		RegionFile minecraft_regionfile = new RegionFile(f);
		DataInputStream raw_data = null;
		File[][] output = new File[32][32];
		
		for (int x = 0; x < 32; x++) {
		for(int y = 0; y < 32; y++) {
			output[x][y] = new File(args[1]+"_"+x+"_"+y);
		try {
			FileWriter w = new FileWriter(output[x][y]);
			ArrayList<Integer> buffer = new ArrayList<Integer>();
				raw_data = minecraft_regionfile.getChunkDataInputStream(x,y);
				if(raw_data!=null) {
				do {
				buffer.add(raw_data.read());
				}while(buffer.get(buffer.size()-1) != -1);
			for(int z = 0; z < buffer.size(); z++) 
			w.write(buffer.get(z));
			w.close();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	}
}
