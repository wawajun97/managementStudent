import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.RandomAccessFile;

class Files {
	void write(String name, String major, String id) {
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\박준서\\Desktop\\study\\student.txt",true);
				
			fos.write(name.getBytes());
			fos.write(" ".getBytes());
			fos.write(major.getBytes());
			fos.write(" ".getBytes());
			fos.write(id.getBytes());
			fos.write("\n".getBytes());
				
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void read(ArrayList<Student> arr) throws IOException {		
		Scanner scanner = new Scanner(new FileReader("C:\\Users\\박준서\\Desktop\\study\\student.txt"));
		Student s;
		String name = "";
		String major = "";
		String id = "";
		int i = 0;
		while (scanner.hasNext()) {
			String str = scanner.next();
			if(i%3 == 0) {
				name = str;
				i++;
			}
			else if(i%3 == 1) {
				major = str;
				i++;
			}
			else {
				id = str;
				s = new Student(name,major,id);
				i++;
				arr.add(s);
			}
		}	
	}
	
	void clear() {
		String path = "C:\\Users\\박준서\\Desktop\\study\\student.txt";

        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            raf.setLength(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}


