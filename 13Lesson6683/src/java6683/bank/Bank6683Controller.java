package java6683.bank;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Elxect
 * @date 2021/6/8 8:30 上午
 */
public class Bank6683Controller {
	public TextField tfBankID;
	public TextField tfBalance0;
	public Label lblErrMsg;
	public TextArea taMoney;
	public Label lblBalance;
	public TextArea taResult;
	private BankAccount6683 bankAccount6683;

	public void start6683() {
		taResult.clear();
		//账户ID
		String bankId = tfBankID.getText().trim();
		if (bankId.isEmpty()) {
			alertShow("银行账号不能为空！");
			return;
		}
		//账户余额
		String balance = tfBalance0.getText().trim();
		if (!isInteger(balance)) {
			alertShow("请输入正确格式的余额！");
			return;
		}
		AtomicInteger threadName = new AtomicInteger();
		bankAccount6683 = new BankAccount6683(bankId, Double.parseDouble(balance));
		//根据输入创建银行账户
		if (bankAccount6683.getBalance() >= 0) {
			String taMoneyText = taMoney.getText().trim();
			Stream<String> moneyStream = Stream.of(taMoneyText.split("\n"));
			moneyStream.filter(this::isInteger).forEach(item -> {
				new Thread(this::run6683, item + ",线程" + threadName.incrementAndGet()).start();
			});
		} else {
			alertShow("初始余额不能小于0");
		}
	}

	public void run6683() {
		String[] name = Thread.currentThread().getName().split(",", 0);
		String action;
		String operation;
		double money = Double.parseDouble(name[0]), b;
		if (money > 0) {
			action = "存款" + money;
		} else {
			action = "取款" + money;
		}
		//加锁
		synchronized (bankAccount6683) {
			b = bankAccount6683.getBalance() + money;
			if (b < 0) {
				operation = name[1] + " " + action + "失败," + "余额不足（" + bankAccount6683.getBalance() + "）";
			} else {
				operation = name[1] + " " + action;
				bankAccount6683.setBalance(b);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(() -> {
				lblBalance.setText("最终余额:" + bankAccount6683.getBalance());
				taResult.appendText(operation + "\n");
			});
		}
	}

	/**
	 * 提示框
	 *
	 * @param msg
	 */
	public void alertShow(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(msg);
		alert.show();
	}

	/**
	 * 判断是否输入整数
	 *
	 * @param str
	 * @return
	 */
	public boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d][^\\n\\r]*$");
		return pattern.matcher(str).matches();
	}
}
