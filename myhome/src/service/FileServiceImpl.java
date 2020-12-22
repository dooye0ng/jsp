package service;

import java.io.File;
import java.util.ArrayList;

import model.FileDao;
import model.FileDto;

public class FileServiceImpl implements FileService{
	private static FileService instance;
	
	FileDao dao = FileDao.getInstance();
	
	private FileServiceImpl() {
		dao = FileDao.getInstance();
	}
	
	public static FileService getInstance() {
		if(instance == null) {
			instance = new FileServiceImpl();
		}
		
		return instance;
	}
	@Override
	public File download(int no) {
		FileDto dto = dao.select(no);
		
		return new File(dto.getFilePath());
	}
	
	@Override
	public void upload(String fileName, String filePath, String uploader) {
		FileDto dto = new FileDto();
		dto.setFileName(fileName);
		dto.setFilePath(filePath);
		dto.setUploader_id(uploader);
		
		dao.insert(dto);
	}
	
	@Override
	public ArrayList<FileDto> getList() {
		return dao.selectAll();
	}
}
