package com.useeinfo.framework.sugar.func.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Author: 居泽平  Date: 13-7-5, 下午1:54
 */
@SuppressWarnings("unused")
public class FileUtils extends org.apache.commons.io.FileUtils {

	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static String getContextRootPath(HttpServletRequest request) {

		String contextPath = request.getServletContext().getRealPath("/");
		logger.info("获取得到Context原始根目录为:[{}]", contextPath);
		contextPath = contextPath.replace("\\", "/");
		File file = new File(contextPath);
		if (!file.isAbsolute()) {
			file = new File(file.getAbsolutePath());
		}
		contextPath = file.getParent();
		contextPath += contextPath.endsWith("/") ? "" : "/";
		logger.info("获取得到FileUpload根目录为:[{}]", contextPath);

		return contextPath;
	}

	/**
	 * 上传文件，根据Context获得根目录，移动就文件
	 *
	 * @param uploadFile        上传文件
	 * @param configFileDirName 配置的文件夹名称
	 * @param configFileFixName 配置的文件名称
	 * @param oldFilePath       旧的文件路径
	 * @param request           HttpServletRequest
	 * @return 上传以后的文件名称
	 */
	public static String fileUpload(MultipartFile uploadFile, String configFileDirName, String configFileFixName, String oldFilePath, HttpServletRequest request) {

		String fileUploadRootPath = getContextRootPath(request);
		return fileUpload(uploadFile, configFileDirName, configFileFixName, fileUploadRootPath, oldFilePath);
	}

	/**
	 * 上传文件，根据param获得根目录，移动就文件
	 *
	 * @param uploadFile         上传文件
	 * @param configFileDirName  配置的文件夹名称
	 * @param configFileFixName  配置的文件名称
	 * @param oldFilePath        旧的文件路径
	 * @param fileUploadRootPath 文件上传的根目录
	 * @return 上传以后的文件名称
	 */
	public static String fileUpload(MultipartFile uploadFile, String configFileDirName, String configFileFixName, String fileUploadRootPath, String oldFilePath) {

		String fileName = fileUpload(uploadFile, configFileDirName, configFileFixName, fileUploadRootPath);
		if (fileName != null && !"".equals(fileName)) {
			FileUtils.moveFile(configFileDirName, oldFilePath, fileUploadRootPath);
		}
		return fileName;
	}

	/**
	 * 上传文件，根据Context获得根目录，不移动旧文件
	 *
	 * @param uploadFile        上传文件
	 * @param configFileDirName 配置的文件夹名称
	 * @param configFileFixName 配置的文件名称
	 * @param request           HttpServletRequest
	 * @return 上传以后的文件名称
	 */
	public static String fileUpload(MultipartFile uploadFile, String configFileDirName, String configFileFixName, HttpServletRequest request) {

		String fileUploadRootPath = getContextRootPath(request);
		return fileUpload(uploadFile, configFileDirName, configFileFixName, fileUploadRootPath);
	}

	/**
	 * 上传文件，根据param获得根目录，不移动旧文件
	 *
	 * @param uploadFile         上传文件
	 * @param configFileDirName  配置的文件夹名称
	 * @param configFileFixName  配置的文件名称
	 * @param fileUploadRootPath 文件上传的根目录
	 * @return 上传以后的文件名称
	 */
	public static String fileUpload(MultipartFile uploadFile, String configFileDirName, String configFileFixName, String fileUploadRootPath) {

		if (uploadFile != null && !uploadFile.isEmpty()) {
			//从配置文件获取图片上传物理根路径
			//String fileUploadRootPath = Configuration.getConfigurationByName(ConstantKeyFilePath.FILE_UPLOAD_ROOT_KEY);

			if (fileUploadRootPath == null) {
				logger.error("获取文件保存根路径异常。");
				return null;
			}

			if (!fileUploadRootPath.endsWith("/")) {
				fileUploadRootPath = fileUploadRootPath + "/";
			}
			if (!configFileDirName.endsWith("/")) {
				configFileDirName = configFileDirName + "/";
			}
			configFileDirName += new SimpleDateFormat("yyyyMMdd").format(new Date());

			//根路径+上传目录=最终路径
			String companyAbsolutePath = fileUploadRootPath + configFileDirName;
			logger.info("从配置文件获取上传文件路径为[" + companyAbsolutePath + "]");
			logger.info("创建文件夹[" + companyAbsolutePath + "]结果为[" + FileUtils.getFolder(companyAbsolutePath) + "]");
			//根据上传文件获取文件后缀
			String subFix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			//生成文件保存名称为
			String fileName = configFileDirName + "/" + configFileFixName + "_" + FileUtils.getFileNameByDateTime() + subFix;//customer.getUsername() + "_" +
			//获取文件上传路径
			String filePath = fileUploadRootPath + fileName;
			logger.info("开始上传并保存文件资料至[" + filePath + "]\r\n");

			//开始上传并保存文件资料
			try {
				uploadFile.transferTo(new File(filePath));
			} catch (IOException ex) {
				logger.error("上传文件出错！错误原因[" + ex.toString() + "]", ex);
				return null;
			}

			return fileName;
		}
		return null;
	}

	/**
	 * 移动文件
	 *
	 * @param configFileDirName 目标文件目录
	 * @param filePath          现有的文件相对路径
	 * @return 移动结果
	 */
	public static boolean moveFile(String configFileDirName, String filePath, String fileUploadRootPath) {

		if (filePath != null && !"".equals(filePath)) {
			// 从配置文件获取【文件目录绝对根路径】
			//String fileUploadRootPath = Configuration.getConfigurationByName(ConstantKeyFilePath.FILE_UPLOAD_ROOT_KEY);
			if (fileUploadRootPath == null) {
				logger.error("获取文件保存根路径异常。");
				return false;
			}

			fileUploadRootPath = fileUploadRootPath.endsWith("/") ? fileUploadRootPath : fileUploadRootPath + "/";
			configFileDirName = configFileDirName == null ? "unknow" : configFileDirName;
			configFileDirName = configFileDirName.endsWith("/") ? configFileDirName : configFileDirName + "/";
			configFileDirName += new SimpleDateFormat("yyyyMMdd").format(new Date());

			// 根据目标文件目录，取得目标目录的绝对路径
			String newFilePath = fileUploadRootPath + "/backup/" + configFileDirName;
			logger.info("创建文件夹[" + newFilePath + "]结果为[" + FileUtils.getFolder(newFilePath) + "]");

			// 根据传入的相对路径获得文件名
			String fileName = "unknow";
			if (filePath.lastIndexOf("/") > 0) {
				fileName = filePath.substring(filePath.lastIndexOf("/"), filePath.length());
			}

			// 移动文件,并返回移动结果
			return FileUtils.renameOldFile(fileUploadRootPath + filePath, newFilePath + fileName);
		}

		return false;
	}

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 *
	 * @param path 需要创建的文件目录
	 * @return 创建是否成功的文件目录
	 */
	public static boolean getFolder(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				return dir.mkdirs();
			} catch (Exception e) {
				logger.error("生成文件夹报错！", e);
			}
		}
		return true;
	}

	/**
	 * 文件重命名
	 *
	 * @param oldFile    需要重命名的文件【需完整路径】
	 * @param renamePath 重命名后的名称【需完整路径】
	 * @return 重命名情况
	 */
	public static boolean renameOldFile(String oldFile, String renamePath) {
		File file = new File(oldFile);
		if (file.exists()) {
			try {
				if (file.renameTo(new File(renamePath))) {
					logger.info("修改原文件名称为[" + renamePath + "]");
					return true;
				} else {
					logger.debug("修改失败，原文件不存在！");
					return false;
				}
			} catch (Exception ex) {
				logger.error("修改文件名称失败!", ex);
				return false;
			}
		} else {
			logger.debug("原文件不存在，无法重命名");
			return false;
		}
	}

	/**
	 * 通过日期时间生成一个时间戳
	 *
	 * @return 返回固定格式的时间戳
	 */
	public static String getFileNameByDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "_"
				+ new Random().nextInt(1000);
	}

	/**
	 * 写入文件
	 *
	 * @param fileName 要写入的文件
	 */
	public static void writeToFile(String fileName, String content, boolean append) {
		try {
			FileUtils.write(new File(fileName), content, "utf-8", append);
			logger.debug("文件 " + fileName + " 写入成功!");
		} catch (IOException e) {
			logger.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
		}
	}

	/**
	 * 写入文件
	 *
	 * @param fileName 要写入的文件
	 */
	public static void writeToFile(String fileName, String content, String encoding, boolean append) {
		try {
			FileUtils.write(new File(fileName), content, encoding, append);
			logger.debug("文件 " + fileName + " 写入成功!");
		} catch (IOException e) {
			logger.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
		}
	}

	/**
	 * 创建单个文件
	 *
	 * @param descFileName 文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			logger.debug("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			logger.debug(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建文件所在的目录失败!");
				return false;
			}
		}

		// 创建文件
		try {
			if (file.createNewFile()) {
				logger.debug(descFileName + " 文件创建成功!");
				return true;
			} else {
				logger.debug(descFileName + " 文件创建失败!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(descFileName + " 文件创建失败!");
			return false;
		}
	}

	/**
	 *
	 * 删除单个文件
	 *
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				logger.debug("删除文件 " + fileName + " 成功!");
				return true;
			} else {
				logger.debug("删除文件 " + fileName + " 失败!");
				return false;
			}
		} else {
			logger.debug(fileName + " 文件不存在!");
			return true;
		}
	}

	/**
	 * 获取工程路径
	 * @return
	 */
	public static String getProjectPath(){
		// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = "";
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
	}
}
