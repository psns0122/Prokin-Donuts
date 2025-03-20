package repository;

import config.DBUtil;
import dto.InventoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepoImpl implements InventoryRepo {
    @Override
    public List<InventoryDTO> getAllInventory() {
        String sql = "SELECT * FROM Inventory;";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()){

                List<InventoryDTO> list = new ArrayList<>();
                while (rs.next()) {
                    InventoryDTO dto = new InventoryDTO();
                    dto.setInventoryId(rs.getString("inventoryId"));
                    dto.setQuantity(rs.getInt("quantity"));
                    dto.setProductId(rs.getString("productId"));
                    dto.setWarehouseId(rs.getString("warehouseId"));
                    list.add(dto);
                }

                return list;

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public int getInventoryQuantity(String productId) {
        String sql = "SELECT quantity FROM Inventory WHERE productId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateInventoryQuantity(String productId, int newQuantity) {
        String sql = "UPDATE Inventory SET quantity = ? WHERE productId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
