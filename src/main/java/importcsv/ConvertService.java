package importcsv;

import java.util.List;

import model.Fund;

public interface ConvertService {
	public List<Fund> readFromFile();
	public Fund CSVtoArrayList(String CSV);
}
