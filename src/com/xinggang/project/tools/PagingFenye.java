package com.xinggang.project.tools;

public class PagingFenye {

	private int pageNow;// 当前页
	private int rowSize;// 显示的行数
	private int pageCount;// 显示的总页数
	private int rowCount;// 显示的总行数

	public void page(int rowSize, int pageNow, int rowCount) {// rowSize传入过来要显示的行数pageNow当前页rowCount总行数
		this.setRowSize(rowSize);
		this.setPageNow(pageNow);
		this.setRowCount(rowCount);
		// 记录总页数
		if (rowCount % rowSize == 0) {
			pageCount = rowCount / rowSize;
		} else if (rowSize < rowCount) {
			pageCount = 1;
		} else {
			pageCount = rowCount / rowSize + 1;
		}
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}
