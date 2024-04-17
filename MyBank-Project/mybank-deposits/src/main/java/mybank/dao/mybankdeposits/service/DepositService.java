package mybank.dao.mybankdeposits.service;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.entity.DepositsAvailed;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class DepositService implements DepositInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
    Logger logger = LoggerFactory.getLogger(DepositService.class);

    @Override
    public ResponseEntity<?> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException {
        try {
            String url = resourceBundle.getString("spring.datasource.url");
            String username = resourceBundle.getString("spring.datasource.username");
            String password = resourceBundle.getString("spring.datasource.password");
            try (Connection con = DriverManager.getConnection(url, username, password)) {
                try (CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?, ?)}")) {
                    statement.setDouble(1, roi);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.execute();

                    try (ResultSet rs = (ResultSet) statement.getObject(2)) {
                        if (!rs.next()) {
                            throw new DepositException(messageBundle.getString("deposit.exception"));
                        }

                        DepositsMapper mapper = new DepositsMapper();
                        List<DepositsAvailable> deposits = new ArrayList<>();
                        do {
                            deposits.add(mapper.mapRow(rs, rs.getRow()));
                        } while (rs.next());
                        logger.info(messageBundle.getString("roi.fetch.success"));
                        return new ResponseEntity<>(deposits, HttpStatus.OK);
                    }
                }
            }
        } catch (DataAccessException | SQLException ex) {
            // Handle DataAccessException
            if (ex.getCause() instanceof SQLException) {
                SQLException sqlCause = (SQLException) ex.getCause();
                if (sqlCause.getErrorCode() == -20001) {
                    logger.warn(messageBundle.getString("deposit.exception"));
                    throw new DepositException(messageBundle.getString("roi.no.deposits"));
                } else {
                    logger.warn(messageBundle.getString("internal.error"));
                    throw new SQLSyntaxErrorException();
                }
            }
//            ex.printStackTrace();
            logger.error(messageBundle.getString("fetch.error"));
            return new ResponseEntity<>(messageBundle.getString("fetch.error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @Override
//    public ResponseEntity<?> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException {
//        try {
//            String url = resourceBundle.getString("spring.datasource.url");
//            String username = resourceBundle.getString("spring.datasource.username");
//            String password = resourceBundle.getString("spring.datasource.password");
//            Connection con = DriverManager.getConnection(url, username, password);
//
//                CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?, ?)}");
//                statement.setDouble(1, roi);
//                statement.registerOutParameter(2, Types.REF_CURSOR);
//            statement.execute();
//
//            try (ResultSet rs = (ResultSet) statement.getObject(2)) {
//                if (!rs.next()) {
//                    throw new DepositException(messageBundle.getString("deposit.exception"));
//                }
//
//                DepositsMapper mapper = new DepositsMapper();
//                List<DepositsAvailable> deposits = new ArrayList<>();
//                do {
//                    deposits.add(mapper.mapRow(rs, rs.getRow()));
//                } while (rs.next());
//                return new ResponseEntity<>(deposits, HttpStatus.OK);
//            }
//        } catch (DataAccessException | SQLException ex) {
//            // Handle DataAccessException
//            if (ex.getCause() instanceof SQLException) {
//                SQLException sqlCause = (SQLException) ex.getCause();
//                if (sqlCause.getErrorCode() == -20001) {
////                    return new ResponseEntity<>("No deposits found for the provided ROI.", HttpStatus.NOT_FOUND);
//                    throw new DepositException(messageBundle.getString("deposit.exception"));
//                } else {
//                    throw new SQLSyntaxErrorException();
//                }
//            }
//            ex.printStackTrace();
//            return new ResponseEntity<>("Error occurred while accessing data", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    //    Search deposits based on ROI - stream().filter()
//    @Override
//    public ResponseEntity<?> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException {
//        try {
//            List<DepositsAvailable> deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
//                    new BeanPropertyRowMapper<>(DepositsAvailable.class));
//
//            List<DepositsAvailable> roiDeposits = deposits.stream().filter(deposit -> deposit.getDepositRoi()>=roi).collect(Collectors.toList());
//
//            if (roiDeposits.isEmpty()) {
//                logger.warn(messageBundle.getString("deposit.exception"));
//                throw new DepositException(messageBundle.getString("deposit.exception"));
//            } else {
//                logger.info(messageBundle.getString("roi.fetch.success"));
//                return ResponseEntity.ok(roiDeposits);
//            }
//        } catch (DataAccessException sqlException) {
//            logger.error(messageBundle.getString("internal.error"));
//            throw new SQLSyntaxErrorException(messageBundle.getString("internal.error"));
//        }
//    }



    //            List<SqlParameter> list = Arrays.asList(
//                    new SqlParameter(Types.DOUBLE),
//                    new SqlOutParameter("deposit_data", Types.REF_CURSOR)
//            );
//
//            Map<String, Object> returnedDeposits = jdbcTemplate.call(creator, list);
//
//            // Extract data from the result set and populate depositsList
//            List<Map<String, Object>> rows = (List<Map<String, Object>>) returnedDeposits.get("deposit_data");
////            System.out.println(returnedDeposits);
//            List<DepositsAvailable> depositsList = new ArrayList<>();
//            for (Map<String, Object> row : rows) {
//                DepositsAvailable deposit = new DepositsAvailable();
//                deposit.setDepositId((Long) row.get("deposit_id"));
//                deposit.setDepositName((String) row.get("deposit_name"));
//                deposit.setDepositRoi((Double) row.get("deposit_roi"));
//                deposit.setDepositType((String) row.get("deposit_type"));
//                deposit.setDepositDescription((String) row.get("deposit_description"));
//                depositsList.add(deposit);
//            }
//
//            return new ResponseEntity<>(depositsList, HttpStatus.OK);


//    //Search deposits based on ROI - Procedure
//    @Override
//    public ResponseEntity<?> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException {
//        List<DepositsAvailable> depositsList = new ArrayList<>();
//
//        try {
//            CallableStatementCreator creator = con -> {
//                CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?,?,?,?,?,?)}");
//                statement.setDouble(1, roi);
//                statement.registerOutParameter(2, Types.NUMERIC);
//                statement.registerOutParameter(3, Types.VARCHAR);
//                statement.registerOutParameter(4, Types.NUMERIC);
//                statement.registerOutParameter(5, Types.VARCHAR);
//                statement.registerOutParameter(6, Types.VARCHAR);
//                return statement;
//            };
//
//            List<SqlParameter> list = Arrays.asList(
//                    new SqlParameter(Types.DOUBLE),
//                    new SqlOutParameter("id", Types.NUMERIC),
//                    new SqlOutParameter("name", Types.VARCHAR),
//                    new SqlOutParameter("out_roi", Types.DOUBLE),
//                    new SqlOutParameter("type", Types.VARCHAR),
//                    new SqlOutParameter("description", Types.VARCHAR)
//            );
//
//            Map<String, Object> returnedDeposits = jdbcTemplate.call(creator, list);
////            System.out.println("Returned Deposits: " + returnedDeposits);
//
//            DepositsAvailable deposit = new DepositsAvailable();
//            BigDecimal id = (BigDecimal) returnedDeposits.get("id");
//            if (id != null) {
//                Long longValue = id.longValue();
//                deposit.setDepositId(longValue);
//            }
//            deposit.setDepositName((String) returnedDeposits.get("name"));
//            BigDecimal outRoi = (BigDecimal) returnedDeposits.get("out_roi");
//            if (outRoi != null) {
//                Double doubleOutRoi = outRoi.doubleValue();
//                deposit.setDepositRoi(doubleOutRoi);
//            }
//            deposit.setDepositType((String) returnedDeposits.get("type"));
//            deposit.setDepositDescription((String) returnedDeposits.get("description"));
//
//            depositsList.add(deposit);
//            logger.info(messageBundle.getString("roi.fetch.success"));
//            return ResponseEntity.ok(depositsList);
//
//        } catch (DataAccessException sqlException) {
//            if (sqlException.getCause() instanceof SQLException) {
//                SQLException sqlCause = (SQLException) sqlException.getCause();
//                if (sqlCause.getErrorCode() == 20002) {
//                    logger.warn(messageBundle.getString("roi.no.deposits"));
//                    throw new DepositException(messageBundle.getString("roi.no.deposits"));
//                } else {
//                    logger.error(messageBundle.getString("internal.error"));
//                    throw new SQLSyntaxErrorException(messageBundle.getString("internal.error"));
//                }
//            }
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(messageBundle.getString("fetch.error"));
//        }
//    }










//    //Search deposits based on ROI - stream().filter()
//    @Override
//    public List<DepositsAvailable> searchDepositsByRoi(double roi) throws SQLSyntaxErrorException {
//        List<DepositsAvailable> deposits;
//        try {
//            deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
//                    new BeanPropertyRowMapper<>(DepositsAvailable.class));
//        } catch (DataAccessException sqlException) {
//            logger.error(resourceBundle.getString("internal.error"));
//            throw new SQLSyntaxErrorException(resourceBundle.getString("internal.error"));
//        }
//
//        List<DepositsAvailable> roiDeposits = deposits.stream().filter(deposit -> deposit.getDepositRoi()==roi).collect(Collectors.toList());
//
//        if (roiDeposits.size()==0) {
//            logger.warn(resourceBundle.getString("deposit.exception"));
//            throw new DepositException(resourceBundle.getString("deposit.exception"));
//        } else
//            return roiDeposits;
//    }
//
//    @Override
//    public ResponseEntity<?> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException {
//        return null;
//    }


    //Listing all deposits -> For Soap
    @Override
    public List<DepositsAvailable> listAllDeposits() throws SQLSyntaxErrorException {
        List<DepositsAvailable> deposits;
        try {
            deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
                    new BeanPropertyRowMapper<>(DepositsAvailable.class));
        } catch (DataAccessException sqlException) {
            throw new SQLSyntaxErrorException();
        }
        if (deposits.size()==0) {
            logger.warn(resourceBundle.getString("deposit.exception"));
            throw new DepositException(resourceBundle.getString("deposit.exception"));
        }

        else
            return deposits;
    }




//    @Override
//    public List<DepositsAvailable> searchDepositsByRoi(double roi) {
//        List<DepositsAvailable> depositsList = new ArrayList<>();
//
//        CallableStatementCreator creator = con -> {
//            CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?,?,?,?,?,?,?)}");
//            statement.setDouble(1,roi);
//            statement.registerOutParameter(2, Types.NUMERIC);
//            statement.registerOutParameter(3,Types.VARCHAR);
//            statement.registerOutParameter(4,Types.NUMERIC);
//            statement.registerOutParameter(5,Types.VARCHAR);
//            statement.registerOutParameter(6,Types.VARCHAR);
//            statement.registerOutParameter(7, Types.VARCHAR);
//            return statement;
//        };
//
//        List<SqlParameter> list= Arrays.asList(
//                        new SqlParameter(Types.DOUBLE),
//                        new SqlOutParameter("id",Types.NUMERIC),
//                        new SqlOutParameter("name",Types.VARCHAR),
//                        new SqlOutParameter("out_roi",Types.DOUBLE),
//                        new SqlOutParameter("type",Types.VARCHAR),
//                        new SqlOutParameter("description",Types.VARCHAR),
//                        new SqlOutParameter("deposit_info",Types.VARCHAR)
//                );
//        Map<String,Object> returnedDeposits = jdbcTemplate.call(creator,list);
//
//        if (returnedDeposits == null || returnedDeposits.isEmpty()) {
//            throw new DepositException("EXC-002: No deposits with given return of interest");
//        }
//
//
//        DepositsAvailable deposit = new DepositsAvailable();
//        BigDecimal id = (BigDecimal) returnedDeposits.get("id");
//        if (id != null) {
//            long longValue = id.longValue();
//            deposit.setDepositId(longValue);
//        }
//        deposit.setDepositName((String) returnedDeposits.get("name"));
//        BigDecimal outRoi = (BigDecimal) returnedDeposits.get("out_roi");
//        if (outRoi != null) {
//            double doubleOutRoi = outRoi.doubleValue();
//            deposit.setDepositRoi(doubleOutRoi);
//        }
//        deposit.setDepositType((String) returnedDeposits.get("type"));
//        deposit.setDepositDescription((String) returnedDeposits.get("description"));
//
//        depositsList.add(deposit);
//
//        return depositsList;
//    }


//


//    @Override
//    public ResponseEntity<?> searchDepositsByRoi(double roi) throws SQLSyntaxErrorException {
//        List<DepositsAvailable> depositsList = new ArrayList<>();
//
//        CallableStatementCreator creator = con -> {
//            CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?,?,?,?,?,?,?)}");
//            statement.setDouble(1,roi);
//            statement.registerOutParameter(2, Types.NUMERIC);
//            statement.registerOutParameter(3,Types.VARCHAR);
//            statement.registerOutParameter(4,Types.NUMERIC);
//            statement.registerOutParameter(5,Types.VARCHAR);
//            statement.registerOutParameter(6,Types.VARCHAR);
//            statement.registerOutParameter(7, Types.VARCHAR);
//            return statement;
//        };
//
//        List<SqlParameter> list = Arrays.asList(
//                new SqlParameter(Types.DOUBLE),
//                new SqlOutParameter("id",Types.NUMERIC),
//                new SqlOutParameter("name",Types.VARCHAR),
//                new SqlOutParameter("out_roi",Types.DOUBLE),
//                new SqlOutParameter("type",Types.VARCHAR),
//                new SqlOutParameter("description",Types.VARCHAR),
//                new SqlOutParameter("deposit_info",Types.VARCHAR)
//        );
//        Map<String,Object> returnedDeposits = jdbcTemplate.call(creator,list);
//
//        if (returnedDeposits == null || returnedDeposits.isEmpty()) {
//            throw new DepositException("EXC-002: No deposits with given return of interest");
//        }
//
//        DepositsAvailable deposit = new DepositsAvailable();
//        BigDecimal id = (BigDecimal) returnedDeposits.get("id");
//        if (id != null) {
//            long longValue = id.longValue();
//            deposit.setDepositId(longValue);
//        }
//        deposit.setDepositName((String) returnedDeposits.get("name"));
//        BigDecimal outRoi = (BigDecimal) returnedDeposits.get("out_roi");
//        if (outRoi != null) {
//            double doubleOutRoi = outRoi.doubleValue();
//            deposit.setDepositRoi(doubleOutRoi);
//        }
//        deposit.setDepositType((String) returnedDeposits.get("type"));
//        deposit.setDepositDescription((String) returnedDeposits.get("description"));
//
//        depositsList.add(deposit);
//
//        return ResponseEntity.ok(depositsList);
//    }



//    @Override
//    public List<DepositsAvailable> searchDepositsByRoi(double roi) {
//        List<DepositsAvailable> deposits;
//        deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available where deposit_roi=?",
//                new Object[]{roi},
//                new BeanPropertyRowMapper<>(DepositsAvailable.class));
//        if (deposits.size()==0)
//            throw new DepositException("EXC-002: No deposits with given return of interest");
//        else
//            return deposits;
//    }


    @Override
    public Optional<DepositsAvailable> searchDepositById(Long id) {
        return null;
    }

    @Override
    public DepositsAvailed availDeposit(DepositsAvailed depositsAvailed) {
        return null;
    }

    public class DepositsMapper implements RowMapper<DepositsAvailable> {

        @Override
        public DepositsAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            DepositsAvailable deposit = new DepositsAvailable();
            deposit.setDepositId(rs.getLong("deposit_id"));
            deposit.setDepositName(rs.getString("deposit_name"));
            deposit.setDepositRoi(rs.getDouble("deposit_roi"));
            deposit.setDepositType(rs.getString("deposit_type"));
            deposit.setDepositDescription(rs.getString("deposit_description"));
            return deposit;
        }
    }
}


