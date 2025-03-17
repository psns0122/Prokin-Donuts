package repository;

import config.DBUtil;
import dto.memberDTO.MemberDTO;
import vo.memberVO.MemberVO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MemberRepoImpl implements MemberRepo {


    Connection conn = null;
    CallableStatement cs = null;

    /* 회원 등록 메서드
    * 총관리자는 창고관리자를 등록한다.
    * */
    @Override
    public Optional<MemberVO> insertMember(MemberVO member) {
        conn = DBUtil.getConnection();

        try {
            String sql = "{call insertMember('member',?,?,?,?,?,?,?,?)}";

            cs = conn.prepareCall(sql);

            cs.setInt(1, member.getMemberNo());
            cs.setInt(2, member.getAuthorityId());
            cs.setString(3,member.getName());
            cs.setString(4,member.getPhoneNumber());
            cs.setString(5,member.getEmail());
            cs.setString(6,member.getAddress());
            cs.setString(7,member.getId());
            cs.setString(8,member.getPassword());

            int rs = cs.executeUpdate();

            //실행 성공 시 객체 반환, 실패 시 빈 optional반환
            if(rs > 0) return  Optional.of(member);
            else return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    @Override
    public Optional<MemberVO> updateMember(String memberId, MemberVO updateMember) {
        conn =DBUtil.getConnection();

        try {
            String sql = "{call updateMember(?, ?, ? ,? ,? ,? ,? )}";
            cs = conn.prepareCall(sql);

            cs.setString(1,memberId);
            cs.setString(2,updateMember.getName());
            cs.setString(3,updateMember.getPhoneNumber());
            cs.setString(4,updateMember.getEmail());
            cs.setString(5,updateMember.getAddress());
            cs.setString(6,updateMember.getId());
            cs.setString(7,updateMember.getPassword());

            int rs = cs.executeUpdate();
            //실행 성공 시 객체 반환, 실패 시 빈 optional반환
            if(rs > 0) return  Optional.of(updateMember);
            else return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
            }
        return Optional.empty();
    }

    @Override
    public Optional<String> deleteMember(String memberId) {
        return Optional.empty();
    }

    @Override
    public boolean requestMember(MemberVO member) {
        return false;
    }

    @Override
    public Optional<MemberVO> approvalMember(String memberId) {
        return Optional.empty();
    }

    @Override
    public <T> Optional<List<MemberVO>> loadMember(String searchAttribute, T serchValue) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MemberVO>> allLoadMember() {
        return Optional.empty();
    }

    @Override
    public Optional<String> searchLoginfo(String searchValue) {
        return Optional.empty();
    }

    @Override
    public Optional<String> logInnOut(String memberId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MemberVO>> loadRequestMember() {
        return Optional.empty();
    }
}
