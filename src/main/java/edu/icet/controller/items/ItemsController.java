package edu.icet.controller.items;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.ItemBo;
import edu.icet.dto.Item;
import edu.icet.dto.ItemDetail;
import edu.icet.dto.tm.ItemTM;
import edu.icet.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ItemsController implements Initializable {
    public JFXTextField txtItemCode;
    public JFXTextField txtName;
    public JFXTextField txtDescription;
    public JFXTextField txtSearchCode;
    public Button btnSave;
    public Button btnUpdate;
    public JFXComboBox cmbCategory;
    public JFXComboBox cmbSupplier;
    public JFXTextField txtQtyS;
    public JFXTextField txtBuyingPriceS;
    public JFXTextField txtSellingPriceS;
    public JFXTextField txtQty;
    public JFXTextField txtBuyingPrice;
    public JFXTextField txtSellingPrice;
    public JFXComboBox cmbSelectSize;
    public JFXTextField txtQtyM;
    public JFXTextField txtQtyL;
    public JFXTextField txtQtyXL;
    public JFXTextField txtBuyingPriceM;
    public JFXTextField txtBuyingPriceL;
    public JFXTextField txtBuyingPriceXL;
    public JFXTextField txtSellingPriceM;
    public JFXTextField txtSellingPriceL;
    public JFXTextField txtSellingPriceXL;

    ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSizes();
    }

    public void loadSizes(){
        ObservableList<Object> sizes = FXCollections.observableArrayList();
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        cmbSelectSize.setItems(sizes);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        
        Item item = new Item(txtItemCode.getText(),
                txtName.getText(),
                txtDescription.getText(),
                cmbSupplier.getSelectionModel().getSelectedItem().toString(),
                cmbCategory.getSelectionModel().getSelectedItem().toString(),
                itemList
                );
        itemBo.saveItem(item);

    }

    public void btnDeteleOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void cmbSelectSize(ActionEvent actionEvent) {
    }

    List<ItemDetail> itemList = new ArrayList<>();
    public void btnAddOnAction(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String size = cmbSelectSize.getSelectionModel().getSelectedItem().toString();
        String qty = txtQty.getText();
        Double buyingPrice = Double.valueOf(txtBuyingPrice.getText());
        Double sellingPrice = Double.valueOf(txtSellingPrice.getText());
        itemList.add(new ItemDetail(itemCode,size,qty,buyingPrice,sellingPrice));

        if(size.equals("S")){
            txtQtyS.setText(qty);
            txtBuyingPriceS.setText(String.valueOf(buyingPrice));
            txtSellingPriceS.setText(String.valueOf(sellingPrice));
        }else if(size.equals("M")){
            txtQtyM.setText(qty);
            txtBuyingPriceM.setText(String.valueOf(buyingPrice));
            txtSellingPriceM.setText(String.valueOf(sellingPrice));
        }else if(size.equals("L")){
            txtQtyL.setText(qty);
            txtBuyingPriceL.setText(String.valueOf(buyingPrice));
            txtSellingPriceL.setText(String.valueOf(sellingPrice));
        }else{
            txtQtyXL.setText(qty);
            txtBuyingPriceXL.setText(String.valueOf(buyingPrice));
            txtSellingPriceXL.setText(String.valueOf(sellingPrice));

        }
    }
}
