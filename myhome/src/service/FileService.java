package service;

import java.io.File;
import java.util.ArrayList;

import model.FileDto;

public interface FileService {
	public void upload(String fileName, String filePath, String uploader);
	public File download(int no);
	public ArrayList<FileDto> getList();
}
