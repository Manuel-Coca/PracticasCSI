package gui;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import data.TipoTrabajador;

public class TipoTrabajadorListModel 
	extends AbstractListModel<TipoTrabajador> 
	implements ComboBoxModel<TipoTrabajador>{
	
	private final List<TipoTrabajador> _aData;
	private Object _oSelectedItem = null;
	
	public TipoTrabajadorListModel(List<TipoTrabajador> aData) { _aData = aData; }

	@Override
	public int getSize() { return _aData.size(); }

	@Override
	public TipoTrabajador getElementAt(int iIndex) { return _aData.get(iIndex); }

	@Override
	public void setSelectedItem(Object oSelectedItem) { _oSelectedItem = oSelectedItem; }

	@Override
	public Object getSelectedItem() { return _oSelectedItem; }
}