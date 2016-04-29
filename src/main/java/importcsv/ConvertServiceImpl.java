package importcsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Fund;

public class ConvertServiceImpl implements ConvertService{
	
    
	@Override
	public List<Fund> readFromFile() {
		BufferedReader buffer = null;
		
		List<Fund> fundsList = new ArrayList<Fund>();
		
		try {
			String line;
			buffer = new BufferedReader(new FileReader("C:/Users/Baka/Desktop/data.csv"));
			
			while ((line = buffer.readLine()) != null) {
				Fund f = CSVtoArrayList(line);
				if (f != null) {
					fundsList.add(f);
				} else {
//					System.out.println("No fund");
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null) buffer.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		return fundsList;
		
	}

	@Override
	public Fund CSVtoArrayList(String CSV) {
		if (CSV != null) {
			String[] splitData = CSV.split("\\s*,\\s*");
			
			for (int i = 0; i < splitData.length; i+=3) {
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
					
					int id = Integer.parseInt(splitData[i].trim());

					DateFormat format = new SimpleDateFormat("dd-MM-yy");
					Date date = null;
					try {
						date = format.parse(splitData[i+1].trim());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					double value = Double.parseDouble(splitData[i+2].trim());
					
					Fund f = new Fund(id, date, value);
					return f;
				}
			}
		}
		return null;
		
		
	}

}
