package gmConverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.common.collect.Table.Cell;

import model.GmKnowledgeBase;

public class KnowledgeBaseSerializer {
	/**
	 * Prints the KB
	 * @param kb
	 */
	public void printKB(GmKnowledgeBase kb) {
		System.out.println("The KB is the following:");
		System.out.println();
		for (Cell<String, String, String> cell : kb.getKnowledgeBase().cellSet()) {
			System.out.println(cell.getRowKey() + " | " + cell.getColumnKey() + " | " + cell.getValue());
		} 
		System.out.println();
	}
	
	/**
	 * This method serialize a given kb
	 * @param filename
	 * @param kb
	 */
	public void serialize(String filename, GmKnowledgeBase kb) {
		try {
			//We want to save the kb in a file
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			//Method for serialization of kb
			out.writeObject(kb);
			
			out.close();
			file.close();
			
			System.out.println("Object has been serialized\n" + "Data before Deserialization.");
			printKB(kb);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method deserialize a file into a kb
	 * @param filename
	 * @param kb is the KB to be assigned with deserialized object
	 */
	public void deserialize(String filename, GmKnowledgeBase kb) {
		try {
			//Read object from a file
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			
			//Method for deserialization of kb
			kb = (GmKnowledgeBase) in.readObject();
			
			in.close();
			file.close();
			
			System.out.println("Object has been deserialized\n"	+ "Data after Deserialization.");
			printKB(kb);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
