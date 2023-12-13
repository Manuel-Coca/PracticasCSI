package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import data.TipoTrabajador;

public class TiposTrabajadoresTableModel extends AbstractTableModel {

	private final List<TipoTrabajador> _aData;
	
	public TiposTrabajadoresTableModel(List<TipoTrabajador> aData) { _aData = aData; }
	
	@Override
	public int getRowCount() { return _aData.size(); }

	@Override
	public int getColumnCount() { return 1; }

	@Override
	public Object getValueAt(int iRow, int iColumn) {
		switch(iColumn) {
			case 0: return _aData.get(iRow).GetNombre();
			default: throw new IllegalArgumentException("Error, la columna no existe.");
		}
	}
	
	public TipoTrabajador getData(int iRow) { return _aData.get(iRow); }
}