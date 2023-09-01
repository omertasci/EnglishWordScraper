import java.sql.Statement

class WordDao {

    fun saveWordList(words: List<WordData>) {
        // Bir statement nesnesi oluşturun
        val conn = ConnectionFactory().getConnection()
        val statement = ConnectionFactory().getConnection()?.prepareStatement(
            "INSERT INTO word (code, definition, type, level, link, create_date) VALUES (?, ?, ?, ?, ?, SYSDATE())",
            Statement.RETURN_GENERATED_KEYS
        )

        for (word in words) {
            if (statement != null) {
                statement.setString(1, word.code)
                statement.setString(2, word.definition)
                statement.setString(3, word.type)
                statement.setString(4, word.level)
                statement.setString(5, word.link)

                statement.executeUpdate()
            }
        }

        // Sonucu alın
        val resultSet = statement?.getGeneratedKeys()
        if (resultSet != null) {
            while (resultSet.next()) {
                println(resultSet.getInt(1))
            }
        }

        // Bağlantıyı kapatın
        if (conn != null) {
            conn.close()
        }
    }

}