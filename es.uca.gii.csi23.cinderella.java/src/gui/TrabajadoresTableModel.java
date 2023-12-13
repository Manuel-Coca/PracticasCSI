package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import data.Trabajador;

public class TrabajadoresTableModel extends AbstractTableModel {

	private final List<Trabajador> _aData;
	
	public TrabajadoresTableModel(List<Trabajador> aData) { _aData = aData; }
	
	@Override
	public int getRowCount() { return _aData.size(); }

	@Override
	public int getColumnCount() { return 2; }

	@Override
	public Object getValueAt(int iRow, int iColumn) {
		switch(iColumn) {
			case 0: return _aData.get(iRow).GetNombre();
			case 1: return _aData.get(iRow).GetTipoTrabajador().GetNombre();
			default: throw new IllegalArgumentException("Error, la columna no existe.");
		}
	}
	
	public Trabajador getData(int iRow) { return _aData.get(iRow); }
}