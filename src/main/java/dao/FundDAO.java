package dao;

import java.util.List;

import model.Fund;

public interface FundDAO {
	//CRUD:
    public void save(Fund fund);
    public Fund getById(int id);
    public void update(Fund fund);
    public void deleteById(int id);
    public List<Fund> getAll();
}
