package org.eweb4j.solidbase.files.model;

import java.io.File;
import java.util.Collection;

import org.eweb4j.mvc.view.PageMod;

public interface FilesService {

	String create(Files files, File uploadFile) throws FilesException;

	PageMod<Files> getPage(int p, int n) throws FilesException;

	void remove(Long id) throws FilesException;

	void batchRemove(Long[] ids) throws FilesException;

	void update(Files files) throws FilesException;

	Files get(Long id) throws FilesException;

	/**
	 * 找出所有图片，即 media_type == image 的
	 * @date 2013-4-2 上午11:18:57
	 * @return
	 * @throws FilesException
	 */
	Collection<Files> getAllImages() throws FilesException;
	
}
