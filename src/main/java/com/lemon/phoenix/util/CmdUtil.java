package com.lemon.phoenix.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.apache.xmlbeans.impl.xb.xsdschema.DerivationControl;

public class CmdUtil {
	
	/**
	 * 去执行dos环境下的命令工具方法
	 * @param command
	 * @return
	 */
	public static List<String> execCmd(String command){
		//1、初始化java运行时对象
		Runtime runtime = Runtime.getRuntime();
		BufferedReader bufferedReader = null;
		//2、runtime对象去执行对应的命令
		try {
			Process process = runtime.exec("cmd /c "+command);
			//3、通过BufferReader读取cmd执行结果
			 bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			//初始化一个集合用来存储命令的执行结果
			List<String> content = new ArrayList<String>();
			//4、通过循环的方式从缓冲区里面去拿内容
			while((line=bufferedReader.readLine()) !=null){
				if(line.contains("Appium REST http interface listener started on")){
					System.out.println("Appium启动成功");
				}
				content.add(line);
			}
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//在执行完毕之后，将缓冲读取流关闭
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 得到当前连接到PC端的所有设备名集合
	 * @return
	 */
	public static List<String>  getUdidList(){
		List<String> results = execCmd("adb devices");
		//初始化一个集合用于存储解析得到的deviceName
		List<String> udidList = new ArrayList<String>();
		//得到设备的udid（设备名）
		for(int i=0; i<results.size();i++){
			//跳过第一行List of devices attached
			if(i!=0 && i!=(results.size()-1)){
				String[] splitInfo = results.get(i).split("\t");
				udidList.add(splitInfo[0]);
			}
		}
		//当我们通过adb devices没有检测到设备的时候，我们没有必要再继续下去运行了
		if(udidList.size() == 0){
			System.out.println("获取设备名失败，终止程序运算");
			System.exit(0);
		}
		return udidList;
		
	}
	
	/**
	 * 根据设备数量得到端口的列表集合
	 * @param startPort
	 * @param deviceNum
	 * @return
	 */
	public static List<String> getPortList(int startPort,int deviceNum){
		//存储端口列表
		List<String> portList = new ArrayList<String>();
		while(portList.size() != deviceNum){
			//端口的检测与释放
			if(checkPortIsUsed(startPort)){
				String pid = getPidByPort(startPort);
				killProcessByPid(Integer.parseInt(pid));
			}
			portList.add(startPort+"");
			startPort = startPort + 2;
		}
		return portList;
	}
	
	
	/**
	 * 检测端口是否有被占用
	 * @param port
	 * @return
	 */
	public static boolean checkPortIsUsed(int port){
		//1、执行netstat -ano | findstr 端口
		List<String> results = execCmd("netstat -ano | findstr "+port);
		if(results.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据当前端口号得到对应的进程id
	 * @param port
	 */
	public static String getPidByPort(int port){
		List<String> results = execCmd("netstat -ano | findstr "+port);
		for (String line : results) {
			String [] splitInfo = line.split("\\s");
			return splitInfo[splitInfo.length-1];
		}
		return null;
	}
	
	/**
	 * 根据进程号去杀死对应的进程
	 * @param pid
	 */
	public static void killProcessByPid(int pid){
		//1、执行taskkill -f -pid 
		List<String> results = execCmd("taskkill -f -pid " + pid);
		for (String line : results) {
			if(line.contains("成功")){
				System.out.println("杀死对应的进程【"+pid+"】成功");
			}
		}
	}
	
	
	public static void main(String[] args) {
		getPidByPort(5037);
	}

}
