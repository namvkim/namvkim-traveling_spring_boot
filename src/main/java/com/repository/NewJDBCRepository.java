package com.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.entity.NewEntity;

@Repository
public class NewJDBCRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class NewEntityRowMapper implements RowMapper<NewEntity> {
		@Override
		public NewEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			NewEntity newEntity = new NewEntity();
			newEntity.setId(rs.getLong("id"));
			newEntity.setTitle(rs.getString("title"));
			newEntity.setShortDescription(rs.getString("shortdescription"));
			newEntity.setContent(rs.getString("content"));
			newEntity.setThumbnail(rs.getString("thumbnail"));
			newEntity.setCreatedDate(rs.getTimestamp("created_date"));
			return newEntity;
		}
	}

	public List<NewEntity> findAll() {
		return jdbcTemplate.query("select * from new", new NewEntityRowMapper());
	}

	public List<NewEntity> findById(Long id) {
		return jdbcTemplate.query("select * from new where id=?", new NewEntityRowMapper(), id);
	}

	public int deleteById(Long id) {
		return jdbcTemplate.update("delete from new where id=?", new Object[] { id });
	}

	public int insert(NewEntity newEntity) {
		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		return jdbcTemplate.update(
				"insert into new (title, shortdescription, content, thumbnail,created_date,modified_date) "
						+ "values(?, ?, ?, ?, ?, ?)",
				new Object[] { newEntity.getTitle(), newEntity.getShortDescription(), newEntity.getContent(),
						newEntity.getThumbnail(), dateTime, dateTime });
	}

	public int update(NewEntity newEntity) {
		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		return jdbcTemplate.update(
				"update new " + " set title = ?, shortdescription = ?, content = ?,thumbnail=? modified_date=?" + " where id = ?",
				new Object[] { newEntity.getTitle(), newEntity.getShortDescription(), newEntity.getContent(),
						newEntity.getThumbnail(),dateTime, newEntity.getId() });
	}
}
