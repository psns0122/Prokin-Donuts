import java.sql.*;

public class DBInventoryRepoImpl implements InventoryRepo {
    @Override
    public int getInventoryQuantity(String productId) {
        String sql = "SELECT quantity FROM Inventory WHERE productId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
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
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
