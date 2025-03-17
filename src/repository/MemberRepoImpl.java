package repository;

import config.DBUtil;
import dto.memberDTO.MemberDTO;
import dto.memberDTO.MemberRequestDTO;
import vo.memberVO.MemberReauestVO;
import vo.memberVO.MemberVO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepoImpl implements MemberRepo {


    Connection conn = null;
    CallableStatement cs = null;
    ResultSet rs = null;

    // 회원 등록 메서드
    @Override
    public Optional<MemberVO> insertMember(MemberVO member) {
        conn = DBUtil.getConnection();

        try {
            String sql = "{call insertMember('member',?,?,?,?,?,?,?,?)}";

            cs = conn.prepareCall(sql);

            cs.setInt(1, member.getAuthorityId());
            cs.setString(2,member.getName());
            cs.setString(3,member.getPhoneNumber());
            cs.setString(4,member.getEmail());
            cs.setString(5,member.getAddress());
            cs.setString(6,member.getId());
            cs.setString(7,member.getPassword());

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

    // 회원 수정 메서드
    @Override
    public Optional<MemberVO> updateMember(MemberVO updateMember) {
        conn =DBUtil.getConnection();

        try {
            String sql = "{call updateMember(?, ?, ? ,? ,? ,? ,? )}";
            cs = conn.prepareCall(sql);

            cs.setInt(1,updateMember.getMemberNo());
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

    //회원 삭제 메서드
    @Override
    public Optional<String> deleteMember(String memberId) {
        conn = DBUtil.getConnection();

        try {
            String sql = "{call deleteMember(?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1,memberId);

            int rs = cs.executeUpdate();

            if(rs>0) {
                return Optional.of(memberId);
            } else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    // 회원 가입 요청 메서드
    @Override
    public Optional<MemberReauestVO> requestMember(MemberReauestVO member) {
        conn = DBUtil.getConnection();

        try {
            String sql = "{call insertMember('memberrequest',?,?,?,?,?,?,?,?)}";

            cs = conn.prepareCall(sql);

            cs.setInt(1, member.getAuthorityId());
            cs.setString(2,member.getName());
            cs.setString(3,member.getPhoneNumber());
            cs.setString(4,member.getEmail());
            cs.setString(5,member.getAddress());
            cs.setString(6,member.getId());
            cs.setString(7,member.getPassword());

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

    //회원 승인 메서드
    @Override
    public Optional<String> approvalMember(String memberId) {
        conn = DBUtil.getConnection();

        try {
            String sql = "{call Approval(?)}";
            cs = conn.prepareCall(sql);

            cs.setString(1,memberId);
            int rs = cs.executeUpdate();

            if (rs > 0 ) return Optional.of(memberId);
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(null,cs,conn);
        }
        return Optional.empty();
    }

    //회원 검색 메서드
    @Override
    public Optional<List<MemberVO>> loadMember(String searchAttribute, String serchValue) {
        List<MemberVO> loadMemberList = new ArrayList<>();
        conn = DBUtil.getConnection();
        try {
            String sql = "{call searchMember(?,?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1,searchAttribute);
            cs.setString(2, serchValue);

            rs = cs.executeQuery();

            while (rs.next()){
                MemberVO memberVO = new MemberVO();
                memberVO.setMemberNo(rs.getInt("memberNo"));
                memberVO.setAuthorityId(rs.getInt("authorityId"));
                memberVO.setName(rs.getString("name"));
                memberVO.setPhoneNumber(rs.getString("phoneNumber"));
                memberVO.setEmail(rs.getString("email"));
                memberVO.setAddress(rs.getString("address"));
                memberVO.setId(rs.getString("id"));
                memberVO.setPassword(rs.getString("password"));
                memberVO.setLogstatus(rs.getString("logstatus"));
                loadMemberList.add(memberVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeQuietly(rs,cs,conn);
        }
        return Optional.empty();
    }

    //전체 회원 조회 기능
    @Override
    public Optional<List<MemberVO>> allLoadMember() {
        List<MemberVO> allLoadMemberList = new ArrayList<>();
        conn = DBUtil.getConnection();
        try {
            String sql = "{call loadMember()}";
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();

            while (rs.next()){
                MemberVO memberVO = new MemberVO();
                memberVO.setMemberNo(rs.getInt("memberNo"));
                memberVO.setAuthorityId(rs.getInt("authorityId"));
                memberVO.setName(rs.getString("name"));
                memberVO.setPhoneNumber(rs.getString("phoneNumber"));
                memberVO.setEmail(rs.getString("email"));
                memberVO.setAddress(rs.getString("address"));
                memberVO.setId(rs.getString("id"));
                memberVO.setPassword(rs.getString("password"));
                memberVO.setLogstatus(rs.getString("logstatus"));
                allLoadMemberList.add(memberVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
