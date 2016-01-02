package com.alegend.www.al.activities;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;


public class ALog {
    @SuppressWarnings("unused")
    private static final String tag = "Log";
    public static final AtomicBoolean DEBUG = new AtomicBoolean(true);
    
    public static boolean isShow = true;// 开发模式
	// public static boolean isShow = false;//上线模式
	public static final String filename = "ckbLog.txt";
	public String LogInfo = "";

    private ALog() {
    }

    public static void initDebugLevel(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        boolean newValue = preferences.getBoolean(GlobalConstant.KEY_DEBUG_ENABLE, false);
        DEBUG.set(true);

    }

    public static int v(String tag, String msg) {
    	if (DEBUG.get()) {
    		return android.util.Log.v(tag, "" + msg);
    	}else {
            return 0;
        }
    }

    public static int v(String tag, String msg, Throwable tr) {
    	if (DEBUG.get()) {
    		return android.util.Log.v(tag, "" + msg, tr);
    	}else {
            return 0;
        }
    }

    public static int d(String tag, String msg) {
        if (DEBUG.get()) {
            return android.util.Log.d(tag, "" + msg);
        } else {
            return 0;
        }
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (DEBUG.get()) {
            return android.util.Log.d(tag, "" + msg, tr);
        } else {
            return 0;
        }
    }

    public static int format(String tag, String format, Object... args) {
        if (DEBUG.get()) {
            return android.util.Log.i(tag, String.format(format, args));
        } else {
            return 0;
        }
    }

    public static int i(String tag, String msg) {
    	if (DEBUG.get()) {
    		return android.util.Log.i(tag, "" + msg);
    	 } else {
             return 0;
         }
    }

    public static int i(String tag, String msg, Throwable tr) {
    	if (DEBUG.get()) {
    		return android.util.Log.i(tag, "" + msg, tr);
    	}else {
            return 0;
        }
    }

    public static int w(String tag, String msg) {
        return android.util.Log.w(tag, "" + msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return android.util.Log.w(tag, "" + msg, tr);
    }

    public static int e(String tag, String msg) {
        return android.util.Log.e(tag, "" + msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return android.util.Log.e(tag, "" + msg, tr);
    }

    public static String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }

    public static boolean isLoggable(String tag, int level) {
        return android.util.Log.isLoggable(tag, level);
    }

    public static int println(int priority, String tag, String msg) {
        return android.util.Log.println(priority, tag, "" + msg);
    }

    public static String getTag(Class<?> clazz) {
        return clazz.getSimpleName();
    }
    
    /**
	 * 打LOG 输出当前类名，方法名，代码行数
	 * 
	 * @param msg
	 */
	public static void v(String msg) {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		String TAG = toStringBuffer.toString();
		ALog.v(TAG, msg);
	//write(TAG + "message" + msg, Log.VERBOSE);
	}

	public static void d(String msg) {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		String TAG = toStringBuffer.toString();
		ALog.d(TAG, msg);
		//write(TAG + "message" + msg, Log.DEBUG);
	}

	public static void i(String msg) {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		String TAG = toStringBuffer.toString();
		ALog.i(TAG, msg);
		//write(TAG + "   " + msg, Log.INFO);
	}

	public static void w(String msg) {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		String TAG = toStringBuffer.toString();
		ALog.w(TAG, msg);
		//write(TAG + "message" + msg, Log.WARN);
	}

	public static void e(String msg) {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		String TAG = toStringBuffer.toString();
		ALog.e(TAG, ALog.getStackTraceString(new Throwable()) + msg);
		//write(TAG + "\n" + Log.getStackTraceString(new Throwable()) + msg,
		//		Log.ERROR);
	}

	/**
	 * 将Log写到日志文件中
	 * 
	 * @param text
	 * @param level
	 */
	private static synchronized void write(String text, int level) {
		StringBuilder sb = new StringBuilder();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd-HH:mm:ss");// 格式化日期时间
		String infoTime = dateFormat.format(now);
		sb.append(infoTime);
		switch (level) {
		case android.util.Log.VERBOSE:
			sb.append("[V]\t\n");
			break;
		case android.util.Log.DEBUG:
			sb.append("[D]\t\n");
			break;
		case android.util.Log.INFO:
			sb.append("[I]\t\n");
			break;
		case android.util.Log.WARN:
			sb.append("[W]\t\n");
			break;
		case android.util.Log.ERROR:
			sb.append("[E]\t\n");
			break;
		}
		sb.append(text);
		// 可以任意的访问文件的任何地方
		RandomAccessFile raf = null;
		try {
			String fileName = filename + "_" + infoTime.trim() + ".txt";
			File logFile = new File("");
			if (!logFile.exists()) {
				logFile.mkdirs();
			}
			sb.append("\n" + fileName);
			BufferedWriter localBufferedWriter = new BufferedWriter(
					new FileWriter(new File(logFile, filename), true));
			localBufferedWriter.write(sb.toString());
			localBufferedWriter.newLine();
			localBufferedWriter.flush();
			localBufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
				}
			}
		}

	}
	//返回所有的东西，就是file name+function name + line number
	public static String getFileLineMethod() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		return toStringBuffer.toString();
	}
	// 当前文件名
	public static String _FILE_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		return traceElement.getFileName();
	}
	// 当前方法名
	public static String _FUNC_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		return traceElement.getMethodName();
	}
	// 当前行号
	public static int _LINE_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		return traceElement.getLineNumber();
	}
	// 当前时间
	public static String _TIME_() {
		Date now = new Date(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(now);

	}
}