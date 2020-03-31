package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

public class AppMessageForm extends ActionForm {

	private static final long serialVersionUID = 2813866169924057029L;
	private Integer clientId;// 对应的登录的客户的编号
	private String messageType;// 订单的类型，当点击查看的时候改变其的显示
	private String status;// 订单的状态，准备还是完成
	private String InputSeedId;// 入库子订单编号
	private String ExportSeedId;// 出库子订单id
	private String ShiftStockId;// 过户子订单id

	public String getInputSeedId() {
		return InputSeedId;
	}

	public void setInputSeedId(String inputSeedId) {
		InputSeedId = inputSeedId;
	}

	public String getExportSeedId() {
		return ExportSeedId;
	}

	public void setExportSeedId(String exportSeedId) {
		ExportSeedId = exportSeedId;
	}

	public String getShiftStockId() {
		return ShiftStockId;
	}

	public void setShiftStockId(String shiftStockId) {
		ShiftStockId = shiftStockId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
