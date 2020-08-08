package ChongErUtils.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类用来读取文本和写入文本，修改文本 方法： 
 * 1 打开文件 
 * 2 关闭文件 
 * 3 读取指定行文本 
 * 4 读取全文并分行存储 
 * 5 读取指定范围行文本 
 * 6 修改全文 
 * 7 修改指定行 
 * 8 修改指定范围行 
 * 9 删除全文 
 * 10 删除指定行 
 * 11 删除指定范围行
 * 
 * @author CoolClawEra
 *
 */

public class TextIO {
	private File textHand; // 文本句柄
	private List<String> textDataRows;// 单位存储文本数据
	private String strUrl;// 文本路径

	public TextIO() {
	}

	public TextIO(String strUrl) {
		this.strUrl = strUrl;
	}

	public File getTextHand() {
		return textHand;
	}

	public void setTextHand(File textHand) {
		close();
		this.textHand = textHand;
	}

	public List<String> getTextDataRows() {
		return textDataRows;
	}

	public void setTextDataRows(List<String> textDataRows) {
		this.textDataRows = textDataRows;
	}

	public String getStrUrl() {
		return strUrl;
	}

	public void setStrUrl(String strUrl) {
		close();
		this.strUrl = strUrl;
	}

	// 1 打开文件
	public boolean openText() {

		textHand = new File(strUrl);

		if (!textHand.isFile()) {
			System.out.println("文本打开失败");
			return false;
		} else {
			readRowAll();
			System.out.println("文本打开成功");
			return true;
		}
	}

	// 打开文件
	public boolean openText(String stUrl) {
		close();
		this.strUrl = stUrl;
		textHand = new File(strUrl);

		if (!textHand.isFile()) {
			System.out.println("文本打开失败");
			return false;
		} else {
			readRowAll();
			System.out.println("文本打开成功");
			return true;
		}
	}

	// 2 关闭文件
	public void close() {
		textHand = null;
		System.out.println("文本已关闭");
	}

	// 3 读取指定一行文本
	// 读取指定一行文本
	public int readRow(int n) {
		int i = 0;
		if (textHand == null) {
			System.out.println("文件未打开，请先打开文件");
			return i;
		}
		if (n < 1) {
			System.out.println("行值不对，为您读取第一行");
			n = 1;
		}

		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(textHand), "utf-8"));

			textDataRows = new ArrayList<String>();
			String textDataOneRow = null;
			while ((textDataOneRow = in.readLine()) != null) {
				i++;
				if (i == n) {
					textDataRows.add(textDataOneRow);
					System.out.println("读取了第" + i + "行条数据");
					return i;
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in = null;
		}
		return i;
	}

	// 读取第一行文本
	public int readRow() {
		int i = 0;
		if (textHand == null) {
			System.out.println("文件未打开，请先打开文件");
			return i;
		}

		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(textHand), "utf-8"));

			textDataRows = new ArrayList<String>();
			String textDataOneRow = null;
			while ((textDataOneRow = in.readLine()) != null) {
				i++;
				if (i == 1) {
					textDataRows.add(textDataOneRow);
					System.out.println("读取了第" + i + "行条数据");
					return i;
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in = null;
		}
		return i;
	}

	// 4 读取全文并分行存储,最多读取1000行
	public int readRowAll() {
		int i = 0;
		if (textHand == null) {
			System.out.println("文件未打开，请先打开文件");
			return i;
		}

		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(textHand), "utf-8"));

			textDataRows = new ArrayList<String>();
			String textDataOneRow = null;
			while ((textDataOneRow = in.readLine()) != null) {
				i++;
				if (i > 1000) {
					break;
				}
				textDataRows.add(textDataOneRow);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in = null;
		}
		System.out.println("读取了共" + i + "行数据");
		return i;
	}

	// 5 读取指定范围行文本
	public int readRow(int o, int e) {

		int i = 0;
		if (textHand == null) {
			System.out.println("文件未打开，请先打开文件");
			return i;
		}
		if (o < 1) {
			System.out.println("开始行值不对，请确认行值>0");
			return i;
		}
		if (e < 1) {
			System.out.println("结束行值不对，请确认行值>0");
			return i;
		}

		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(textHand), "utf-8"));

			textDataRows = new ArrayList<String>();

			String textDataOneRow = null;

			while ((textDataOneRow = in.readLine()) != null) {
				i++;
				if ((e > o ? e : o) >= i && i >= (o < e ? o : e)) {
					textDataRows.add(textDataOneRow);
					System.out.println("读取了第" + i + "行条数据");
				}
			}
			in.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			in = null;
		}
		return i;
	}

	// 6 修改全文
	public void modifyAll(List<String> textDataRows) {
		this.textDataRows = textDataRows;
		flush();
	}

	// 刷入文本
	public void flush() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(textHand), "utf-8"));

			for (String string : textDataRows) {
				out.println(string);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out = null;
			System.out.println("数据修改成功！");
		}
	}

	// 7 修改指定行
	public void modify(int n, String str) {
		n -= 1;
		if (n > textDataRows.size()) {
			System.out.println("指定行不存在，修改结束");
			return;
		} else if (n < 0) {
			System.out.println("指定行不存在，修改结束");
			return;
		} else if (0 == textDataRows.size()) {
			textDataRows.add(str);
			flush();
			return;
		} else {
			ArrayList<String> tem = (ArrayList<String>) textDataRows;
			tem.set(n, str);
			textDataRows = tem;
			flush();
			return;
		}
	}

	// 修改指定行为空
	public void modify(int n) {
		n -= 1;
		if (n > textDataRows.size()) {
			System.out.println("指定行不存在，修改结束");
			return;
		}
		if (n < 0) {
			System.out.println("指定行不存在，修改结束");
			return;
		}
		if (0 == textDataRows.size()) {
			System.out.println("指定行不存在，修改结束");
			return;
		}
		ArrayList<String> tem = (ArrayList<String>) textDataRows;
		tem.set(n, "");
		textDataRows = tem;
		flush();
	}

	// 8 修改指定范围行
	public void modify(int o, String[] strArray) {
		int n = o - 1;
		if (n > textDataRows.size()) {
			System.out.println("指定行不存在，修改结束");
			return;
		}
		if (n < 0) {
			System.out.println("指定行不存在，修改结束");
			return;
		}

		int j = textDataRows.size();
		ArrayList<String> tem = (ArrayList<String>) textDataRows;
		for (int i = 0; i < strArray.length; i++) {
			if (j - n <= 0) {
				textDataRows.add(strArray[i]);
				continue;
			}
			tem.set(n, strArray[i]);
			textDataRows = tem;
			n += 1;
		}
		flush();
	}

	// 删除全部

	// 9 删除全文
	public void deleteAll() {
		textDataRows = null;
		textDataRows = new ArrayList<String>();
		flush();
	}

	// 删除第n行文本
	// 10 删除指定行
	public void delete(int n) {
		if ((n > 0) && (n <= textDataRows.size())) {
			ArrayList<String> tem = (ArrayList<String>) textDataRows;
			tem.remove(n - 1);
			textDataRows = tem;
			flush();
		} else {
			System.out.println("无该行，删除失败");
		}
	}

	// 删除范围行o到e行，包括o行和e行
	// 11 删除指定范围行
	public void delete(int o, int e) {
		ArrayList<String> tem = (ArrayList<String>) textDataRows;
		for (int i = e; i > o; i--) {
			if ((i > 0) && (i <= textDataRows.size())) {
				tem.remove(i - 1);
			} else {
				System.out.println("无该行，删除失败");
			}
		}
		textDataRows = tem;
		flush();
	}

	// 打印内容
	public void toPrint() {
		System.out.println(textDataRows.toString());
	}
}
