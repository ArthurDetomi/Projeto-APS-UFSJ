package edu.ufsj.utils;

import javax.swing.table.AbstractTableModel;

public class TableDataModel extends AbstractTableModel {

    private String[] colunas = new String[0];
    private Object[][] dados = new Object[0][0];

    public void setData(String[] colunas, Object[][] dados) {
        this.colunas = colunas;
        this.dados = dados;
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return dados.length;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dados[rowIndex][columnIndex];
    }
}
