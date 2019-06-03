package kr.or.ddit.util;

public class PartUtil {
	
	/**
	 * 
	* Method : getFileName
	* 작성자 : PC10
	* 변경이력 :
	* @param contentDisposition
	* @return
	* Method 설명 : contentDisposition에서 파일명을 반환한다
	 */
	public static String getFileName(String contentDisposition) {
		// form-data; name="profile"; filename="cony.png"
		// 위 문장에서 cony.png 추출하기
		String[] arr = contentDisposition.split("; ");
		for(String split : arr){
			if(split.startsWith("filename=")){
				int start = split.indexOf("\"")+1;
				int end = split.lastIndexOf("\"");
				return split.substring(start, end);
			}
		}
		return "";
	}

	/**
	 * 
	* Method : getExt
	* 작성자 : PC10
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일명에서 파일 확장자를 반환
	 */
	public static String getExt(String fileName) {
		// 다른 방법(결과는 동일)
		String[] splited = fileName.split("\\.");
		if(splited.length==1) 
			return "";
		else
			return splited[splited.length-1];
		
//		if(fileName.indexOf(".") == -1){
//			return "";
//		}
//		int lastDot = fileName.lastIndexOf(".");
//		
//		return fileName.substring(lastDot+1);
	}

}
