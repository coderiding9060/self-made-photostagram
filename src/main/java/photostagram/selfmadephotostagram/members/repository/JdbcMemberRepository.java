package photostagram.selfmadephotostagram.members.repository;

import org.springframework.jdbc.datasource.DataSourceUtils;
import photostagram.selfmadephotostagram.members.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Member> selectMemberById(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member where id = ?";
        Member member = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            member = new Member();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setReg_date(rs.getDate("reg_date"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> selectMemberByEmail(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member where email = ?";
        Member member = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            member = new Member();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setReg_date(rs.getDate("reg_date"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> logInMember(String id, String pw) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member where id = ? and pw = ?";
        Member member = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pw);
            rs = pstmt.executeQuery();
            member = new Member();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setReg_date(rs.getDate("reg_date"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> selectMemberList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member";
        Member member = null;
        List<Member> memberList = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            member = new Member();
            memberList = new ArrayList<>();
            while(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setReg_date(rs.getDate("reg_date"));
                memberList.add(member);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return memberList;
    }

    @Override
    public Optional<Member> insertMember(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "insert into member(id,pw,name,email,gender,reg_date) values(?,?,?,?,?,sysdate)";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,member.getId());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getGender());
            rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
            } else {
                throw new IllegalStateException("아이디 생성 실패");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.ofNullable(member);
    }

    @Override
    public int updateMember(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "update member set pw=?,email=?,gender=? where id=?";
        int updateCount = 0;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getPw());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getGender());
            pstmt.setString(4,member.getId());
            updateCount = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return updateCount;
    }

    @Override
    public int deleteMember(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "delete from member where id=?";
        int deleteCount = 0;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            deleteCount = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return deleteCount;
    }

    // 커넥션을 받아오는 메서드
    private Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }

    // 커넥션 자원을 반환하는 메서드
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if(rs!=null){
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // SQL 예외 발생시 커넥션을 release하는 메서드
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
