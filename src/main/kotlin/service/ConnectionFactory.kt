import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class ConnectionFactory {
    internal var conn: Connection? = null
    internal var username = "root" // provide the username
    internal var password = "root"

    fun getConnection() :Connection?{
        if (conn == null) {
            conn = createConnection()
        }
        return conn
    }

    private fun createConnection() :Connection? {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/englishworddb",
                username,
                password
            )
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
        return null
    }
}